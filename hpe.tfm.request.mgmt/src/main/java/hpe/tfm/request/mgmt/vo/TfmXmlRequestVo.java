package hpe.tfm.request.mgmt.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TfmXmlRequestVo {
	
	private Map<ParameterKeyVo, RequestParameterVo> paramMap;
	
	List<RequestParameterRuleVo> paramRule;
	
	List<String> objecFactorytList;
	
	Map<ParameterKeyVo, List<RequestParameterRuleVo>> childRuleMap;
	
	RequestParameterRuleVo rootRule;
	
	public RequestParameterRuleVo getRootRule() {
		return rootRule;
	}
	public void setRootRule(RequestParameterRuleVo rootRule) {
		this.rootRule = rootRule;
	}
	
	/**
	 * return the matching child.
	 * 
	 * @param ruleParam
	 * @return
	 */
	public RequestParameterVo findRequestParamVoForRule(final RequestParameterRuleVo ruleParam) {
		Optional<Map.Entry<ParameterKeyVo,RequestParameterVo>> rule = paramMap.entrySet().stream()
				.filter(t -> ruleParam.getTfmChildParamMapId() == t.getValue().getId())
				.filter(t -> t.getValue().getTfmParamId() == ruleParam.getTfmParamRuleValue()).findFirst();
		if (rule.isPresent()) {
			return rule.get().getValue();
		}
		return null;
	}
	
	/**
	 * returns First rule in the chain.
	 * 
	 * @return
	 */
	public RequestParameterRuleVo findAndStoreRootRule() {
		setRootRule(findParent (paramMap.keySet().stream().findFirst().get(), paramRule));
		return rootRule;
	}
	//1 = XML_PARENT_RULE
	//2 = XML_CHILD_RULE
	//3 = XML_ATTRIBUTE_RULE
	 private RequestParameterRuleVo findParent(ParameterKeyVo keyVo, List<RequestParameterRuleVo> paramRule) {
		 RequestParameterRuleVo parent = null;
	    	for (RequestParameterRuleVo rule : paramRule) {
	    		if (rule.getTfmParamRuleValue() == 0 ) {
	    			return rule;
	    		}
	    		if (rule.getTfmParamId() == keyVo.getParamKeyId()) {
	    			return findParent(new ParameterKeyVo(keyVo.getParamKeyId(), rule.getTfmParamRuleValue()), paramRule);
	    		} else {
	    			parent = rule;
	    			if (paramMap.get(keyVo).getRuleValue() == 0)
	    				return parent;
	    		}   			
	    	}
	    	return parent;
	    }

	public Map<ParameterKeyVo, RequestParameterVo> getParamMap() {
		if (paramMap == null) paramMap = new HashMap<ParameterKeyVo, RequestParameterVo>();
		return paramMap;
	}

	public void setParamMap(Map<ParameterKeyVo, RequestParameterVo> paramMap) {
		this.paramMap = paramMap;
	}

	public List<RequestParameterRuleVo> getParamRule() {
		if (paramRule == null) paramRule = new ArrayList<RequestParameterRuleVo>();
		return paramRule;
	}

	public void setParamRule(List<RequestParameterRuleVo> paramRule) {
		this.paramRule = paramRule;
	}

	public List<String> getObjecFactorytList() {
		return objecFactorytList;
	}

	public void setObjecFactorytList(List<String> objecFactorytList) {
		this.objecFactorytList = objecFactorytList;
	}

	public Map<ParameterKeyVo, List<RequestParameterRuleVo>> getChildRuleMap() {
		if (childRuleMap == null) childRuleMap = new HashMap<ParameterKeyVo, List<RequestParameterRuleVo>>();
		return childRuleMap;
	}

	public void setChildRuleMap(Map<ParameterKeyVo, List<RequestParameterRuleVo>> childRuleMap) {
		this.childRuleMap = childRuleMap;
	}
}
