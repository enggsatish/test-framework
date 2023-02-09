package hpe.tfm.request.yaml.converter;

public interface TfmYamlService <T> {

	public T getObjectForString (Class<? extends T> clazz, String yamlString);
	
	public String getStringForYaml(T t);
}
