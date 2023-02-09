package hpe.tfm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.tfm.dao.model.TfmTestCase;
import hpe.tfm.dao.model.TfmTestSuite;
import hpe.tfm.dao.service.TfmTestCaseDaoService;
import hpe.tfm.dao.service.TfmTestSuiteDaoService;
import hpe.tfm.request.yaml.converter.TfmYamlService;
import hpe.tfm.request.yaml.converter.YamlHttpMessageConverter;
import hpe.tfm.request.yaml.vo.Properties;
import hpe.tfm.request.yaml.vo.YamlTestSuiteVo;

@Service
public class TfmTestSuiteService extends AbstractTfmService{

	private static final Logger log = LoggerFactory.getLogger(TfmTestSuiteService.class.getSimpleName());
	
	@Autowired
	private TfmTestSuiteDaoService testSuiteDaoService;
	
	@Autowired
	private TfmTestCaseDaoService testCaseDaoService;
	
	TfmYamlService<YamlTestSuiteVo> yamlService = new YamlHttpMessageConverter<YamlTestSuiteVo>();
	
	public String findAllCaseBySuiteName(String suiteName) {
		StringBuffer result = new StringBuffer();
		TfmTestSuite testSuite = testSuiteDaoService.findByName(suiteName);
		if (null == testSuite)
			result.append("ERROR:: No test suite with name :: " + suiteName);
		List<TfmTestCase> caseList = testCaseDaoService.findBySuiteId(testSuite.getId());
		if (null != caseList) {
			for (TfmTestCase tCase : caseList) {
				result.append(tCase.getTfmSTcName() +"\n");
			}
		} else {
			result.append("ERROR:: No test cases for suite :: " + suiteName);
		}
		return result.toString();
	}
	
	/**
	 * This method will process input YAML file
	 * create the test suite. 
	 * update parameters for this test suite. 
	 * 
	 * @param yaml
	 * @return
	 */
	public String process(String yaml) {
		init();
		StringBuffer result = new StringBuffer();
		log.debug("Process:: START");
		try {
			YamlTestSuiteVo vo = yamlService.getObjectForString(YamlTestSuiteVo.class, yaml);
			TfmTestSuite suiteModel = testSuiteDaoService.findByName(vo.getName());
			if (null != suiteModel) {
				log.info("Test Suite with name already present :: " + vo.getName());
				result.append("Test Suite with name already present :: " + vo.getName());
				return result.toString();
			}
			int id = testSuiteDaoService
					.setTestSuite()
					.setValues(vo.getName(), vo.getDescription(), yaml)
					.addTestSuite();
			updateParams(id, vo.getProperties());
			result.append("SUCCESS").append("::id::").append(id).append("::template-name::").append(vo.getName());
		} catch (Exception e) {
			result.append("ERROR").append("::").append(e.getMessage());
		}
		log.debug("Process:: END");
		return result.toString();
	}
	
	private void updateParams(int id, List<Properties> props) {
		for (Properties prop : props) {
			testSuiteDaoService
					.setTestSuiteParam()
					.setParamValues(id, aliasParamIdMap.get(prop.getKey()), prop.getValue())
					.addTestSuiteParam();
		}
				
	}
}
