package hpe.tfm.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "tfm_test_case")
public class TfmTestCase {
	
	public TfmTestCase() {}

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "tfm_s_tc_name")
	private String tfmSTcName;
	
	@Column(name = "tfm_s_tc_description")
	private String tfmSTcDescription;
	
	@Column(name = "tfm_s_tc_value")
	private String tfmSTcValue;
	
	@Column(name = "tfm_suite_id")
	private int tfmSuiteId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTfmSTcName() {
		return tfmSTcName;
	}

	public void setTfmSTcName(String tfm_s_tc_name) {
		this.tfmSTcName = tfm_s_tc_name;
	}

	public String getTfmSTcDescription() {
		return tfmSTcDescription;
	}

	public void setTfmSTcDescription(String tfm_s_tc_description) {
		this.tfmSTcDescription = tfm_s_tc_description;
	}

	public String getTfmSTcValue() {
		return tfmSTcValue;
	}

	public void setTfmSTcValue(String tfm_s_tc_value) {
		this.tfmSTcValue = tfm_s_tc_value;
	}

	public int getTfmSuiteId() {
		return tfmSuiteId;
	}

	public void setTfmSuiteId(int tfm_suite_id) {
		this.tfmSuiteId = tfm_suite_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((tfmSTcDescription == null) ? 0 : tfmSTcDescription.hashCode());
		result = prime * result + ((tfmSTcName == null) ? 0 : tfmSTcName.hashCode());
		result = prime * result + ((tfmSTcValue == null) ? 0 : tfmSTcValue.hashCode());
		result = prime * result + tfmSuiteId;
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
		TfmTestCase other = (TfmTestCase) obj;
		if (id != other.id)
			return false;
		if (tfmSTcDescription == null) {
			if (other.tfmSTcDescription != null)
				return false;
		} else if (!tfmSTcDescription.equals(other.tfmSTcDescription))
			return false;
		if (tfmSTcName == null) {
			if (other.tfmSTcName != null)
				return false;
		} else if (!tfmSTcName.equals(other.tfmSTcName))
			return false;
		if (tfmSTcValue == null) {
			if (other.tfmSTcValue != null)
				return false;
		} else if (!tfmSTcValue.equals(other.tfmSTcValue))
			return false;
		if (tfmSuiteId != other.tfmSuiteId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TfmTestCase [id=" + id + ", tfmSTcName=" + tfmSTcName + ", tfmSTcDescription=" + tfmSTcDescription
				+ ", tfmSTcValue=" + tfmSTcValue + ", tfmSuiteId=" + tfmSuiteId + "]";
	}
}
