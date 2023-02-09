package hpe.tfm.dao.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.tfm.dao.model.TfmRequestTemplatePt;
import hpe.tfm.dao.model.TfmRequestTemplateRule;
import hpe.tfm.dao.repository.TfmRequestTemplateRepository;
import hpe.tfm.dao.service.TfmRequestTemplatePtDaoService;

@Service("TfmRequestTemplateDaoService")
public class TfmRequestTemplatePtDaoServiceImpl implements TfmRequestTemplatePtDaoService {

	@Autowired
	private TfmRequestTemplateRepository repository;
	
	@PersistenceContext
	private EntityManager em;
	
	private TfmRequestTemplatePt requestTemplate;
	
	public TfmRequestTemplatePtDaoService setTfmRequestTemplate() {
		this.requestTemplate = new TfmRequestTemplatePt();
		return this;
	}
	
	public TfmRequestTemplatePtDaoService setValues(String tfm_req_t_type, String tfm_req_t_file_path) {
		requestTemplate.setTfmReqtType(tfm_req_t_type);
		requestTemplate.setTfmReqtFilePath(tfm_req_t_file_path);
		return this;
	}
	
	public TfmRequestTemplatePtDaoService setRule(String rule_key, String rule_value) {
		if (null == requestTemplate.getTemplateRule()) {
			List<TfmRequestTemplateRule> ruleList = new ArrayList<TfmRequestTemplateRule>();
			requestTemplate.setTemplateRule(ruleList);
		}
		requestTemplate.getTemplateRule().add(new TfmRequestTemplateRule() {{
			setRuleKey(rule_key);
			setRuleValue(rule_value);
		}});
		return this;
	}
	
	public TfmRequestTemplatePtDaoService setValues(String tfm_req_t_value) {
		requestTemplate.setTfmReqtValue(tfm_req_t_value);
		return this;
	}
	
	public int addTfmRequestTemplate() {
		TfmRequestTemplatePt template = repository.save(requestTemplate);
		return template.getTfmReqtId();
	}
	
	public TfmRequestTemplatePt getById(int id) {
		Optional<TfmRequestTemplatePt> template = repository.findById(id);
		return template.get();
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
}
