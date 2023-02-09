package hpe.tfm.dao.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.tfm.dao.model.TfmTestStep;
import hpe.tfm.dao.model.TfmTestStepParamMap;
import hpe.tfm.dao.repository.TfmTestStepParamMapRepository;
import hpe.tfm.dao.repository.TfmTestStepRepository;
import hpe.tfm.dao.service.TfmTestStepDaoService;

@Service("TfmTestStepDaoService")
public class TfmTestStepDaoServiceImpl implements TfmTestStepDaoService {

	@Autowired
	private TfmTestStepRepository repository;
	
	@Autowired
	private TfmTestStepParamMapRepository paramMapRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	private TfmTestStep tfmTestStep;
	
	private TfmTestStepParamMap tfmParamMap;

	public TfmTestStepDaoService setTestStepParam() {
		tfmParamMap = new TfmTestStepParamMap();
		return this;
	}
	
	public TfmTestStepDaoService setParamValues(int stepId, int paramId, String value, int ruleId, int ruleValue) {
		tfmParamMap.setTfmTsId(stepId);
		tfmParamMap.setTfmParamId(paramId);
		tfmParamMap.setTfmParamAValue(value);
		tfmParamMap.setTfmParamRuleId(ruleId);
		tfmParamMap.setTfmParamRuleValue(ruleValue);
		return this;
	}
	
	public TfmTestStepDaoService setTfmTestStep() {
		this.tfmTestStep = new TfmTestStep();
		return this;
	}
	
	public TfmTestStepDaoService setValue(String name, int tfm_req_t_id, int tfm_res_t_id, int tfm_s_tc_id, int tfm_app_id, int tfm_exec_priority, String tfm_ts_value) {
		tfmTestStep.setTfmTstepName(name);
		tfmTestStep.setTfmReqTId(tfm_req_t_id);
		tfmTestStep.setTfmResTId(tfm_res_t_id);
		tfmTestStep.setTfmSTcId(tfm_s_tc_id);
		tfmTestStep.setTtfmAppId(tfm_app_id);
		tfmTestStep.setTfmTsExecPriority(tfm_exec_priority);
		tfmTestStep.setTfmTsValue(tfm_ts_value);
		return this;
	}

	@Transactional(value = TxType.NEVER)
	public int addTestStep() {
		TfmTestStep testStep = repository.saveAndFlush(tfmTestStep);
		return testStep.getTfmTstepId();
	}
	
	@Transactional(value = TxType.NEVER)
	public int addTestStepParam() {
		TfmTestStepParamMap pmap = paramMapRepository.saveAndFlush(tfmParamMap);
		return pmap.getId();
	}

	public List<TfmTestStep> findAllTStepByTCaseId(int testCaseId) {
		return repository.findByTfmSTcId(testCaseId);
	}
	
	public TfmTestStep findStepById(int id) {
		Optional<TfmTestStep> result = repository.findById(id);
		return result.get();
	}
	
	public TfmTestStep findStepByName(String name) {
		return repository.findByTfmTstepName(name);
	}
	
	public int findReqTypeIdForTestStep(int id) {
		List<Integer> resultList = em
				.createQuery("select TFM_REQ_T_TYPE from tfm.TFM_REQUEST_TEMPLATE_PT where TFM_REQ_T_ID = (select TFM_REQ_T_id from tfm.tfm_test_step where tfm_ts_id = :teststepid)")
				.setParameter("teststepid", id)
				.getResultList();
		return resultList.get(0);
	}
	
	public Iterable<TfmTestStepParamMap> findAllParamForTestStep(int testStepId) {
		//List<Integer> param = Stream.of(id).collect(Collectors.toList());
		return paramMapRepository.findByTfmTsId(testStepId);
	}
	
	public List<TfmTestStepParamMap> findTStepParamByTStepId(int tfmTsId) {
		List<TfmTestStepParamMap> result = paramMapRepository.findByTfmTsId(tfmTsId);
		return result;
	}
	
}
