package hpe.tfm.dao.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "tfm_param_pt")
public class TfmParamPt implements Serializable {
	
	private static final long serialVersionUID = 7497595046349179538L;

	public TfmParamPt() {}

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tfm_param_id")
	private int tfmParamId;
	
	@Column(name = "tfm_param_key")
	private String tfmParamKey;
	
	@Column(name = "tfm_param_alias")
	private String tfmParamAlias;
	
	@Column(name = "tfm_param_default_value")
	private String tfmParamDefaultValue;
	
	@Column(name = "tfm_req_t_id")
	private int tfmReqTId;
	
	@OneToMany (fetch = FetchType.LAZY, targetEntity = TfmParamRuleMap.class)
	@JoinColumn(name = "tfm_param_id", referencedColumnName = "tfm_param_id", nullable = false, updatable = false, insertable = false)
	private List<TfmParamRuleMap> paramRuleList;

	public int getTfmParamId() {
		return tfmParamId;
	}

	public void setTfmParamId(int tfmParamId) {
		this.tfmParamId = tfmParamId;
	}

	public String getTfmParamKey() {
		return tfmParamKey;
	}

	public void setTfmParamKey(String tfmParamKey) {
		this.tfmParamKey = tfmParamKey;
	}

	public String getTfmParamAlias() {
		return tfmParamAlias;
	}

	public void setTfmParamAlias(String tfmParamAlias) {
		this.tfmParamAlias = tfmParamAlias;
	}

	public String getTfmParamDefaultValue() {
		return tfmParamDefaultValue;
	}

	public void setTfmParamDefaultValue(String tfmParamDefaultValue) {
		this.tfmParamDefaultValue = tfmParamDefaultValue;
	}

	public int getTfmReqTId() {
		return tfmReqTId;
	}

	public void setTfmReqTId(int tfmReqTId) {
		this.tfmReqTId = tfmReqTId;
	}

	public List<TfmParamRuleMap> getParamRuleList() {
		return paramRuleList;
	}

	public void setParamRuleList(List<TfmParamRuleMap> paramRuleList) {
		this.paramRuleList = paramRuleList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
