package hpe.tfm.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "tfm_request_template_pt")
public class TfmRequestTemplatePt {
	
	public TfmRequestTemplatePt() {}

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tfm_req_t_id")
	private int tfmReqtId;
	
	@Column(name = "tfm_req_t_type")
	private String tfmReqtType;
	
	@Column(name = "tfm_req_t_file_path")
	private String tfmReqtFilePath;
	
	@Column(name = "tfm_req_t_value")
	private String tfmReqtValue;
	
	@OneToMany( cascade = CascadeType.ALL)
	@JoinColumn(name = "tfm_req_t_id")
	private List<TfmRequestTemplateRule> templateRule;

	public List<TfmRequestTemplateRule> getTemplateRule() {
		return templateRule;
	}

	public void setTemplateRule(List<TfmRequestTemplateRule> templateRule) {
		this.templateRule = templateRule;
	}

	public int getTfmReqtId() {
		return tfmReqtId;
	}

	public void setTfmReqtId(int tfmReqtId) {
		this.tfmReqtId = tfmReqtId;
	}

	public String getTfmReqtType() {
		return tfmReqtType;
	}

	public void setTfmReqtType(String tfmReqtType) {
		this.tfmReqtType = tfmReqtType;
	}

	public String getTfmReqtFilePath() {
		return tfmReqtFilePath;
	}

	public void setTfmReqtFilePath(String tfmReqtFilePath) {
		this.tfmReqtFilePath = tfmReqtFilePath;
	}

	public String getTfmReqtValue() {
		return tfmReqtValue;
	}

	public void setTfmReqtValue(String tfmReqtValue) {
		this.tfmReqtValue = tfmReqtValue;
	}

	@Override
	public String toString() {
		return "TFM_REQUEST_TEMPLATE_PT [tfm_req_t_id=" + tfmReqtId + ", tfm_req_t_type=" + tfmReqtType
				+ ", tfm_req_t_file_path=" + tfmReqtFilePath + ", tfm_req_t_value=" + tfmReqtValue + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tfmReqtFilePath == null) ? 0 : tfmReqtFilePath.hashCode());
		result = prime * result + (int) (tfmReqtId ^ (tfmReqtId >>> 32));
		result = prime * result + ((tfmReqtType == null) ? 0 : tfmReqtType.hashCode());
		result = prime * result + ((tfmReqtValue == null) ? 0 : tfmReqtValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TfmRequestTemplatePt other = (TfmRequestTemplatePt) obj;
		if (tfmReqtFilePath == null) {
			if (other.tfmReqtFilePath != null)
				return false;
		} else if (!tfmReqtFilePath.equals(other.tfmReqtFilePath))
			return false;
		if (tfmReqtId != other.tfmReqtId)
			return false;
		if (tfmReqtType == null) {
			if (other.tfmReqtType != null)
				return false;
		} else if (!tfmReqtType.equals(other.tfmReqtType))
			return false;
		if (tfmReqtValue == null) {
			if (other.tfmReqtValue != null)
				return false;
		} else if (!tfmReqtValue.equals(other.tfmReqtValue))
			return false;
		return true;
	}
}
