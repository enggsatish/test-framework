package hpe.tfm.dao.service;

import java.util.List;

import hpe.tfm.dao.model.TfmTestCase;
import hpe.tfm.dao.model.TfmTestCaseParamMap;

public interface TfmTestCaseDaoService {

	public int addTestCase();
	
	public TfmTestCase findById(int id);
	
	public TfmTestCaseDaoService setTfmTestCase();
	
	public TfmTestCaseDaoService setValues(int tfm_suite_id, String tfm_s_tc_name, String tfm_s_tc_description, String tfm_s_tc_value);

	public Iterable<TfmTestCaseParamMap>  findAllParamForTestCase(Integer... id);
	
	public int addTestCaseParam();
	
	public TfmTestCaseDaoService setTestCaseParam();
	
	public TfmTestCaseDaoService setParamValues(int caseId, int paramId, String value);
	
	public List<TfmTestCaseParamMap> findTCaseParamByTcId(int tfmTcId);
	
	public List<TfmTestCase> findBySuiteId(int suiteId);
	
	public TfmTestCase findByTestCaseName(String tfmSTcName);
}
