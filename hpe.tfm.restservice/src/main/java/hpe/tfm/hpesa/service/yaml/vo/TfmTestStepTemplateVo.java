package hpe.tfm.hpesa.service.yaml.vo;

public class TfmTestStepTemplateVo {

	public TfmTestStepTemplateVo() {}
	
	private String name;
	
	private String description;
	
	private String reqtemplate;
	
	private String restemplate;
	
	private String applicationid;
	
	private String executionpriority;
	
	private InputParam inputparam;
	
	public String getReqtemplate() {
		return reqtemplate;
	}

	public void setReqtemplate(String reqtemplate) {
		this.reqtemplate = reqtemplate;
	}

	public String getRestemplate() {
		return restemplate;
	}

	public void setRestemplate(String restemplate) {
		this.restemplate = restemplate;
	}

	public String getApplicationid() {
		return applicationid;
	}

	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}

	public String getExecutionpriority() {
		return executionpriority;
	}

	public void setExecutionpriority(String executionpriority) {
		this.executionpriority = executionpriority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InputParam getInputparam() {
		return inputparam;
	}

	public void setInputparam(InputParam inputparam) {
		this.inputparam = inputparam;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
