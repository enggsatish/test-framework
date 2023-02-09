package hpe.tfm.restservice;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import hpe.tfm.Application;
import hpe.tfm.service.TfmRequestTemplateService;

@RunWith( SpringRunner.class )
@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = Application.class)
public abstract class AbstractRestModuleTest {

	@Autowired
	private TfmRequestTemplateService service;
	
	@Test
	public void testRequestTemplateService() {
		assertNotNull(service);
	}
}
