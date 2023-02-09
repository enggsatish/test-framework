package hpe.tfm.request.yaml.converter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.yaml.snakeyaml.Yaml;

public class YamlHttpMessageConverter<T> extends AbstractHttpMessageConverter<T> implements TfmYamlService <T> {

	public YamlHttpMessageConverter() {
		super(new MediaType("text", "yaml"));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	protected T readInternal(Class<? extends T> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		Yaml yaml = new Yaml();
		T t = yaml.loadAs(inputMessage.getBody(), clazz);
		return t;
	}

	@Override
	protected void writeInternal(T t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		Yaml yaml = new Yaml();
		OutputStreamWriter writer = new OutputStreamWriter(outputMessage.getBody());
		yaml.dump(t, writer);
		writer.close();
	}

	public T getObjectForString (Class<? extends T> clazz, String yamlString) {
		Yaml yaml = new Yaml();
		T t = yaml.loadAs(yamlString, clazz);
		return t;
	}
	
	public String getStringForYaml(T t) {
		Yaml yaml = new Yaml();
		StringWriter writer = new StringWriter();
		yaml.dump(t, writer);
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
}
