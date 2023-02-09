package hpe.tfm.dao.model;

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
@Table(name = "tfm_test_suite_param_map")
public class TfmTestSuiteParamMap {
	
	public TfmTestSuiteParamMap() {}

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "tfm_suite_id")
	private int tfmSuiteId;
	
	@Column(name = "tfm_param_id")
	private int tfmParamId;
	
	@Column(name = "tfm_param_a_value")
	private String tfmParamAValue;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = TfmParamPt.class)
	@JoinColumn(name = "tfm_param_id", updatable = false, insertable = false)
	private TfmParamPt paramPt;

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

	public int getTfmSuiteId() {
		return tfmSuiteId;
	}

	public void setTfmSuiteId(int tfm_suite_id) {
		this.tfmSuiteId = tfm_suite_id;
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
