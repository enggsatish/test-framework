package hpe.tfm.dao.model;

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
@Table(name = "tfm_test_step_param_map")
public class TfmTestStepParamMap {
	
	public TfmTestStepParamMap(){}
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "tfm_ts_id")
	private int tfmTsId;
	
	@Column(name = "tfm_param_id")
	private int tfmParamId;
	
	@Column(name = "tfm_param_a_value")
	private String tfmParamAValue;
	
	@Column(name = "tfm_param_rule_id")
	private int tfmParamRuleId;
	
	@Column(name = "tfm_param_rule_value")
	private int tfmParamRuleValue;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = TfmParamPt.class, optional = false)
	@JoinColumn(name = "tfm_param_id", updatable = false, insertable = false, nullable = true)
	private TfmParamPt paramPt;

	public int getTfmParamRuleId() {
		return tfmParamRuleId;
	}

	public void setTfmParamRuleId(int tfmParamRuleId) {
		this.tfmParamRuleId = tfmParamRuleId;
	}

	public int getTfmParamRuleValue() {
		return tfmParamRuleValue;
	}

	public void setTfmParamRuleValue(int tfmParamRuleValue) {
		this.tfmParamRuleValue = tfmParamRuleValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TfmParamPt getParamPt() {
		return paramPt;
	}

	public void setParamPt(TfmParamPt paramPt) {
		this.paramPt = paramPt;
	}

	public int getTfmTsId() {
		return tfmTsId;
	}

	public void setTfmTsId(int tfm_ts_id) {
		this.tfmTsId = tfm_ts_id;
	}

	public int getTfmParamId() {
		return tfmParamId;
	}

	public void setTfmParamId(int tfm_param_id) {
		this.tfmParamId = tfm_param_id;
	}

	public String getTfmParamAValue() {
		return tfmParamAValue;
	}

	public void setTfmParamAValue(String tfm_param_a_value) {
		this.tfmParamAValue = tfm_param_a_value;
	}
}
