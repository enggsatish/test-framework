package hpe.tfm.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.tfm.dao.model.TfmParamRuleMap;
import hpe.tfm.dao.repository.TfmParamRuleMapRepository;
import hpe.tfm.dao.service.TfmParamRuleMapDaoService;

@Service("TfmParamRuleMapDaoService")
public class TfmParamRuleMapDaoServiceImpl implements TfmParamRuleMapDaoService {
	
	private static final Logger log = LoggerFactory.getLogger(TfmParamRuleMapDaoServiceImpl.class);
	
	@Autowired
	private TfmParamRuleMapRepository repository;
	
	private TfmParamRuleMap paramRuleMap;
	
	public TfmParamRuleMapDaoService setTfmParamRuleMap() {
		paramRuleMap = new TfmParamRuleMap();
		return this;
	}
	
	public TfmParamRuleMapDaoService setValues(int paramId, int ruleId, int ruleValue) {
		paramRuleMap.setTfmParamId(paramId);
		paramRuleMap.setTfmParamRuleId(ruleId);
		paramRuleMap.setTfmParamRuleValue(ruleValue);
		return this;
	}
	
	public int addTfmRequestParam() {
		log.info("tfmRequestParamRuleMap :: to be added ::" + paramRuleMap);
		TfmParamRuleMap result = repository.saveAndFlush(paramRuleMap);
		return result.getTfmParamId();
	}
	
	public TfmParamRuleMap getById(int id) {
		Optional<TfmParamRuleMap> result = repository.findById(id);
		return result.get();
	}
	
	public List<TfmParamRuleMap> findByTfmParamId(int paramId) {
		List<TfmParamRuleMap> result = repository.findByTfmParamId(paramId);
		return result;
	}
	
	
	public List<TfmParamRuleMap> findAll() {
		return repository.findAll();
	}
}
