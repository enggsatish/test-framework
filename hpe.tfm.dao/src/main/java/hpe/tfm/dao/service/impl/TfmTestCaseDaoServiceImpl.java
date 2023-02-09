package hpe.tfm.dao.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.tfm.dao.model.TfmTestCase;
import hpe.tfm.dao.model.TfmTestCaseParamMap;
import hpe.tfm.dao.repository.TfmTestCaseParamMapRepository;
import hpe.tfm.dao.repository.TfmTestCaseRepository;
import hpe.tfm.dao.service.TfmTestCaseDaoService;

@Service("TfmTestCaseDaoService")
public class TfmTestCaseDaoServiceImpl implements TfmTestCaseDaoService {

	@Autowired
	private TfmTestCaseRepository repository;
	
	@Autowired
	private TfmTestCaseParamMapRepository paramMapRepository;
	
	private TfmTestCase testCase;
	
	private TfmTestCaseParamMap caseParamMap;
	
	public TfmTestCaseDaoService setTestCaseParam() {
		caseParamMap = new TfmTestCaseParamMap();
		return this;
	}
	
	public TfmTestCaseDaoService setParamValues(int caseId, int paramId, String value) {
		caseParamMap.setTfmTcId(caseId);
		caseParamMap.setTfmParamId(paramId);
		caseParamMap.setTfmParamAValue(value);
		return this;
	}
		
	public TfmTestCaseDaoService setTfmTestCase() {
		this.testCase = new TfmTestCase();
		return this;
	}
	
	public TfmTestCaseDaoService setValues(int tfm_suite_id, String tfm_s_tc_name, String tfm_s_tc_description, String tfm_s_tc_value) {
		testCase.setTfmSTcName(tfm_s_tc_name);
		testCase.setTfmSTcDescription(tfm_s_tc_description);
		testCase.setTfmSTcValue(tfm_s_tc_value);
		testCase.setTfmSuiteId(tfm_suite_id);
		return this;		
	}
	
	@Override
	public int addTestCase() {
		TfmTestCase tfmTestCase = repository.saveAndFlush(testCase);
		return tfmTestCase.getId();
	}
	
	public int addTestCaseParam() {
		TfmTestCaseParamMap pmap = paramMapRepository.saveAndFlush(caseParamMap);
		return pmap.getTfmParamId();
	}
	
	public List<TfmTestCase> findBySuiteId(int suiteId) {
		return repository.findByTfmSuiteId(suiteId);
	}
	
	public TfmTestCase findById(int id) {
		Optional<TfmTestCase> result = repository.findById(id);
		return result.get();
	}
	
	public TfmTestCase findByTestCaseName(String tfmSTcName) {
		return repository.findByTfmSTcName(tfmSTcName);
	}
	
	public Iterable<TfmTestCaseParamMap>  findAllParamForTestCase(Integer... id) {
		List<Integer> param = Stream.of(id).collect(Collectors.toList());
		return paramMapRepository.findAllById(param);
	}
	
	public List<TfmTestCaseParamMap> findTCaseParamByTcId(int tfmTcId) {
		List<TfmTestCaseParamMap> result = paramMapRepository.findByTfmTcId(tfmTcId);
		return result;
	}
}
