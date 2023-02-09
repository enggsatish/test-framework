package hpe.tfm.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "tfm_request_template_rule")
public class TfmRequestTemplateRule {
	
	public TfmRequestTemplateRule() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "rule_key")
	private String ruleKey;
	
	@Column(name = "rule_value")
	private String ruleValue;

	@Column(name = "tfm_req_t_id")
	private int tfmReqtId;

	public int getTfmReqtId() {
		return tfmReqtId;
	}

	public void setTfmReqtId(int tfmReqtId) {
		this.tfmReqtId = tfmReqtId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuleKey() {
		return ruleKey;
	}

	public void setRuleKey(String ruleKey) {
		this.ruleKey = ruleKey;
	}

	public String getRuleValue() {
		return ruleValue;
	}

	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
}
