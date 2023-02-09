package hpe.tfm.request.yaml.vo;

import java.util.List;

public class YamlTestStepVo {
	
	public YamlTestStepVo() {}
	
	private String name;
	
	private String templatename;
	
	private String testcasename;
	
	private List<Properties> properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTestcasename() {
		return testcasename;
	}

	public void setTestcasename(String testcasename) {
		this.testcasename = testcasename;
	}

	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	public List<Properties> getProperties() {
		return properties;
	}

	public void setProperties(List<Properties> properties) {
		this.properties = properties;
	}

}
