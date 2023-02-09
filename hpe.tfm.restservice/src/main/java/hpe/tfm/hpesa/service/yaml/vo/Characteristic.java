package hpe.tfm.hpesa.service.yaml.vo;

public class Characteristic {
	
	public Characteristic() {}

	private String characteristicname;
	
	private String categoryname;
	
	private String value;

	public String getCharacteristicname() {
		return characteristicname;
	}

	public void setCharacteristicname(String characteristicname) {
		this.characteristicname = characteristicname;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
