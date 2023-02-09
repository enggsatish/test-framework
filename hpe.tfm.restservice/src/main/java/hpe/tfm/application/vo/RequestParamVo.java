package hpe.tfm.application.vo;

import java.util.List;
import java.util.Properties;

public class RequestParamVo {

	private String key;
	
	private String value;
	
	private List<Properties> attributeList;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Properties> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<Properties> attributeList) {
		this.attributeList = attributeList;
	}
	
	
}
