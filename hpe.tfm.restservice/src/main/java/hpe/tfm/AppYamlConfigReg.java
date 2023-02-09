package hpe.tfm;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import hpe.tfm.request.yaml.converter.YamlHttpMessageConverter;

@Configuration
@ComponentScan("hpe.tfm.request.yaml.vo")
public class AppYamlConfigReg implements WebMvcConfigurer  {

	@Override
	public void extendMessageConverters (List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlHttpMessageConverter<>());
	}
}
