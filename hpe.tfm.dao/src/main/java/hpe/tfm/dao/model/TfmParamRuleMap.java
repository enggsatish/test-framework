package hpe.tfm.dao.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "tfm_param_rule_map")
public class TfmParamRuleMap implements Serializable{
	
	private static final long serialVersionUID = -8341442821434522019L;

	public TfmParamRuleMap() {}
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	
	
	@Column(name = "tfm_param_id")
	private int tfmParamId;
	
	@Column(name = "tfm_param_rule_id")
	private int tfmParamRuleId;
	
	@Column(name = "tfm_param_rule_key")
	private int tfmParamRuleKey;
	
	@Column(name = "tfm_param_rule_value")
	private int tfmParamRuleValue;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = TfmParamRule.class)
	@JoinColumn(name = "tfm_param_rule_id", referencedColumnName = "tfm_param_rule_id", nullable = false, updatable = false, insertable = false)
	private TfmParamRule paramRule;
	
	public int getTfmParamRuleKey() {
		return tfmParamRuleKey;
	}

	public void setTfmParamRuleKey(int tfmParamRuleKey) {
		this.tfmParamRuleKey = tfmParamRuleKey;
	}

	public TfmParamRule getParamRule() {
		return paramRule;
	}

	public void setParamRule(TfmParamRule paramRule) {
		this.paramRule = paramRule;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTfmParamId() {
		return tfmParamId;
	}
	
	public void setTfmParamId(int tfm_param_id) {
		this.tfmParamId = tfm_param_id;
	}
	
	public int getTfmParamRuleId() {
		return tfmParamRuleId;
	}
	
	public void setTfmParamRuleId(int tfmParamRuleValue) {
		this.tfmParamRuleId = tfmParamRuleValue;
	}
	
	public int getTfmParamRuleValue() {
		return tfmParamRuleValue;
	}
	
	public void setTfmParamRuleValue(int tfmParamRuleValue) {
		this.tfmParamRuleValue = tfmParamRuleValue;
	}
	
	@Override
	public String toString() {
		return "Tfm_Param_Rule_Map [TFM_PARAM_ID=" + tfmParamId + ", TFM_PARAM_RULE_ID=" + tfmParamRuleId
				+ ", TFM_PARAM_RULE_VALUE=" + tfmParamRuleValue + "]";
	}
}
