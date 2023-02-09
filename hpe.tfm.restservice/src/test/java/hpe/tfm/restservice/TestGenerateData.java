package hpe.tfm.restservice;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hpe.tfm.service.TfmRequestTemplateService;

public class TestGenerateData extends AbstractRestModuleTest{

	@Autowired
	private TfmRequestTemplateService templateService;
	
	@Test
	public void test() {
		String str = templateService.generateTestStep("1");
		System.out.println(str);
		
	}
}
