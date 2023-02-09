package hpe.tfm.request.mgmt.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hpe.tfm.request.mgmt.vo.ClassVo;

public final class TfmClazzzUtil {
	
	private TfmClazzzUtil() {}

	private static final String set = "set";
	private static final String splitregex = "/"; 
	private static final String create = "create";
	
	private static final Logger log = LoggerFactory.getLogger(TfmClazzzUtil.class.getSimpleName());
	
	static ClassLoader classLoader = TfmClazzzUtil.class.getClassLoader();
	
	public static List<String> getAllObjectFactory(String packageName) throws IOException, URISyntaxException {
		URL packageURL;
		ArrayList<String> names = new ArrayList<String>();
		String packageNameWithpipe = packageName.replace(".", splitregex);
		packageURL = classLoader.getResource(packageNameWithpipe);
		if (packageURL==null) {
			return null;
		}
		try {
			URI uri = new URI(packageURL.toString());
			File folder = new File(uri.getPath().replaceAll("!.*$", ""));
			File[] contenuti = folder.listFiles();
			String entryName;
			for (File actual : contenuti) {
				entryName = actual.getName();
				if (actual.isDirectory()) {
					names.addAll(getAllObjectFactory(packageName+"."+ entryName));				
				} else {
					entryName = entryName.substring(0, entryName.lastIndexOf('.'));
					if (entryName.contains("ObjectFactory"))
						names.add(packageName + "." + entryName);
				}
			}
		} catch (Exception e) {
			log.debug("SSSSSSSSSSSS " + packageURL.getPath());
			String[] str = {"xsd.generated.schema0.ObjectFactory",
					"xsd.generated.schema1.ObjectFactory",
					"xsd.generated.schema2.ObjectFactory",
					"xsd.generated.schema3.ObjectFactory",
					"xsd.generated.schema4.ObjectFactory"};
			//System.out.println("str>>>>> " + str);
			return Arrays.asList(str);
		}
		return names;
	}
	
	public static ClassVo findObjectClassWithMethod(final List<String> packageName, final String methodName) throws IOException, URISyntaxException {
		ClassVo classVo = null;
		for (String str : packageName) {
			//System.err.println("className>>>>" + str);
			classVo = findClass(str, methodName);
			if (null != classVo) {				
				return classVo;
			}
		}
		return classVo;
	}
	
	private static Method findSetMethodByName(Object obj, String methodName) {
		for (Method method : obj.getClass().getDeclaredMethods()) {
			if (method.getName().equals(set + methodName)) {
				return method;
			}
		}
		return null;
	}
	
	private static ClassVo findClass(String className, String methodName) {
		try {
			ClassVo classVo = null;
			Class clazzz = Class.forName(className);			
			Method[] methods = clazzz.getMethods();
			for (Method mthd : methods) {
				if (mthd.getName().startsWith(create) && mthd.getName().endsWith(methodName)) {
					classVo = new ClassVo(clazzz, mthd);
					return classVo;
				}
			}
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object getObjectFromArray(Object object, String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
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
	/**
	 * Parse and get the last possible node object in the xpath, below case object of Message
	 * ex: Message/Body/ServiceActivationRequest/ServiceOrder
	 * 
	 * @param key
	 * @param value
	 * @param objecFactorytList
	 * @return
	 * @throws Exception
	 */
	public static Object generateClassAndGetRootObjectOfKey(String key, String value, List<String> objecFactorytList) throws Exception {
		return generateClass(key, value, objecFactorytList, true);
	}
	
	/**
	 * Parse and get the last possible node object in the xpath, below case object of ServiceOrder
	 *  ex: Message/Body/ServiceActivationRequest/ServiceOrder
	 * 
	 * @param key
	 * @param value
	 * @param objecFactorytList
	 * @return
	 * @throws Exception
	 */
	public static Object generateClassAndGetLeafObjectOfKey(String key, String value, List<String> objecFactorytList) throws Exception {
		return generateClass(key, value, objecFactorytList, false);
	}
    
	/**
	 * 
	 * @param key
	 * @param value
	 * @param objecFactorytList
	 * @param needRoot
	 * @return
	 * @throws Exception
	 */
	private static Object generateClass(String key, String value, List<String> objecFactorytList, final boolean needRoot) throws Exception {
    	Object rootObj = null;
    	Object rootObjTemp = null;
    	if (key.contains(splitregex)) {
    		String [] strArr = key.split(splitregex);
    		for (int i = 0 ; i < strArr.length ; i++) { // for each entity name
    			ClassVo classVo = findObjectClassWithMethod(objecFactorytList, strArr[i]);
    			// if last key is attribute then send the parent object.
    			// to avoid scenario like CharacteristicValue/Value while finding the class
    			if (null == classVo || (i > 0 && strArr[i-1].endsWith(strArr[i]) && !classVo.getMethod().getName().endsWith(strArr[i-1]+strArr[i])) ) {
    				if (value != null && !value.equals("")) {
	    				Method method = findSetMethodByName(rootObjTemp, strArr[i]);
    					invokeMethod(rootObjTemp, method, value);
    				}
    				break;
    			}
    			Object objectFactoryInstance = classVo.getClazz().newInstance();
    			Object entityInstance = null;
    			if (classVo.getMethod().getParameterCount() == 1) {
    				entityInstance = classVo.getMethod().invoke(objectFactoryInstance, value);
    			} else 
    			entityInstance = classVo.getMethod().invoke(objectFactoryInstance);
    			if (rootObjTemp != null) {
    				boolean match = false;
    				for (Method mthd : rootObjTemp.getClass().getDeclaredMethods()) {
    					if (mthd.getName().contains("set" + strArr[i])) {
    						try {
    							mthd.invoke(rootObjTemp, entityInstance);
    						} catch (IllegalArgumentException iae) {
    							log.info(iae.getMessage() + ":: set object may not be needed, try setting value directly.");
    							invokeMethod(rootObjTemp, mthd, value);
    						}
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
    		ClassVo classVo = findObjectClassWithMethod(objecFactorytList, key);
    		if (null == classVo) return null;
    		Object instance = classVo.getClazz().newInstance();
    		rootObjTemp = rootObj = invokeMethod(instance, classVo.getMethod(), value);
    	}
    	return (needRoot) ? rootObj : rootObjTemp;
    }
	
	public static Object invokeMethod(Object obj, Method method, String value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (method.getParameterCount() == 1) {
			String methodTypeName = method.getGenericParameterTypes()[0].getTypeName();
			if (methodTypeName.contains("BigInteger"))
				return method.invoke(obj, new BigInteger(value));
			else if (methodTypeName.contains("int")) 
				return method.invoke(obj, Integer.parseInt(value));
			else if (methodTypeName.contains("long"))
				return method.invoke(obj, Long.parseLong(value));
			else
				return method.invoke(obj, value);
		} else {
			return method.invoke(obj);
		}
	}
}
