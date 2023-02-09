package hpe.tfm.request.yaml.vo;

import java.util.List;

public class YamlRequestTemplate {
	
	public YamlRequestTemplate() {}
	
	private String request_template_type;
	
	private String request_template_filepath;
	
	private List<Properties> rules;
	
	public List<Properties> getRules() {
		return rules;
	}

	public void setRules(List<Properties> rules) {
		this.rules = rules;
	}

	public String getRequest_template_type() {
		return request_template_type;
	}

	public void setRequest_template_type(String request_template_type) {
		this.request_template_type = request_template_type;
	}

	public String getRequest_template_filepath() {
		return request_template_filepath;
	}

	public void setRequest_template_filepath(String request_template_filepath) {
		this.request_template_filepath = request_template_filepath;
	}
}
