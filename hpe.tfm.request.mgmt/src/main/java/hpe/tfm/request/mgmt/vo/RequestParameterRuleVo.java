package hpe.tfm.request.mgmt.vo;

public class RequestParameterRuleVo {
	
	private int tfmParamId;
	
	private int tfmParamRuleId;
	
	private int tfmParamRuleValue;
	
	private int tfmParentParamMapId;
	
	private int tfmChildParamMapId;
	
	public ParameterKeyVo getParameterKeyVo() {
		return new ParameterKeyVo(tfmParamId, tfmParentParamMapId);
	}

	public int getTfmParentParamMapId() {
		return tfmParentParamMapId;
	}

	public int getTfmChildParamMapId() {
		return tfmChildParamMapId;
	}

	public void setTfmChildParamMapId(int tfmChildParamMapId) {
		this.tfmChildParamMapId = tfmChildParamMapId;
	}

	public void setTfmParentParamMapId(int tfmStepParamMapId) {
		this.tfmParentParamMapId = tfmStepParamMapId;
	}

	public RequestParameterRuleVo(int tfmParamId, int tfmParamRuleId, int tfmParamRuleValue, int tfmParentParamMapId,
			int tfmChildParamMapId) {
		super();
		this.tfmParamId = tfmParamId;
		this.tfmParamRuleId = tfmParamRuleId;
		this.tfmParamRuleValue = tfmParamRuleValue;
		this.tfmParentParamMapId = tfmParentParamMapId;
		this.tfmChildParamMapId = tfmChildParamMapId;
	}

	public int getTfmParamId() {
		return tfmParamId;
	}

	public void setTfmParamId(int tFM_PARAM_ID) {
		tfmParamId = tFM_PARAM_ID;
	}

	public int getTfmParamRuleId() {
		return tfmParamRuleId;
	}

	public void setTfmParamRuleId(int tFM_PARAM_RULE_ID) {
		tfmParamRuleId = tFM_PARAM_RULE_ID;
	}

	public int getTfmParamRuleValue() {
		return tfmParamRuleValue;
	}

	public void setTfmParamRuleValue(int tFM_PARAM_RULE_VALUE) {
		tfmParamRuleValue = tFM_PARAM_RULE_VALUE;
	}

	@Override
	public String toString() {
		return "RequestParameterRuleVo [tfmParamId=" + tfmParamId + ", tfmParamRuleId=" + tfmParamRuleId
				+ ", tfmParamRuleValue=" + tfmParamRuleValue + ", tfmParentParamMapId=" + tfmParentParamMapId
				+ ", tfmChildParamMapId=" + tfmChildParamMapId + "]";
	}

}
