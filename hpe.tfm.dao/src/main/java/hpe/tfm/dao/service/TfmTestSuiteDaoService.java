package hpe.tfm.dao.service;

import java.util.List;

import hpe.tfm.dao.model.TfmTestSuite;
import hpe.tfm.dao.model.TfmTestSuiteParamMap;

public interface TfmTestSuiteDaoService {

	public int addTestSuite();
	
	public TfmTestSuite getById(int id);
	
	public TfmTestSuiteDaoService setTestSuite();
	
	public TfmTestSuiteDaoService setTestSuite(TfmTestSuite testSuite);
	
	public TfmTestSuiteDaoService setValues(String testSuiteName, String testSuiteDescription, String testSuiteValue);
	
	public TfmTestSuiteDaoService setTestSuiteParam();
	
	public TfmTestSuiteDaoService setParamValues(int suiteId, int paramId, String paramValue);
	
	public int addTestSuiteParam();
	
	public List<TfmTestSuiteParamMap> findSuiteParamByTfmSuiteId(int tfmSuiteId);
	
	public TfmTestSuite findByName(String name);
}
