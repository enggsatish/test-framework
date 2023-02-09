package hpe.tfm.request.mgmt;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {

	public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException {
		//ArrayList<String> clssss = getAllObjectFactory("hpe.tfm.generated");
		//System.out.println(clssss);
		//System.out.println(findClass(clssss.get(0), "Header"));
	}
	
	public static ArrayList<String> getAllObjectFactory(String packageName) throws IOException, URISyntaxException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL packageURL;
		ArrayList<String> names = new ArrayList<String>();
		String packageNameWithpipe = packageName.replace(".", "/");
		packageURL = classLoader.getResource(packageNameWithpipe);
		if (packageURL==null) {
			return null;
		}
		URI uri = new URI(packageURL.toString());
		File folder = new File(uri.getPath());
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

		return names;
	}
	
	public static ClassVo findObjectClassWithMethod(final List<String> packageName, final String methodName) throws IOException, URISyntaxException {
		ClassVo classVo = null;
		for (String str : packageName) {
			classVo = findClass(str, methodName);
			if (null != classVo) {				
				return classVo;
			}
		}
		return classVo;
	}
	
	private static ClassVo findClass(String className, String methodName) {
		try {
			ClassVo classVo = null;
			Class clazzz = Class.forName(className);			
			Method[] methods = clazzz.getMethods();
			for (Method mthd : methods) {
				if (mthd.getName().startsWith("create") && mthd.getName().endsWith(methodName)) {
					classVo = new ClassVo() {{
						setClazz(clazzz);
						setMethod(mthd);
					}};
					return classVo;
				}
			}
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
