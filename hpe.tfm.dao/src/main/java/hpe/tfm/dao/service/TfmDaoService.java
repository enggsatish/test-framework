package hpe.tfm.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TfmDaoService {

	@Autowired
	private TfmTestSuiteDaoService testSuiteDaoService;

	@Autowired
	private TfmTestCaseDaoService testCaseDaoService;

	@Autowired
	private TfmRequestTemplatePtDaoService templateDaoService;

	@Autowired
	private TfmTestStepDaoService tfmTestStepDaoService;
	
	@Autowired
	private TfmParamRuleMapDaoService tfmParamRuleMapDaoService;
	
	@Autowired
	private TfmParamPtDaoService tfmParamPtDaoService;
	
	@Autowired
	private TfmTestStepTemplateDaoService tfmTestStepTemplateService;
	
	public TfmTestStepTemplateDaoService getTfmTestStepTemplateService() {
		return tfmTestStepTemplateService;
	}
	
	public TfmParamPtDaoService getTfmParamPtDaoService() {
		return tfmParamPtDaoService;
	}

	public TfmParamRuleMapDaoService getTfmParamRuleMapDaoService() {
		return tfmParamRuleMapDaoService;
	}
	
	public TfmTestSuiteDaoService getTfmTestSuiteDaoService() {
		return testSuiteDaoService;
	}

	public TfmTestCaseDaoService getTfmTestCaseDaoService() {
		return testCaseDaoService;
	}

	public TfmTestStepDaoService getTfmTestStepDaoService() {
		return tfmTestStepDaoService;
	}

	public TfmRequestTemplatePtDaoService getTfmRequestTemplateDaoService() {
		return templateDaoService.setTfmRequestTemplate();
	}

}
