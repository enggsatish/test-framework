package hpe.tfm.request.yaml.vo;

import java.util.List;

public class YamlTestSuiteVo {
	
	public YamlTestSuiteVo() {}
	
	private String name;
	
	private String description;
	
	private List<Properties> properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Properties> getProperties() {
		return properties;
	}

	public void setProperties(List<Properties> properties) {
		this.properties = properties;
	}
}
