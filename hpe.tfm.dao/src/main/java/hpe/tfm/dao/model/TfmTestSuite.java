package hpe.tfm.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tfm_test_suite")
public class TfmTestSuite {

	public TfmTestSuite(){}
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
		
	@Column(name = "tfm_suite_name")
	private String tfmSuiteName;

	@Column(name = "tfm_suite_description")
	private String tfmSuiteDescription;

	@Column(name = "tfm_suite_value")
	private String tfmSuiteValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTfmSuiteName() {
		return tfmSuiteName;
	}

	public void setTfmSuiteName(String tfmSuiteName) {
		this.tfmSuiteName = tfmSuiteName;
	}

	public String getTfmSuiteDescription() {
		return tfmSuiteDescription;
	}

	public void setTfmSuiteDescription(String tfmSuiteDescription) {
		this.tfmSuiteDescription = tfmSuiteDescription;
	}

	public String getTfmSuiteValue() {
		return tfmSuiteValue;
	}

	public void setTfmSuiteValue(String tfmSuiteValue) {
		this.tfmSuiteValue = tfmSuiteValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((tfmSuiteDescription == null) ? 0 : tfmSuiteDescription.hashCode());
		result = prime * result + ((tfmSuiteName == null) ? 0 : tfmSuiteName.hashCode());
		result = prime * result + ((tfmSuiteValue == null) ? 0 : tfmSuiteValue.hashCode());
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
		TfmTestSuite other = (TfmTestSuite) obj;
		if (id != other.id)
			return false;
		if (tfmSuiteDescription == null) {
			if (other.tfmSuiteDescription != null)
				return false;
		} else if (!tfmSuiteDescription.equals(other.tfmSuiteDescription))
			return false;
		if (tfmSuiteName == null) {
			if (other.tfmSuiteName != null)
				return false;
		} else if (!tfmSuiteName.equals(other.tfmSuiteName))
			return false;
		if (tfmSuiteValue == null) {
			if (other.tfmSuiteValue != null)
				return false;
		} else if (!tfmSuiteValue.equals(other.tfmSuiteValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TfmTestSuite [id=" + id + ", tfmSuiteName=" + tfmSuiteName + ", tfmSuiteDescription=" + tfmSuiteDescription
				+ ", tfmSuiteValue=" + tfmSuiteValue + "]";
	}
	
}
