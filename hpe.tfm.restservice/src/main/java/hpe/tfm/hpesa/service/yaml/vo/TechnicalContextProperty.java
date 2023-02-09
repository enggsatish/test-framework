package hpe.tfm.hpesa.service.yaml.vo;

public class TechnicalContextProperty {
	
	public TechnicalContextProperty() {}
	
	private String name;
	
	private String type;
	
	private String nodevalue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String value) {
		this.type = value;
	}

	public String getNodevalue() {
		return nodevalue;
	}

	public void setNodevalue(String nodevalue) {
		this.nodevalue = nodevalue;
	}
}
