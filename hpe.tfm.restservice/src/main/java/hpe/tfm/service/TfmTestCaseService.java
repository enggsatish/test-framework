package hpe.tfm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.tfm.dao.model.TfmTestCase;
import hpe.tfm.dao.model.TfmTestStep;
import hpe.tfm.dao.model.TfmTestSuite;
import hpe.tfm.dao.service.TfmTestCaseDaoService;
import hpe.tfm.dao.service.TfmTestStepDaoService;
import hpe.tfm.dao.service.TfmTestSuiteDaoService;
import hpe.tfm.request.yaml.converter.TfmYamlService;
import hpe.tfm.request.yaml.converter.YamlHttpMessageConverter;
import hpe.tfm.request.yaml.vo.Properties;
import hpe.tfm.request.yaml.vo.YamlTestCaseVo;

@Service
public class TfmTestCaseService extends AbstractTfmService{
	
	private static final Logger log = LoggerFactory.getLogger(TfmTestCaseService.class.getSimpleName());

	@Autowired
	private TfmTestSuiteDaoService testSuiteDaoService;
	
	@Autowired
	private TfmTestCaseDaoService testCaseDaoService;
	
	@Autowired
	private TfmTestStepDaoService tfmTestStepDaoService;
	
	TfmYamlService<YamlTestCaseVo> yamlService = new YamlHttpMessageConverter<YamlTestCaseVo>();
	
	public String findAllStepForCase(String testCaseName) {
		StringBuffer result = new StringBuffer();
		TfmTestCase tCase = testCaseDaoService.findByTestCaseName(testCaseName);
		if (null == tCase) {
			result.append("ERROR :: no test case with name :: " + testCaseName);
		}
		List<TfmTestStep> tStepList = tfmTestStepDaoService.findAllTStepByTCaseId(tCase.getId());
		if (null != tStepList) {
			for (TfmTestStep tStep : tStepList) {
				result.append(tStep.getTfmTstepName() + "\n");
			}
		} else {
			result.append("ERROR :: no test step for test case :: " + testCaseName);
		}
		return result.toString();
	}
	
	/**
	 * Process YAML file to create test case,
	 * update parameter for this test case.
	 * 
	 * @param yaml
	 * @return
	 */
	public String process(String yaml) {
		init();
		StringBuffer result = new StringBuffer();
		log.debug("Process:: START");
		try {
			YamlTestCaseVo vo = yamlService.getObjectForString(YamlTestCaseVo.class, yaml);
			TfmTestSuite suite = testSuiteDaoService.findByName(vo.getSuitename());
			int id = testCaseDaoService.setTfmTestCase().setValues(suite.getId(), vo.getName(), vo.getDescription(), yaml).addTestCase();
			updateParams(id, vo.getProperties());
			result.append("Test Case created with name :: " + vo.getName());
		} catch (Exception e) {
			e.printStackTrace();
			result.append("ERROR").append("::").append(e.getMessage());
		}
		log.debug("Process:: END");
		return result.toString();
	}
	
	private void updateParams(int id, List<Properties> props) {
		if (null == props) return;
		for (Properties prop : props) {
			testCaseDaoService
					.setTestCaseParam()
					.setParamValues(id, aliasParamIdMap.get(prop.getKey()), prop.getValue())
					.addTestCaseParam();
		}
				
	}
}
