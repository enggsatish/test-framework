package hpe.tfm.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "tfm_param_rule")
public class TfmParamRule  implements Serializable{
	
	private static final long serialVersionUID = 7101861030383796869L;

	public TfmParamRule() {}
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tfm_param_rule_id")
	private int tfmParamRuleId;
	
	@Column(name = "tfm_param_rule_name")
	private String tfmParamRuleName;

	public int getTfmParamRuleId() {
		return tfmParamRuleId;
	}

	public void setTfmParamRuleId(int tfm_param_rule_id) {
		this.tfmParamRuleId = tfm_param_rule_id;
	}

	public String getTfmParamRuleName() {
		return tfmParamRuleName;
	}

	public void setTfmParamRuleName(String tfm_param_rule_name) {
		this.tfmParamRuleName = tfm_param_rule_name;
	}

}
