package hpe.tfm.dao.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.tfm.dao.model.TfmTestSuite;
import hpe.tfm.dao.model.TfmTestSuiteParamMap;
import hpe.tfm.dao.repository.TfmTestSuiteParamMapRepository;
import hpe.tfm.dao.repository.TfmTestSuiteRepository;
import hpe.tfm.dao.service.TfmTestSuiteDaoService;

@Service("TfmTestSuiteDaoService")
public class TfmTestSuiteDaoServiceImpl implements TfmTestSuiteDaoService {
	
	private static final Logger log = LoggerFactory.getLogger(TfmTestSuiteDaoServiceImpl.class);
	
	@Autowired
	private TfmTestSuiteRepository repository;
	
	@Autowired
	private TfmTestSuiteParamMapRepository paramMapRepository;

	private TfmTestSuite testSuit;
	
	private TfmTestSuiteParamMap paramMap;
	
	public TfmTestSuiteDaoService setTestSuiteParam() {
		this.paramMap = new TfmTestSuiteParamMap();
		return this;
	}
	
	public TfmTestSuiteDaoService setParamValues(int suiteId, int paramId, String paramValue) {
		paramMap.setTfmSuiteId(suiteId);
		paramMap.setTfmParamId(paramId);
		paramMap.setTfmParamAValue(paramValue);
		return this;
	}
	
	public TfmTestSuiteDaoService setTestSuite(TfmTestSuite testSuite) {
		this.testSuit = testSuite;
		return this;
	}
	
	public TfmTestSuiteDaoService setTestSuite() {
		this.testSuit = new TfmTestSuite();;
		return this;
	}
	
	public TfmTestSuiteDaoService setValues(String testSuiteName, String testSuiteDescription, String testSuiteValue) {
		this.testSuit.setTfmSuiteName(testSuiteName);
		this.testSuit.setTfmSuiteDescription(testSuiteDescription);
		this.testSuit.setTfmSuiteValue(testSuiteValue);
		return this;
	}
	
	public int addTestSuite() {
		TfmTestSuite suit = repository.saveAndFlush(testSuit);
		return suit.getId();
	}
	
	public int addTestSuiteParam() {
		TfmTestSuiteParamMap pmap = paramMapRepository.saveAndFlush(paramMap);
		return pmap.getTfmParamId();
	}
	
	public TfmTestSuite getById(int id) {
		Optional<TfmTestSuite> suite = repository.findById(id);
		return suite.get();
	}
	
	public TfmTestSuite findByName(String name) {
		return repository.findByTfmSuiteName(name); 
	}
	
	public Iterable<TfmTestSuiteParamMap>  findAllParamForTestSuite(Integer... id) {
		List<Integer> param = Stream.of(id).collect(Collectors.toList());
		return paramMapRepository.findAllById(param);
	}
	
	public List<TfmTestSuiteParamMap> findSuiteParamByTfmSuiteId(int tfmSuiteId) {
		List<TfmTestSuiteParamMap> result = paramMapRepository.findByTfmSuiteId(tfmSuiteId);
		return result;
	}
}
