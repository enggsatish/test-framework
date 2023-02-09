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
@Table (name = "tfm_test_step")
public class TfmTestStep {

	public TfmTestStep() {}
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tfm_ts_id")
	private int tfmTstepId;
	
	@Column(name = "tfm_ts_name")
	private String tfmTstepName;
	
	@Column(name = "tfm_req_t_id")
	private int tfmReqTId;
	
	@Column(name = "tfm_res_t_id")
	private int tfmResTId;
	
	@Column(name = "tfm_s_tc_id")
	private int tfmSTcId;

	@Column(name = "tfm_app_id")
	private int tfmAppId;
	
	@Column(name = "tfm_ts_exec_priority")
	private int tfmTsExecPriority;

	@Column(name = "tfm_ts_value")
	private String tfmTsValue;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = TfmRequestTemplatePt.class, optional = false)
	@JoinColumn(name = "tfm_req_t_id", updatable = false, insertable = false, nullable = true)
	private TfmRequestTemplatePt tfmRequestTemplatePt;

	public String getTfmTstepName() {
		return tfmTstepName;
	}

	public void setTfmTstepName(String tfmTstepName) {
		this.tfmTstepName = tfmTstepName;
	}

	public TfmRequestTemplatePt getTfmRequestTemplatePt() {
		return tfmRequestTemplatePt;
	}

	public void setTfmRequestTemplatePt(TfmRequestTemplatePt tfmRequestTemplatePt) {
		this.tfmRequestTemplatePt = tfmRequestTemplatePt;
	}

	public int getTfmTstepId() {
		return tfmTstepId;
	}

	public void setTfmTstepId(int tfm_ts_id) {
		this.tfmTstepId = tfm_ts_id;
	}

	public int getTfmReqTId() {
		return tfmReqTId;
	}

	public void setTfmReqTId(int tfm_req_t_id) {
		this.tfmReqTId = tfm_req_t_id;
	}

	public int getTfmResTId() {
		return tfmResTId;
	}

	public void setTfmResTId(int tfm_res_t_id) {
		this.tfmResTId = tfm_res_t_id;
	}

	public int getTfmSTcId() {
		return tfmSTcId;
	}

	public void setTfmSTcId(int tfm_s_tc_id) {
		this.tfmSTcId = tfm_s_tc_id;
	}

	public int getTfmAppId() {
		return tfmAppId;
	}

	public void setTtfmAppId(int tfm_app_id) {
		this.tfmAppId = tfm_app_id;
	}

	public int getTfmTsExecPriority() {
		return tfmTsExecPriority;
	}

	public void setTfmTsExecPriority(int tfm_ts_exec_priority) {
		this.tfmTsExecPriority = tfm_ts_exec_priority;
	}

	public String getTfmTsValue() {
		return tfmTsValue;
	}

	public void setTfmTsValue(String tfm_ts_value) {
		this.tfmTsValue = tfm_ts_value;
	}

	@Override
	public String toString() {
		return "TfmTestStep [tfm_ts_id=" + tfmTstepId + ", tfm_req_t_id=" + tfmReqTId + ", tfm_res_t_id="
				+ tfmResTId + ", tfm_s_tc_id=" + tfmSTcId + ", tfm_app_id=" + tfmAppId
				+ ", tfm_ts_exec_priority=" + tfmTsExecPriority + ", tfm_ts_value=" + tfmTsValue
				//+ ", tfmRequestTemplatePt=" + tfmRequestTemplatePt + "]"
				;
	}
}
