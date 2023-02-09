package hpe.tfm.dao.service;

import hpe.tfm.dao.model.TfmRequestTemplatePt;

public interface TfmRequestTemplatePtDaoService {
	
	public int addTfmRequestTemplate();

	public TfmRequestTemplatePt getById(int id);

	public TfmRequestTemplatePtDaoService setValues(String tfm_req_t_type, String tfm_req_t_file_path);
	
	public TfmRequestTemplatePtDaoService setRule(String rule_key, String rule_value);

	public TfmRequestTemplatePtDaoService setTfmRequestTemplate();
	
	public TfmRequestTemplatePtDaoService setValues(String tfm_req_t_value);
	
	public void deleteById(int id);
}
