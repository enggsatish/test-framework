package hpe.tfm.dao.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.tfm.dao.model.TfmTestCaseParamMap;
import hpe.tfm.dao.repository.TfmTestCaseParamMapRepository;
import hpe.tfm.dao.service.TfmRequestParamTestCaseMapDaoService;

@Service("TfmRequestParamTestCaseMapDaoService")
public class TfmRequestParamTestCaseMapDaoServiceImpl implements TfmRequestParamTestCaseMapDaoService {

	private static final Logger log = LoggerFactory.getLogger(TfmRequestParamTestCaseMapDaoServiceImpl.class);
	
	@Autowired
	private TfmTestCaseParamMapRepository repository;
	
	private TfmTestCaseParamMap tfmRequestParamTestCaseMap;
	
	public TfmRequestParamTestCaseMapDaoService setTfmRequestParamTestCaseMap(TfmTestCaseParamMap tfmRequestParamTestCaseMap) {
		this.tfmRequestParamTestCaseMap = tfmRequestParamTestCaseMap;
		return this;
	}
	
	public int addTfmRequestParamTestCaseMap() {
		TfmTestCaseParamMap result = repository.save(tfmRequestParamTestCaseMap);
		return result.getTfmParamId();
	}
	
	public TfmTestCaseParamMap getByParamId(int id) {
		//TODO
		return null;
	}
}
