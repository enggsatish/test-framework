package hpe.tfm.request;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import hpe.tfm.request.mgmt.TfmRequest;

@RunWith(SpringRunner.class )
@SpringBootTest 
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = TfmRequestApplicationConfig.class)
public class ReqDaoTest {
	
	@Autowired
	private TfmRequest tfmRequest;  
	
	@Test
	public void findAllParamTest() throws Exception {
		assertNotNull(tfmRequest);
		String str = tfmRequest.requestGenerator().setTestStepId(1).generateRequestPayload();
		System.out.println(str);
		assertNotNull(str);
	}
	
	@SpringBootApplication
	static class TestConfiguration {
	}
}
