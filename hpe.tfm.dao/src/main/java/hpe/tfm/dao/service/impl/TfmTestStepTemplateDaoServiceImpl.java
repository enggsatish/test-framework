package hpe.tfm.dao.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.tfm.dao.model.TfmTestStepTemplate;
import hpe.tfm.dao.repository.TfmTestStepTemplateRepository;
import hpe.tfm.dao.service.TfmTestStepTemplateDaoService;

@Service("TfmTestStepTemplateService")
public class TfmTestStepTemplateDaoServiceImpl implements TfmTestStepTemplateDaoService {

	@Autowired
	private TfmTestStepTemplateRepository repository;
	
	private TfmTestStepTemplate testStepTemplate;
	
	public TfmTestStepTemplateDaoService setTfmTestStepTemplate() {
		this.testStepTemplate = new TfmTestStepTemplate();
		return this;
	}
	
	public TfmTestStepTemplateDaoService setValues(String templateId, String templateDesc, String templateValue) {
		this.testStepTemplate.setTfmTestStepTemplateId(templateId);
		this.testStepTemplate.setTfmTestStepTemplateDesc(templateDesc);
		this.testStepTemplate.setTfmTestStepTemplateValue(templateValue);
		return this;
	}
	
	public String addTestStepTemplate() {
		TfmTestStepTemplate result = repository.saveAndFlush(testStepTemplate);
		return result.getTfmTestStepTemplateId();
	}
	
	public List<String> findAllTemplateIds() {
		List<TfmTestStepTemplate> templateList = repository.findAll();
		if (null != templateList) {
			List<String> templateIdList = templateList.stream().map(t -> t.getTfmTestStepTemplateId()).collect(Collectors.toList());
			return templateIdList;
		} else {
			return Collections.emptyList();
		}
	}
	
	public String findTemplate(String templateId) {
		TfmTestStepTemplate result = repository.findByTfmTestStepTemplateId(templateId);
		return result.getTfmTestStepTemplateValue();
	}
}
