package hpe.tfm.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.tools.xjc.BadCommandLineException;

import hpe.tfm.dao.model.TfmRequestTemplatePt;
import hpe.tfm.dao.model.TfmTestStep;
import hpe.tfm.dao.service.TfmDaoService;
import hpe.tfm.dao.service.TfmRequestTemplatePtDaoService;
import hpe.tfm.dao.service.TfmTestStepDaoService;
import hpe.tfm.request.mgmt.TfmRequest;
import hpe.tfm.request.mgmt.xml.TfmRequestGenerator;
import hpe.tfm.request.yaml.vo.YamlRequestTemplate;
import hpe.tfm.service.util.VideoServiceUtil;

@Service
public class TfmRequestTemplateService {
	
	private static final Logger log = LoggerFactory.getLogger(TfmRequestTemplateService.class.getSimpleName());
	
	@Autowired
	private TfmDaoService daoService;
	
	@Autowired
	private TfmTestStepDaoService testStepDaoService;
	
	@Autowired
	private TfmRequest tfmRequest;
	
	public String generateTestStep(String testStepId) {		
		TfmRequestGenerator requestGenerator = null;
		try {
			int testStepIdInt = Integer.parseInt(testStepId);
			requestGenerator = tfmRequest.requestGenerator().setTestStepId(testStepIdInt);
			TfmTestStep tStep = testStepDaoService.findStepById(testStepIdInt);
			if (null == tStep) {
				return "ERROR:: No test step with id :: " + testStepId;
			}
		} catch (NumberFormatException nfe) {
			TfmTestStep tStep = testStepDaoService.findStepByName(testStepId);
			if (null == tStep) {
				return "ERROR:: No test step with name :: " + testStepId;
			}
			requestGenerator = tfmRequest.requestGenerator().setTestStepId(tStep.getTfmTstepId());
		}
		String xmlPayload;
		try {
			xmlPayload = requestGenerator.generateRequestPayload();
		} catch (Exception e) {
			log.error("ERROR while generating the request", e);
			e.printStackTrace();
			return "ERROR";
		}
		return xmlPayload;
	}
	
	public String process(YamlRequestTemplate yaml) {
		log.debug("process:start:value" + yaml);
		try {
			TfmRequestTemplatePtDaoService service = daoService
					.getTfmRequestTemplateDaoService()
					.setTfmRequestTemplate()
					.setValues(yaml.getRequest_template_type(), yaml.getRequest_template_filepath());
			yaml.getRules().stream().forEach(t -> service.setRule(t.getKey(), t.getValue()));
			int id = service.addTfmRequestTemplate();
			generateXmlFromXsd(id);
			log.debug("process:end:requesttemplateid" + id);
			return "SUCCESS: Template added with id -> " + id;
		} catch (Exception e) {
			log.error("Error while adding request template ", e);
		}
		return "Error: Could not add template";
	}
	
	private void generateXmlFromXsd(int id) throws IOException, BadCommandLineException {
		TfmRequestTemplatePt requestTemplatePt = daoService.getTfmRequestTemplateDaoService().getById(id);
		String binding = requestTemplatePt.getTemplateRule().stream().filter(t -> t.getRuleKey().equals("binding"))
				.findFirst().get().getRuleValue();
		String targetdir = requestTemplatePt.getTemplateRule().stream().filter(t -> t.getRuleKey().equals("targetdir"))
				.findFirst().get().getRuleValue();
		tfmRequest
			.xsdProcessor()
			.setBinding(binding)
			.setTargetDir(targetdir)
			.setXsdFilePath(requestTemplatePt.getTfmReqtFilePath())
			.generate();
	}

	public String populateRules() {
		try {
			Map<String, String> paramList = VideoServiceUtil.generateParamList();
			Map<String, String> paramRuleList = VideoServiceUtil.generateRuleList();
			Map<String, Integer> paramIds = new HashMap<String, Integer>(); 
			for (String key : paramList.keySet()) {
				int paramId = daoService.getTfmParamPtDaoService().setParamPt().setValues(key, paramList.get(key), null).addTfmRequestParam();
				log.info("populateRules::ParamId::"+ paramId);
				paramIds.put(key, paramId);
			}
			for (String key : paramRuleList.keySet()) {
				paramIds.get(key);
				int ruleid = daoService.getTfmParamRuleMapDaoService().setTfmParamRuleMap().setValues(paramIds.get(key), 1, paramIds.get(paramRuleList.get(key))).addTfmRequestParam();
				log.info("populateRules::ruleid::"+ ruleid);
			}
			return "SUCCESS: parameters added successfully.";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ERROR: parameters NOT added";
	}
}
