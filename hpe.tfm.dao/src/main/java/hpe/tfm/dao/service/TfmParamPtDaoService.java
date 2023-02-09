package hpe.tfm.dao.service;

import java.util.List;

import hpe.tfm.dao.model.TfmParamPt;

public interface TfmParamPtDaoService {
	
	public TfmParamPt getById (int id);
	
	public List<TfmParamPt> findAll();
	
	public TfmParamPtDaoService setParamPt();
	
	public TfmParamPtDaoService setValues(String key, String alias, String defaultValue);
	
	public int addTfmRequestParam();
	
	public TfmParamPt findByTfmParamAlias(String tfmParamAlias);
}
