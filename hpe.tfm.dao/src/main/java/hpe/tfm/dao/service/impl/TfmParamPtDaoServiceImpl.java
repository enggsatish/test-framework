package hpe.tfm.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.tfm.dao.model.TfmParamPt;
import hpe.tfm.dao.repository.TfmParamPtRepository;
import hpe.tfm.dao.service.TfmParamPtDaoService;

@Service("TfmParamPtDaoService")
public class TfmParamPtDaoServiceImpl implements TfmParamPtDaoService {

	private static final Logger log = LoggerFactory.getLogger(TfmParamPtDaoServiceImpl.class);
	
	@Autowired
	private TfmParamPtRepository repository;
	
	private TfmParamPt tfmParamPt;
	
	public TfmParamPtDaoService setParamPt() {
		tfmParamPt = new TfmParamPt();
		return this;
	}
	
	public TfmParamPtDaoService setValues(String key, String alias, String defaultValue) {
		tfmParamPt.setTfmParamKey(key);
		tfmParamPt.setTfmParamAlias(alias);
		if (null != defaultValue)
			tfmParamPt.setTfmParamDefaultValue(defaultValue);
		return this;
	}
	
	public int addTfmRequestParam() {
		log.info("tfmRequestParam :: to be added ::" + tfmParamPt);
		TfmParamPt result = repository.saveAndFlush(tfmParamPt);
		return result.getTfmParamId();
	}
	
	public TfmParamPt findByTfmParamAlias(String tfmParamAlias) {
		TfmParamPt result = repository.findByTfmParamAlias(tfmParamAlias);
		return result;
	}
	
	public TfmParamPt getById (int id) {
		Optional<TfmParamPt> result = repository.findById(id);
		return result.get();
	}
	
	public List<TfmParamPt> findAll() {
		return repository.findAll();
	}
}
