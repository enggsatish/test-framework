package hpe.tfm.dao.service;

import hpe.tfm.dao.model.TfmTestCaseParamMap;

public interface TfmRequestParamTestCaseMapDaoService {
	
	public TfmRequestParamTestCaseMapDaoService setTfmRequestParamTestCaseMap(TfmTestCaseParamMap tfmRequestParamTestCaseMap);
	
	public int addTfmRequestParamTestCaseMap();
	
	public TfmTestCaseParamMap getByParamId(int id);

}
