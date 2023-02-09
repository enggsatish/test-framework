package hpe.tfm.request.mgmt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import hpe.tfm.request.TfmXmlRequestMarshaller;
import hpe.tfm.request.mgmt.util.TfmXmlRequestMarshallerWrapper;

public class ParamToXmlGenerateTest {
	
	static Connection conn = null;
	static Statement statement = null;
    static ResultSet resultSet = null;
	    
    public static void main(String[] args) {
    	ParamToXmlGenerateTest test = new ParamToXmlGenerateTest();
		try {
			TfmXmlRequestMarshaller tfmXmlRequestMarshaller = new TfmXmlRequestMarshaller();
			Jaxb2Marshaller marshaller = tfmXmlRequestMarshaller.jaxb2Marshaller();
			marshaller.setPackagesToScan("hpe.tfm.generated.schema0", "hpe.tfm.generated.schema1", "hpe.tfm.generated.schema2", "hpe.tfm.generated.schema3", "hpe.tfm.generated.schema4");
			TfmXmlRequestMarshallerWrapper marshallerWrapper = new TfmXmlRequestMarshallerWrapper();
			marshallerWrapper.setMarshaller(marshaller);
			//read all the request param and Rules
			Map<Integer, ParamPt> paramMap = test.readParamPt();
			List<ParamRule> paramRule = test.readParamRule();
			//get list of object factory
			ArrayList<String> objecFactorytList = TestUtil.getAllObjectFactory("hpe.tfm.generated");
			//generate list of child per parent.
			Map<Integer, List<ParamRule>> childRuleMap = paramRule.stream().collect(Collectors.groupingBy(ParamRule::getParentId)); 
			
			ParamRule rootRule = test.findRoot (paramMap, paramRule); // find top root parent mapping.-- (1, 1, 14);
			ParamPt rootParam = paramMap.get(rootRule.getParentId()); // get details of 14 from parampt.			
			Object rootObj = test.generateClass(rootParam.getKey(), objecFactorytList); // create object of 14
			
			//System.out.println(marshallerWrapper.marshallXml(rootObj));
			
			test.generateTree(rootObj, rootParam, paramMap, childRuleMap, objecFactorytList, paramRule);
			
			System.out.println(marshallerWrapper.marshallXml(rootObj));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
    
    private Object getObjectFromArray(Object object, String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	for (Method method : object.getClass().getDeclaredMethods()) {
    		if (method.getName().equalsIgnoreCase(name)) {
    			if (method.getReturnType().equals(List.class)) {
    				List arrayObjectInstance = (List) method.invoke(object);
    				return arrayObjectInstance.get(0);
    			} else {
    				return method.invoke(object);
    			}
    		}
    	}
    	return null;
    }
    
	private void generateTree(Object rootObj, final ParamPt rootParam, Map<Integer, ParamPt> paramMap,
			Map<Integer, List<ParamRule>> childRuleMap, ArrayList<String> objecFactorytList, List<ParamRule> paramRule)
			throws Exception {
    	List<ParamRule> childList = childRuleMap.get(rootParam.getId()); // get list of child of the given parentid.
		if (childList != null && childList.size() > 0) { // if child exists
			for (ParamRule rule : childList) {
				System.out.println(rule);
				//get child node here
				ParamPt paramPt = paramMap.get(rule.getParamId());
				System.out.println(paramPt);
				String methodNameToLookFor = paramPt.getKey();
				if (paramPt.getKey().contains("/")) {
					methodNameToLookFor = paramPt.getKey().split("/")[0];
				}
				//generate object of child node, this can again be a single line tree of childs
				Object childObj = generateClass(paramPt.getKey(), objecFactorytList);

				// get relative path for the current object to attach the newly created object.
				// iterate through each object and method to find the parent object and attach object.
				String completePath = getCompletePathUntilParent(rule.getParamId(), paramRule, paramMap);
				System.out.println(completePath);
				Object lastChildObject = rootObj;
				if (completePath.contains("/")) {
					String[] strArr = completePath.split("/");
					for (int i = 2; i < strArr.length ; i++) { // start from second level
						lastChildObject = getObjectFromArray(lastChildObject, "get" + strArr[i]);
//						lastChildObject = lastChildObject.getClass().getDeclaredMethod("get" + strArr[i]).invoke(lastChildObject);
//						System.err.println(i + "::" + (lastChildObject instanceof ServiceOrder));
						try {
							boolean match = false;
							if (null == lastChildObject) {
								System.err.println("lastChildObject null " + "get" + strArr[i]);break;
							}
							for (Method method : lastChildObject.getClass().getDeclaredMethods()) {
								// if no object then possibly an attribute
								System.out.println(method.getName() + "::::" + "set" + methodNameToLookFor);
								if (null == childObj) {
									if (method.getName().equalsIgnoreCase("set" + methodNameToLookFor)) {
										method.invoke(lastChildObject, paramPt.getValue());
										match = true; break;
									}
								} else {
									if (method.getName().equalsIgnoreCase("set" + methodNameToLookFor)) {
										// entry found set child object
										lastChildObject = method.invoke(lastChildObject, childObj);
										match = true; break;
									}
								}
							}
							if (match) 
								break;
							else {
								// entry not found see if entry is in form of arraylist.
								for (Method method : lastChildObject.getClass().getDeclaredMethods()) {
									if (method.getReturnType().equals(List.class)) {
										if (method.getName().equalsIgnoreCase("get" + methodNameToLookFor)) {
											List arrayObjectInstance = (List) method.invoke(lastChildObject);
											arrayObjectInstance.add(childObj);
											break;
										}
									}
								}
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					lastChildObject = rootObj;
					if (null == childObj) {
						boolean match = false;
						for (Method method : lastChildObject.getClass().getDeclaredMethods()) {
							match = method.getName().equalsIgnoreCase("set" + methodNameToLookFor);
							if (match) {
								method.invoke(lastChildObject, paramPt.getValue());
								break;
							}
						}
						//later to be added if child are array of value.
//						if (!match) {
//							System.err.println(lastChildObject + "::" + childObj);
//							// no setter method found, check for arraylist.
//							for (Method method : lastChildObject.getClass().getDeclaredMethods()) {
//								boolean foundGetter = method.getName().equalsIgnoreCase("get" + paramPt.getKey());
//								if (foundGetter) {
//									if (method.getReturnType().equals(List.class)) {
//										List arrayObjectInstance = (List) method.invoke(lastChildObject);
//										arrayObjectInstance.add(paramPt.getValue());
//										break;
//									}
//								}
//							}
//						}
					} else {
						boolean match = false;
						for (Method method : lastChildObject.getClass().getDeclaredMethods()) {
							match = method.getName().equalsIgnoreCase("set" + methodNameToLookFor);
							if (match) {
								method.invoke(lastChildObject, childObj);
								break;
							}
						}
						if (!match) {
							System.err.println(lastChildObject + "::" + childObj);
							// no setter method found, check for arraylist.
							for (Method method : lastChildObject.getClass().getDeclaredMethods()) {
								boolean foundGetter = method.getName().equalsIgnoreCase("get" + methodNameToLookFor);
								if (foundGetter) {
									if (method.getReturnType().equals(List.class)) {
										List arrayObjectInstance = (List) method.invoke(lastChildObject);
										arrayObjectInstance.add(childObj);
										break;
									}
								}
							}
						}
					}
				}
				generateTree(rootObj, paramPt, paramMap, childRuleMap, objecFactorytList, paramRule);
			}
		} 
//    	ruleMap.entrySet().stream().forEach(t -> t.get);
    	
    }
    
    private String getCompletePathUntilParent(int key, List<ParamRule> paramRule, Map<Integer, ParamPt> paramMap) {
    	String completePath = "";
    	for (ParamRule rule : paramRule) {
    		if (rule.getParamId() == key) {
    			completePath = paramMap.get(rule.getParentId()).getKey();
    			return getCompletePathUntilParent(rule.getParentId(), paramRule, paramMap) + "/" + completePath;
    		}  			
    	}
    	return completePath;
    }
    
    private Object generateClass(String key, ArrayList<String> objecFactorytList) throws Exception {
    	Object rootObj = null;
    	if (key.contains("/")) {
    		String [] strArr = key.split("/");
    		Object rootObjTemp = null;
    		for (int i = 0 ; i < strArr.length ; i++) { // for each entity name
    			ClassVo classVo = TestUtil.findObjectClassWithMethod(objecFactorytList, strArr[i]);
    			// if last key is attribute then send the parent object.
    			// to avoid scenario like CharacteristicValue/Value while finding the class
    			if (null == classVo || (i > 0 && strArr[i-1].endsWith(strArr[i]) && !classVo.getMethod().getName().endsWith(strArr[i-1]+strArr[i])) ) {
    				return rootObj;
    			}
    			Object objectFactoryInstance = classVo.getClazz().newInstance();
    			Object entityInstance = classVo.getMethod().invoke(objectFactoryInstance);
    			if (rootObjTemp != null) {
    				boolean match = false;
    				for (Method mthd : rootObjTemp.getClass().getDeclaredMethods()) {
    					if (mthd.getName().contains("set" + strArr[i])) {
    						mthd.invoke(rootObjTemp, entityInstance);
    						match = true; break;
    					}
    				}
    				if (!match) {
    					// try if it is of array list
    					for (Method mthd : rootObjTemp.getClass().getDeclaredMethods()) {
	    					if (mthd.getReturnType().equals(List.class)) {
								List arrayObjectInstance = (List) mthd.invoke(rootObjTemp);
								arrayObjectInstance.add(entityInstance);
								break;
							}
    					}
    				}
    			}
    			if (i == 0) {
    				rootObj = entityInstance;
    			} 
    			rootObjTemp = entityInstance;
    		}
    	} else {
    		ClassVo classVo = TestUtil.findObjectClassWithMethod(objecFactorytList, key);
    		if (null == classVo) return null;
    		Object instance = classVo.getClazz().newInstance();
    		rootObj = classVo.getMethod().invoke(instance);
    	}
    	return rootObj;
    }
    
    private ParamRule findRoot(Map<Integer, ParamPt> paramMap, List<ParamRule> paramRule) {
    	ParamRule rl = findParent (paramMap.keySet().stream().findFirst().get(), paramRule);
//    	System.out.println(rl.getParentId());
//    	System.out.println(rl.getParamId());
    	return rl;
    }
    
    private ParamRule findParent(int key, List<ParamRule> paramRule) {
//    	System.out.println("key->" + key);
    	ParamRule parent = null;
    	for (ParamRule rule : paramRule) {
    		if (rule.getParamId() == key) {
    			return findParent(rule.getParentId(), paramRule);
    		} else {
    			parent = rule;
    		}   			
    	}
    	return parent;
    }
    	
	private Map<Integer, ParamPt> readParamPt() throws SQLException {
		ResultSet resultSet = statement.executeQuery("select * from tfm.TFM_PARAM_PT");
		Map<Integer, ParamPt> paramMap = new HashMap<Integer, ParamToXmlGenerateTest.ParamPt>();
		while (resultSet.next()) {
			int id = resultSet.getInt("TFM_PARAM_ID");
			paramMap.put(id, new ParamPt(id, resultSet.getString("TFM_PARAM_KEY"), resultSet.getString("TFM_PARAM_DEFAULT_VALUE")));
		}
		return paramMap;
	}
	
	private List<ParamRule> readParamRule() throws SQLException{
		ResultSet resultSet = statement.executeQuery("select * from tfm.TFM_PARAM_RULE_MAP");
		List<ParamRule> paramRule = new ArrayList<ParamToXmlGenerateTest.ParamRule>();
		while (resultSet.next()) {
			paramRule.add(new ParamRule(resultSet.getInt("TFM_PARAM_ID"), resultSet.getInt("TFM_PARAM_RULE_ID"), resultSet.getInt("TFM_PARAM_RULE_VALUE")));
		}
		return paramRule;
	}
	
	private static void close() {
        try {
            if (resultSet != null) {resultSet.close();}
            if (statement != null) {statement.close();}
            if (conn != null) {conn.close();}
        } catch (Exception e) {

        }
    }
	
	class ParamRule {
		int paramId;
		int ruleId;
		int parentId;
		public ParamRule(int paramId, int ruleId, int parentId) {
			super();
			this.paramId = paramId;
			this.ruleId = ruleId;
			this.parentId = parentId;
		}
		@Override
		public String toString() {
			return "ParamRule [paramId=" + paramId + ", ruleId=" + ruleId + ", parentId=" + parentId + "]";
		}
		public int getParamId() {
			return paramId;
		}
		public void setParamId(int paramId) {
			this.paramId = paramId;
		}
		public int getRuleId() {
			return ruleId;
		}
		public void setRuleId(int ruleId) {
			this.ruleId = ruleId;
		}
		public int getParentId() {
			return parentId;
		}
		public void setParentId(int parentId) {
			this.parentId = parentId;
		}
	}
	class ParamPt {
		int id;
		String key;
		String value;
		@Override
		public String toString() {
			return "ParamPt [id=" + id + ", key=" + key + ", value=" + value + "]";
		}
		public ParamPt(int id, String key, String value) {
			super();
			this.id = id;
			this.key = key;
			this.value = value;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	private static void mysqlConnection() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tfm?user=tfm&password=Tfm$062020");
		    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tfm", "root", "Tfm$062020");
		    statement = conn.createStatement();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

    static {
    	try {
		mysqlConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
