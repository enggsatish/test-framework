package hpe.tfm.dao.service;

import java.util.List;

import hpe.tfm.dao.model.TfmParamRuleMap;

public interface TfmParamRuleMapDaoService {

	public TfmParamRuleMap getById(int id) ;
	
	public List<TfmParamRuleMap> findAll();
	
	public TfmParamRuleMapDaoService setTfmParamRuleMap();
	
	public TfmParamRuleMapDaoService setValues(int paramId, int ruleId, int ruleValue);
	
	public int addTfmRequestParam();
	
	public List<TfmParamRuleMap> findByTfmParamId(int paramId);
}
