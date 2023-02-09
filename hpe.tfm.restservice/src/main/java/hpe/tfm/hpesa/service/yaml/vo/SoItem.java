package hpe.tfm.hpesa.service.yaml.vo;

import java.util.List;

public class SoItem {
	
	public SoItem() {}
	
	private String action;
	
	private String serviceid;
	
	private String servicespecclassificationname;
	
	private String servicespecid;
	
	private String addressid;
	
	private String objectid;

	private List<Characteristic> characteristics;

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}

	public String getServicespecclassificationname() {
		return servicespecclassificationname;
	}

	public void setServicespecclassificationname(String servicespecclassificationname) {
		this.servicespecclassificationname = servicespecclassificationname;
	}

	public String getServicespecid() {
		return servicespecid;
	}

	public void setServicespecid(String servicespecid) {
		this.servicespecid = servicespecid;
	}

	public List<Characteristic> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(List<Characteristic> characteristics) {
		this.characteristics = characteristics;
	}

	@Override
	public String toString() {
		return "SoItem [action=" + action + ", serviceid=" + serviceid + ", servicespecclassificationname="
				+ servicespecclassificationname + ", servicespecid=" + servicespecid + ", addressid=" + addressid
				+ ", characteristics=" + characteristics + "]";
	}

	
}
