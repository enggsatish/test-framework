package hpe.tfm.request.mgmt;

import java.lang.reflect.Method;

public class ClassVo {
	Class clazz;
	Method method;
	public Class getClazz() {
		return clazz;
	}
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
}
