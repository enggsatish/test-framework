package hpe.tfm.dao.service;

import java.util.List;

import hpe.tfm.dao.model.TfmTestStep;
import hpe.tfm.dao.model.TfmTestStepParamMap;

public interface TfmTestStepDaoService {

	public int addTestStep();
	
	public TfmTestStep findStepById(int id);
	
	public TfmTestStepDaoService setTfmTestStep();
	
	public TfmTestStepDaoService setValue(String name, int tfm_req_t_id, int tfm_res_t_id, int tfm_s_tc_id, int tfm_app_id, int tfm_exec_priority, String tfm_ts_value);
	
	public int findReqTypeIdForTestStep(int id);
	
	public Iterable<TfmTestStepParamMap> findAllParamForTestStep(int testStepId);
	
	public int addTestStepParam();
	
	public TfmTestStepDaoService setTestStepParam();
	
	public TfmTestStepDaoService setParamValues(int stepId, int paramId, String value, int ruleId, int ruleValue);
	
	public List<TfmTestStepParamMap> findTStepParamByTStepId(int tfmTsId);
	
	public List<TfmTestStep> findAllTStepByTCaseId(int testCaseId);
	
	public TfmTestStep findStepByName(String name);
}
