package hpe.tfm.hpesa.service.yaml.vo;

import java.util.List;

import hpe.tfm.request.yaml.vo.Properties;

public class InputParam {
	
	public InputParam() {}
	
	private String envname;
	
	private String userid;
	
	private String tranid;
	
	private String otraid;
	
	private String jmscid;
	
	private String jmsreplyto;
	
	private String mid;
	
	private String msgtime;
	
	private String accountnumber;
	
	private String addressid;
	
	private List<SoItem> soitems;
	
	private List<Properties> params;
	
	private List<TechnicalContextProperty> tcproperties;

	public List<TechnicalContextProperty> getTcproperties() {
		return tcproperties;
	}

	public void setTcproperties(List<TechnicalContextProperty> tcproperties) {
		this.tcproperties = tcproperties;
	}

	public String getEnvname() {
		return envname;
	}

	public void setEnvname(String envname) {
		this.envname = envname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTranid() {
		return tranid;
	}

	public void setTranid(String tranid) {
		this.tranid = tranid;
	}

	public String getOtraid() {
		return otraid;
	}

	public void setOtraid(String otraid) {
		this.otraid = otraid;
	}

	public String getJmscid() {
		return jmscid;
	}

	public void setJmscid(String jmscid) {
		this.jmscid = jmscid;
	}

	public String getJmsreplyto() {
		return jmsreplyto;
	}

	public void setJmsreplyto(String jmsreplyto) {
		this.jmsreplyto = jmsreplyto;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMsgtime() {
		return msgtime;
	}

	public void setMsgtime(String msgtime) {
		this.msgtime = msgtime;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}

	public List<Properties> getParams() {
		return params;
	}

	public void setParams(List<Properties> params) {
		this.params = params;
	}

	public List<SoItem> getSoitems() {
		return soitems;
	}

	public void setSoitems(List<SoItem> soitems) {
		this.soitems = soitems;
	}

	@Override
	public String toString() {
		return "InputParam [envname=" + envname + ", userid=" + userid + ", tranid=" + tranid + ", otraid=" + otraid
				+ ", jmscid=" + jmscid + ", jmsreplyto=" + jmsreplyto + ", mid=" + mid + ", msgtime=" + msgtime
				+ ", accountnumber=" + accountnumber + ", addressid=" + addressid + ", soitems=" + soitems + ", params="
				+ params + ", tcproperties=" + tcproperties + ", getTcproperties()=" + getTcproperties()
				+ ", getEnvname()=" + getEnvname() + ", getUserid()=" + getUserid() + ", getTranid()=" + getTranid()
				+ ", getOtraid()=" + getOtraid() + ", getJmscid()=" + getJmscid() + ", getJmsreplyto()="
				+ getJmsreplyto() + ", getMid()=" + getMid() + ", getMsgtime()=" + getMsgtime()
				+ ", getAccountnumber()=" + getAccountnumber() + ", getAddressid()=" + getAddressid() + ", getParams()="
				+ getParams() + ", getSoitems()=" + getSoitems() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}	
}
