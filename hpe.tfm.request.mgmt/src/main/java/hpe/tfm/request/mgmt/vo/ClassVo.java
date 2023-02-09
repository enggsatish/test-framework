package hpe.tfm.request.mgmt.vo;

import java.lang.reflect.Method;

public class ClassVo {
	
	Class clazz;
	
	Object object;
	
	Method method;

	public ClassVo(Class clazz, Method method) {
		super();
		this.clazz = clazz;
		this.method = method;
	}

	public ClassVo(Object object, Method method) {
		super();
		this.object = object;
		this.method = method;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

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

	@Override
	public String toString() {
		return "ClassVo [clazz=" + clazz + ", method=" + method + "]";
	}
}
