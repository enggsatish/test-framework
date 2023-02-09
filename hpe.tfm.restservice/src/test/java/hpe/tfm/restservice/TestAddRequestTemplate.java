package hpe.tfm.restservice;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import hpe.tfm.controller.TfmAddTemplateController;
import hpe.tfm.request.yaml.converter.TfmYamlService;
import hpe.tfm.request.yaml.converter.YamlHttpMessageConverter;
import hpe.tfm.request.yaml.vo.Properties;
import hpe.tfm.request.yaml.vo.YamlRequestTemplate;
import hpe.tfm.service.TfmRequestTemplateService;

@AutoConfigureMockMvc
public class TestAddRequestTemplate extends AbstractRestModuleTest {

	@Autowired
	private TfmAddTemplateController templateController; 
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TfmRequestTemplateService tfmRequestTemplateService;
	
	@Test
	public void addTemplateTest() throws Exception {
		assertNotNull(templateController);
		//String response = templateController.receiveStringYaml(createYamlString());
		String response = tfmRequestTemplateService.process(createYamlString());
		assertNotNull(response);
		assertTrue(response.contains("SUCCESS"));
	}
	
	private YamlRequestTemplate createYamlString() {
		TfmYamlService<YamlRequestTemplate> tfmYamlService = new YamlHttpMessageConverter<YamlRequestTemplate>();
		List<Properties> ruleList = new ArrayList<Properties>();
		YamlRequestTemplate requestTemplate = new YamlRequestTemplate() {{
			setRequest_template_filepath("asdfa");
			setRequest_template_type("XSD");
			setRules(ruleList);
			getRules().add(new Properties("asdfas", "asdfasdf"));
			getRules().add(new Properties("asdfaasds", "asdfasdasdfasf"));
		}};
		String yaml = tfmYamlService.getStringForYaml(requestTemplate);
		return requestTemplate;
	}
	
//	public void controllerTemplateTest() throws Exception {
//		assertNotNull(templateController);		
//		mockMvc
//			.perform(post("/tfm/requesttemplate/create"))
//			.andDo(print())
//			.andExpect(status().isOk());
//	}
}
