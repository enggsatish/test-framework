package hpe.tfm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.tfm.dao.model.TfmTestCase;
import hpe.tfm.dao.model.TfmTestCaseParamMap;
import hpe.tfm.dao.model.TfmTestSuiteParamMap;
import hpe.tfm.dao.service.TfmTestCaseDaoService;
import hpe.tfm.dao.service.TfmTestStepDaoService;
import hpe.tfm.dao.service.TfmTestStepTemplateDaoService;
import hpe.tfm.dao.service.TfmTestSuiteDaoService;
import hpe.tfm.hpesa.service.yaml.vo.Characteristic;
import hpe.tfm.hpesa.service.yaml.vo.InputParam;
import hpe.tfm.hpesa.service.yaml.vo.SoItem;
import hpe.tfm.hpesa.service.yaml.vo.TechnicalContextProperty;
import hpe.tfm.hpesa.service.yaml.vo.TfmTestStepTemplateVo;
import hpe.tfm.request.yaml.converter.TfmYamlService;
import hpe.tfm.request.yaml.converter.YamlHttpMessageConverter;
import hpe.tfm.request.yaml.vo.Properties;
import hpe.tfm.request.yaml.vo.YamlTestStepVo;
import hpe.tfm.service.util.EsfKeyConstants;

@Service
public class TfmTestStepService extends AbstractTfmService {
	
	private static final Logger log = LoggerFactory.getLogger(TfmTestStepService.class.getSimpleName());
	
	TfmYamlService<TfmTestStepTemplateVo> yamlTemplateService = new YamlHttpMessageConverter<TfmTestStepTemplateVo>();
	
	TfmYamlService<YamlTestStepVo> yamltStepService = new YamlHttpMessageConverter<YamlTestStepVo>();
	
	@Autowired
	private TfmTestStepTemplateDaoService testStepTemplateService;
	
	@Autowired
	private TfmTestStepDaoService testStepDaoService;
	
	@Autowired
	private TfmTestCaseDaoService testCaseDaoService;
	
	@Autowired
	private TfmTestSuiteDaoService testSuiteDaoService;
	
	public String findAllTemplate() {
		List<String> tempList = testStepTemplateService.findAllTemplateIds();
		StringBuffer buffer = new StringBuffer();
		tempList.stream().forEach(t -> buffer.append(t + "\n"));
		return buffer.toString();
	}
	
	public String findTemplateByName(String teamplateName) {
		String value = testStepTemplateService.findTemplate(teamplateName);
		if (null == value || "".equals(value))
			return "ERROR: No template found with name :: " + teamplateName;
		else
			return value;
	}

	public String processTemplate(String yaml) {
		StringBuffer result = new StringBuffer();
		log.debug("Process:: START");
		try {
			TfmTestStepTemplateVo vo = yamlTemplateService.getObjectForString(TfmTestStepTemplateVo.class, yaml);
			String name = testStepTemplateService.setTfmTestStepTemplate().setValues(vo.getName(), vo.getDescription(), yaml).addTestStepTemplate();
			result.append("SUCCESS").append("::template-name::").append(name);
		} catch (Exception e) {
			log.error("ERROR while processing request ", e);
			result.append("ERROR").append("::").append(e.getMessage());
		}
		log.debug("Process:: END");
		return result.toString();
	}
	
	/**
	 * process create test step request. 
	 * 
	 * @param yaml
	 * @return
	 */
	public String process(String yaml) {
		init();
		StringBuffer result = new StringBuffer();
		log.debug("Process:: START");
		try {
			YamlTestStepVo vo = yamltStepService.getObjectForString(YamlTestStepVo.class, yaml);
			String yamlTemplate = testStepTemplateService.findTemplate(vo.getTemplatename());			
			TfmTestCase testCase = testCaseDaoService.findByTestCaseName(vo.getTestcasename());
			int stepId = createTestStep(vo.getName(), yaml, testCase.getId());
			TfmTestStepTemplateVo yamlTemplateVo = processPropertiesAndTemplate(vo, yamlTemplate, testCase);
			processInputParam(stepId, yamlTemplateVo.getInputparam(), TemplateType.test_step);
			result.append("SUCCESS").append("::test-step-id::").append(stepId).append("::test-step-template-name::").append(vo.getTemplatename());
		} catch (Exception e) {
			e.printStackTrace();
			result.append("ERROR").append("::").append(e.getMessage());
		}
		log.debug("Process:: END");
		return result.toString();
	}
	/**
	 * prepare param value. 
	 * if param is present in suite or case, then add to test step
	 * precedence suite -> case -> step
	 *  
	 * @param testCase
	 * @param vo
	 * @param yamlTemplateVo
	 * @return
	 */
	private Map<Integer, String> processParamValue(TfmTestCase testCase, YamlTestStepVo vo) {
		List<TfmTestSuiteParamMap> suiteParamList = testSuiteDaoService.findSuiteParamByTfmSuiteId(testCase.getTfmSuiteId());
		List<TfmTestCaseParamMap> caseParamList = testCaseDaoService.findTCaseParamByTcId(testCase.getId());
		Map<Integer, String> suiteParamIdValueMap = suiteParamList.stream().collect(Collectors.toMap(TfmTestSuiteParamMap::getTfmParamId, TfmTestSuiteParamMap::getTfmParamAValue));
		Map<Integer, String> caseParamIdValueMap = caseParamList.stream().collect(Collectors.toMap(TfmTestCaseParamMap::getTfmParamId, TfmTestCaseParamMap::getTfmParamAValue));
		for (Integer key : suiteParamIdValueMap.keySet()) {
			if (!caseParamIdValueMap.containsKey(key)) {
				caseParamIdValueMap.put(key, suiteParamIdValueMap.get(key));
			}
		}
		Map<Integer, String> stepParamIdValueMap = new HashMap<Integer, String>(); 
		for (Properties prop : vo.getProperties()) {
			stepParamIdValueMap.put(aliasParamIdMap.get(prop.getKey()), prop.getValue());
		}
		for (Integer key : caseParamIdValueMap.keySet()) {			
			if (!stepParamIdValueMap.containsKey(key)) {
				stepParamIdValueMap.put(key, caseParamIdValueMap.get(key));
			}
		}
		return stepParamIdValueMap;
	}
	
	private TfmTestStepTemplateVo processPropertiesAndTemplate(YamlTestStepVo vo, String yamlTemplate, TfmTestCase testCase) {		
		TfmTestStepTemplateVo templateVo = yamlTemplateService.getObjectForString(TfmTestStepTemplateVo.class, yamlTemplate);
		Map<Integer, String> stepParamIdValueMap = processParamValue(testCase, vo);
		InputParam iParam = templateVo.getInputparam();
		for (Integer key : stepParamIdValueMap.keySet()) {
			if (key == aliasParamIdMap.get(EsfKeyConstants.accountid.getAlias())) {
				iParam.setAccountnumber(stepParamIdValueMap.get(key));
			} else if (key == aliasParamIdMap.get(EsfKeyConstants.addressid.getAlias())) {
				iParam.setAddressid(stepParamIdValueMap.get(key));
			} else if (key == aliasParamIdMap.get(EsfKeyConstants.serialnumber.getAlias())) {
				for (SoItem soitemval : iParam.getSoitems()) {
					for (Characteristic charVal : soitemval.getCharacteristics()) {
						if (charVal.getCharacteristicname().equalsIgnoreCase(EsfKeyConstants.serialnumber.getAlias())) {
							charVal.setValue(stepParamIdValueMap.get(key));
							break;
						}
					}
				}
			} else if (key == aliasParamIdMap.get(EsfKeyConstants.userid.getAlias())) {
				iParam.setUserid(stepParamIdValueMap.get(key));
			} else if (key == aliasParamIdMap.get(EsfKeyConstants.tranid.getAlias())) {
				iParam.setTranid(stepParamIdValueMap.get(key));
			} else if (key == aliasParamIdMap.get(EsfKeyConstants.mid.getAlias())) {
				iParam.setMid(stepParamIdValueMap.get(key));
			} else if (key == aliasParamIdMap.get(EsfKeyConstants.msgtime.getAlias())) {
				iParam.setMsgtime(stepParamIdValueMap.get(key));
			}		
		}
		return templateVo;
	}
	
	/** @TODO remove hard coded value */
	private int createTestStep(String name, String yaml, int testCaseId) {
		int stepId = testStepDaoService
				.setTfmTestStep()
				.setValue(name, 1, 1, testCaseId, 1, 1, yaml)
				.addTestStep();
		return stepId;
	}
	
	private void processInputParam(int stepId, InputParam iParam, TemplateType type) {
		log.debug("processCaseInputParam:: START::suiteId" + stepId);
		try {			
			//setting test param and step also, dummy test step is required.
			int message = addParam(stepId, EsfKeyConstants.message.getAlias(), null, 1, 0, type);
			int bodyid = addParam(stepId, EsfKeyConstants.body.getAlias(), null, 1, message, type);
			int headerId = addParam(stepId, EsfKeyConstants.header.getAlias(), null, 1, message, type);
			int sar = addParam(stepId, EsfKeyConstants.sar.getAlias(), null, 1, bodyid, type);
			int tc = addParam(stepId, EsfKeyConstants.tc.getAlias(), null, 1, sar, type);
			//prepare the parent child relationship here and store in table.
			validateNotNullAndAddParam(stepId, EsfKeyConstants.userid.getAlias(), iParam.getUserid(), 1, headerId, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.otranid.getAlias(), iParam.getOtraid(), 1, headerId, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.jmscid.getAlias(), iParam.getJmscid(), 1, headerId, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.jmsreplyto.getAlias(), iParam.getJmsreplyto(), 1, headerId, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.tranid.getAlias(), iParam.getTranid(), 1, headerId, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.mid.getAlias(), iParam.getMid(), 1, tc, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.msgtime.getAlias(), iParam.getMsgtime(), 1, tc, type);
			if (null != iParam.getTcproperties()) {
				for (TechnicalContextProperty tcp : iParam.getTcproperties()) {
					// 3 = attribute rule, 1 == parent rule
					int tcprop = addParam(stepId, EsfKeyConstants.prop.getAlias(), null, 1, tc, type);
					addParam(stepId, EsfKeyConstants.name.getAlias(), tcp.getName(), 3, tcprop, type);
					addParam(stepId, EsfKeyConstants.type.getAlias(), tcp.getType(), 3, tcprop, type);
					addParam(stepId, EsfKeyConstants.value.getAlias(), tcp.getNodevalue(), 3, tcprop, type);
				}
			}
			addSoitems (stepId, iParam, sar, type);
		} catch(Exception e ) {
			e.printStackTrace();
		}
		log.debug("processCaseInputParam:: END::suiteId" + stepId);
	}
	
	private void addSoitems(int stepId, InputParam iParam, int sar, TemplateType type) {
		if (null == iParam.getSoitems()) return;			
		int so = addParam(stepId, EsfKeyConstants.so.getAlias(), null, 1, sar, type);
		validateNotNullAndAddParam(stepId, EsfKeyConstants.accountid.getAlias(), iParam.getAccountnumber(), 1, so, type);
		int soitems = addParam(stepId, EsfKeyConstants.soitems.getAlias(), null, 1, so, type);
		for (SoItem soitemval : iParam.getSoitems()) {
			int soitem = addParam(stepId, EsfKeyConstants.soitem.getAlias(), null, 1, soitems, type);
			int rfs = addParam(stepId, EsfKeyConstants.rfs.getAlias(), null, 1, soitem, type);
			int rfsspec = addParam(stepId, EsfKeyConstants.rfsspec.getAlias(), null, 1, rfs, type);
			int servicecharacteristic = addParam(stepId, EsfKeyConstants.servicecharacteristic.getAlias(), null, 1, rfs, type);
			int rrefs = addParam(stepId, EsfKeyConstants.rrefs.getAlias(), null, 1, rfs, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.serviceid.getAlias(), soitemval.getServiceid(), 1, rfs, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.servicespecid.getAlias(), soitemval.getServicespecid(), 1, rfsspec, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.classificationname.getAlias(), soitemval.getServicespecclassificationname(), 1, rfsspec, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.objectid.getAlias(), soitemval.getObjectid(), 1, rrefs, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.addressid.getAlias(), iParam.getAddressid(), 1, soitem, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.action.getAlias(), soitemval.getAction(), 1, soitem, type);
			//If characteristics is not null
			addCharacteristic (stepId, soitemval, servicecharacteristic, type);
		}
	}
	
	private void addCharacteristic(int stepId, SoItem soitemval, int servicecharacteristic, TemplateType type) {
		if (null ==soitemval.getCharacteristics()) return;		
		for (Characteristic charVal : soitemval.getCharacteristics()) {
			int characteristic = addParam(stepId, EsfKeyConstants.characteristic.getAlias(), null, 1, servicecharacteristic, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.carname.getAlias(), charVal.getCharacteristicname(), 1, characteristic, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.catgname.getAlias(), charVal.getCategoryname(), 1, characteristic, type);
			validateNotNullAndAddParam(stepId, EsfKeyConstants.catvalue.getAlias(), charVal.getValue(), 1, characteristic, type);
		}
	}
	
	private int addParam(int stepId, String paramName, String paramValue, int ruleId, int ruleValue, TemplateType type) {
		return testStepDaoService.setTestStepParam().setParamValues(stepId, aliasParamIdMap.get(paramName), paramValue, ruleId, ruleValue).addTestStepParam();
	}
	
	private void validateNotNullAndAddParam(int stepId, String paramName, String paramValue, int ruleId, int ruleValue, TemplateType type) {
		if (null != paramValue && !"".equals(paramValue)) {
			switch (type) {
			case test_step:
				testStepDaoService.setTestStepParam().setParamValues(stepId, aliasParamIdMap.get(paramName), paramValue, ruleId, ruleValue).addTestStepParam();
				break;
			}
		}
	}
}
