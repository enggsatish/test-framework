package hpe.tfm.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "tfm_test_step_template")
public class TfmTestStepTemplate {
	
	public TfmTestStepTemplate() {}
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "tfm_teststep_template_id")
	private String tfmTestStepTemplateId;
	
	@Column(name = "tfm_teststep_template_description")
	private String tfmTestStepTemplateDesc;
	
	@Column(name = "tfm_teststep_template_value")
	private String tfmTestStepTemplateValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTfmTestStepTemplateId() {
		return tfmTestStepTemplateId;
	}

	public void setTfmTestStepTemplateId(String tfmTestStepTemplateId) {
		this.tfmTestStepTemplateId = tfmTestStepTemplateId;
	}

	public String getTfmTestStepTemplateDesc() {
		return tfmTestStepTemplateDesc;
	}

	public void setTfmTestStepTemplateDesc(String tfmTestStepTemplateDesc) {
		this.tfmTestStepTemplateDesc = tfmTestStepTemplateDesc;
	}

	public String getTfmTestStepTemplateValue() {
		return tfmTestStepTemplateValue;
	}

	public void setTfmTestStepTemplateValue(String tfmTestStepTemplateValue) {
		this.tfmTestStepTemplateValue = tfmTestStepTemplateValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((tfmTestStepTemplateDesc == null) ? 0 : tfmTestStepTemplateDesc.hashCode());
		result = prime * result + ((tfmTestStepTemplateId == null) ? 0 : tfmTestStepTemplateId.hashCode());
		result = prime * result + ((tfmTestStepTemplateValue == null) ? 0 : tfmTestStepTemplateValue.hashCode());
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
		TfmTestStepTemplate other = (TfmTestStepTemplate) obj;
		if (id != other.id)
			return false;
		if (tfmTestStepTemplateDesc == null) {
			if (other.tfmTestStepTemplateDesc != null)
				return false;
		} else if (!tfmTestStepTemplateDesc.equals(other.tfmTestStepTemplateDesc))
			return false;
		if (tfmTestStepTemplateId == null) {
			if (other.tfmTestStepTemplateId != null)
				return false;
		} else if (!tfmTestStepTemplateId.equals(other.tfmTestStepTemplateId))
			return false;
		if (tfmTestStepTemplateValue == null) {
			if (other.tfmTestStepTemplateValue != null)
				return false;
		} else if (!tfmTestStepTemplateValue.equals(other.tfmTestStepTemplateValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TfmTestStepTemplate [id=" + id + ", tfmTestStepTemplateId=" + tfmTestStepTemplateId
				+ ", tfmTestStepTemplateDesc=" + tfmTestStepTemplateDesc + ", tfmTestStepTemplateValue="
				+ tfmTestStepTemplateValue + "]";
	}
}
