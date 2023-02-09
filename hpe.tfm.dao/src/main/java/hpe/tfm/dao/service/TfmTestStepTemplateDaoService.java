package hpe.tfm.dao.service;

import java.util.List;

public interface TfmTestStepTemplateDaoService {

	public TfmTestStepTemplateDaoService setTfmTestStepTemplate();
	
	public TfmTestStepTemplateDaoService setValues(String templateId, String templateDesc, String templateValue);
	
	public String addTestStepTemplate();
	
	public List<String> findAllTemplateIds();
	
	public String findTemplate(String templateId);
}
