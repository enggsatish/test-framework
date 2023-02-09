package hpe.tfm.request.mgmt.vo;

public class ParameterKeyVo {
	
	private int paramKeyId;
	
	private int mapId;

	public ParameterKeyVo(int paramKeyId, int mapId) {
		super();
		this.paramKeyId = paramKeyId;
		this.mapId = mapId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + paramKeyId;
		result = prime * result + mapId;
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
		ParameterKeyVo other = (ParameterKeyVo) obj;
		if (paramKeyId != other.paramKeyId)
			return false;
		if (mapId != other.mapId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ParameterKeyVo [paramKeyId=" + paramKeyId + ", mapId=" + mapId + "]";
	}

	public int getParamKeyId() {
		return paramKeyId;
	}

	public void setParamKeyId(int paramKeyId) {
		this.paramKeyId = paramKeyId;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int stepParamMapId) {
		this.mapId = stepParamMapId;
	}
}
