package hpe.tfm.request.mgmt.xml.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.tfm.dao.model.TfmTestStepParamMap;
import hpe.tfm.dao.service.TfmDaoService;
import hpe.tfm.request.mgmt.util.TfmClazzzUtil;
import hpe.tfm.request.mgmt.util.TfmXmlRequestMarshallerWrapper;
import hpe.tfm.request.mgmt.vo.ClassVo;
import hpe.tfm.request.mgmt.vo.ParameterKeyVo;
import hpe.tfm.request.mgmt.vo.RequestParameterRuleVo;
import hpe.tfm.request.mgmt.vo.RequestParameterVo;
import hpe.tfm.request.mgmt.vo.TfmXmlRequestVo;
import hpe.tfm.request.mgmt.xml.TfmRequestGenerator;

@Service("TfmRequestGenerator")
public class TfmXmlGeneratorImpl implements TfmRequestGenerator {
	
	private static final Logger log = LoggerFactory.getLogger(TfmXmlGeneratorImpl.class);
	
	private int testStepId;

	public TfmRequestGenerator setTestStepId(int testStepId) {
		this.testStepId = testStepId;
		return this;
	}

	@Autowired
	private TfmDaoService daoService;
	
	@Autowired
	private TfmXmlRequestMarshallerWrapper marshallerWrapper;
	
	
	public String generateRequestPayload() throws Exception {
		TfmXmlRequestVo requestVo = readRequstParams();
		RequestParameterVo rootParam = requestVo.getParamMap().get(requestVo.getRootRule().getParameterKeyVo());  
		Object rootObj = TfmClazzzUtil.generateClassAndGetLeafObjectOfKey(rootParam.getTfmParamKeyId(), rootParam.getTfmParamValue(), requestVo.getObjecFactorytList()); 
		try {
			processInputToGetTree(requestVo, rootObj,requestVo.getParamMap().get(requestVo.getRootRule().getParameterKeyVo()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String marshallXml = marshallerWrapper.marshallXml(rootObj);
		log.info("GENERATED-XML::" + marshallXml);
		return marshallXml;
	}
	
	private void processInputToGetTree(TfmXmlRequestVo requestVo, Object rootObj, RequestParameterVo rootParamVo) throws Exception {
		List<RequestParameterRuleVo> paramRule = requestVo.getChildRuleMap().get(rootParamVo.getParameterKeyVo());
		if (paramRule == null ) {
			return;
		} else {
			for (RequestParameterRuleVo rule : paramRule) {
				RequestParameterVo parameterVo = requestVo.findRequestParamVoForRule(rule);
				System.err.println(parameterVo);
				//generate object of child node, this can again be a single line tree of childs
				Object childObj = TfmClazzzUtil.generateClassAndGetRootObjectOfKey(parameterVo.getTfmParamKeyId(), parameterVo.getTfmParamValue(), requestVo.getObjecFactorytList());
				parameterVo.setKeyObject(childObj);
				setChildObjectToParent (childObj, rootObj, rule, parameterVo, requestVo);
			}
			for (RequestParameterRuleVo rule2 : paramRule) {
				RequestParameterVo parameterVo2 = requestVo.findRequestParamVoForRule(rule2);
				//generate object of child node, this can again be a single line tree of childs
				processInputToGetTree(requestVo, parameterVo2.getKeyObject(), parameterVo2);
			}
		}
	}
	 
	private void setChildObjectToParent(Object childObj, Object parentObj, RequestParameterRuleVo rule,
			RequestParameterVo parameterVo, TfmXmlRequestVo requestVo)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (null == parentObj) {
			return;
		}
		String key = parameterVo.getTfmParamKeyId();		
		ClassVo classVo = null;
		if (null == childObj) {
			log.error("Child object null:: ObjectFactory could not initiate:: possible object attribute name");
			classVo = getClassVoFromMethod (parentObj, key.replace(underscore, empty));
		} else {
			classVo = getClassVoFromMethod (parentObj, childObj.getClass().getSimpleName());
		}
		if (null == classVo && null != childObj) {
			// If jaxbelement, just set value. 
			// let marshaler create node object.
			if (JAXBElement.equals(childObj.getClass().getSimpleName())) {
				classVo = getClassVoFromMethod (parentObj, key.replace(underscore, empty));
				classVo.getMethod().invoke(classVo.getObject(), parameterVo.getTfmParamValue());
				return;
			} else {
				// if null possibly there is no object to set
				// possibly object is already added to array and returned an array object.
				// next child would be to just set values.
				log.error("No possible parent-> child relation found.");
				return;
			}
		}
		// Look for setter method
		if (classVo.getMethod().getName().startsWith(set)) {
			if (null != childObj) {
				classVo.getMethod().invoke(classVo.getObject(), childObj);
			} else {
				classVo.getMethod().invoke(parentObj, parameterVo.getTfmParamValue());
			}
		}
		if (classVo.getMethod().getName().startsWith(get)) {
			if (classVo.getMethod().getReturnType().equals(List.class)) {
				List arrayObjectInstance = (List) classVo.getMethod().invoke(classVo.getObject());
				arrayObjectInstance.add(childObj);
			}
		}
	}
	
	private ClassVo getClassVoFromMethod(Object parentObj, String key) {
		Method[] declaredMethods = parentObj.getClass().getDeclaredMethods();
		for (Method method : declaredMethods) {
			if (method.getName().equalsIgnoreCase(get + key) && method.getReturnType().equals(List.class)) {
				return new ClassVo(parentObj, method);
			} else if (method.getName().equalsIgnoreCase(set + key)) {
				return new ClassVo(parentObj, method);
			}
		}
		return null;
	}
	
	private TfmXmlRequestVo readRequstParams() {
		TfmXmlRequestVo requestVo = new TfmXmlRequestVo();
		try {
			createReqParamMap(requestVo);
			//createReqParamRuleList(requestVo);
			try {
				requestVo.setObjecFactorytList(TfmClazzzUtil.getAllObjectFactory(packageName));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
			//Function<ParameterKeyVo, List<RequestParameterRuleVo>> voAsKey = paramKey->Arrays.<RequestParameterRuleVo>asList(t -> t);
//			Map<ParameterKeyVo, List<RequestParameterRuleVo>> childRuleMap = requestVo.getParamRule().stream()
//					.collect(Collectors.groupingBy(RequestParameterRuleVo::getParameterKeyVo));
//			requestVo.setChildRuleMap(childRuleMap);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return requestVo;
	}
	
	private TfmXmlRequestVo createReqParamMap(TfmXmlRequestVo requestVo) {
		Iterable<TfmTestStepParamMap> result = daoService.getTfmTestStepDaoService().findAllParamForTestStep(testStepId);
		// Generate Parameter Rule list -- parent child
		for (TfmTestStepParamMap testStepPraram : result) {
			if (testStepPraram.getTfmParamRuleValue() == 0) {
				RequestParameterRuleVo rootRule = new RequestParameterRuleVo(testStepPraram.getTfmParamId(),
						testStepPraram.getTfmParamRuleId(), 0, testStepPraram.getId(), testStepPraram.getId());
				requestVo.getParamRule().add(rootRule);
				requestVo.setRootRule(rootRule);
			}
			ParameterKeyVo key = new ParameterKeyVo(testStepPraram.getTfmParamId(), testStepPraram.getId());
			for (TfmTestStepParamMap p2 : result) {
				if (p2.getTfmParamRuleValue() == testStepPraram.getId()) {
					RequestParameterRuleVo childVo = new RequestParameterRuleVo(testStepPraram.getTfmParamId(),
							testStepPraram.getTfmParamRuleId(), p2.getTfmParamId(), testStepPraram.getId(), p2.getId());
					List<RequestParameterRuleVo> childList = requestVo.getChildRuleMap().get(key);
					if (null == childList)
						requestVo.getChildRuleMap().put(key, new ArrayList());
					requestVo.getChildRuleMap().get(key).add(childVo);
				}

			}
			for (TfmTestStepParamMap p2 : result) {
				if (p2.getId() == testStepPraram.getTfmParamRuleValue()) {
					requestVo.getParamRule().add(
							new RequestParameterRuleVo(testStepPraram.getTfmParamId(),
							testStepPraram.getTfmParamRuleId(), p2.getTfmParamId(), testStepPraram.getId(), p2.getId()));
					break;
				}
			}
		}
		// Generate Parameter List
		for (TfmTestStepParamMap testStepPraram : result) {
//			get ParamList
			requestVo.getParamMap().put(new ParameterKeyVo(testStepPraram.getTfmParamId(), testStepPraram.getId()), 
					new RequestParameterVo(testStepPraram.getId(), testStepPraram.getTfmParamId(),
							testStepPraram.getParamPt().getTfmParamKey(), testStepPraram.getTfmParamAValue(),
							testStepPraram.getParamPt().getTfmReqTId(), testStepPraram.getTfmParamRuleId(),
							testStepPraram.getTfmParamRuleValue()));
			
//			testStepPraram.getId(), testStepPraram.getTfmParamId(), testStepPraram.getParamPt().getTfmParamKey(), testStepPraram.getTfmParamAValue(), testStepPraram.getParamPt().getTfmReqTId(),testStepPraram.getTfmParamRuleId(), testStepPraram.getTfmParamRuleValue()
		}
		
		return requestVo;
	}

}
