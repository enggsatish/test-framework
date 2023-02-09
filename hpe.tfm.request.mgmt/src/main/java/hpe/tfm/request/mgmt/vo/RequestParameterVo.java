package hpe.tfm.request.mgmt.vo;

public class RequestParameterVo {
	
	private int id;
	
	private int tfmParamId;
	
	private String tfmParamKeyId;
	
	private String tfmParamValue;
	
	private int tfmRequestTemplateId;
	
	private int ruleId;
	
	private int ruleValue;
	
	private Object keyObject;
	
	public ParameterKeyVo getParameterKeyVo() {
		return new ParameterKeyVo(tfmParamId, id);
	}

//	public RequestParameterVo(int tFM_PARAM_ID, String tFM_PARAM_KEY, String tFM_PARAM_DEFAULT_VALUE,
//			int tFM_REQ_T_ID) {
//		super();
//		tfmParamId = tFM_PARAM_ID;
//		tfmParamKeyId = tFM_PARAM_KEY;
//		tfmParamValue = tFM_PARAM_DEFAULT_VALUE;
//		tfmRequestTemplateId = tFM_REQ_T_ID;
//	}

	public RequestParameterVo(int id, int tfmParamId, String tfmParamKeyId, String tfmParamValue,
			int tfmRequestTemplateId, int ruleId, int ruleValue) {
		super();
		this.id = id;
		this.tfmParamId = tfmParamId;
		this.tfmParamKeyId = tfmParamKeyId;
		this.tfmParamValue = tfmParamValue;
		this.tfmRequestTemplateId = tfmRequestTemplateId;
		this.ruleId = ruleId;
		this.ruleValue = ruleValue;
	}
	
	public int getRuleId() {
		return ruleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public int getRuleValue() {
		return ruleValue;
	}

	public void setRuleValue(int ruleValue) {
		this.ruleValue = ruleValue;
	}

	public Object getKeyObject() {
		return keyObject;
	}

	public void setKeyObject(Object keyObject) {
		this.keyObject = keyObject;
	}


	@Override
	public String toString() {
		return "RequestParameterVo [id=" + id + ", tfmParamId=" + tfmParamId + ", tfmParamKeyId=" + tfmParamKeyId
				+ ", tfmParamValue=" + tfmParamValue + ", tfmRequestTemplateId=" + tfmRequestTemplateId + ", ruleId="
				+ ruleId + ", ruleValue=" + ruleValue + ", keyObject=" + keyObject + "]";
	}

	public int getTfmParamId() {
		return tfmParamId;
	}

	public void setTfmParamId(int tFM_PARAM_ID) {
		tfmParamId = tFM_PARAM_ID;
	}

	public String getTfmParamKeyId() {
		return tfmParamKeyId;
	}

	public void setTfmParamKeyId(String tFM_PARAM_KEY) {
		tfmParamKeyId = tFM_PARAM_KEY;
	}

	public String getTfmParamValue() {
		return tfmParamValue;
	}

	public void setTfmParamValue(String tFM_PARAM_DEFAULT_VALUE) {
		tfmParamValue = tFM_PARAM_DEFAULT_VALUE;
	}

	public int getTfmRequestTemplateId() {
		return tfmRequestTemplateId;
	}

	public void setTfmRequestTemplateId(int tFM_REQ_T_ID) {
		tfmRequestTemplateId = tFM_REQ_T_ID;
	}
}
