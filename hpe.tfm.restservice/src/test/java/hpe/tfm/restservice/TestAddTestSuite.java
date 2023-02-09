package hpe.tfm.restservice;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hpe.tfm.dao.service.TfmDaoService;
import hpe.tfm.service.RegisteredService;
import hpe.tfm.service.TfmServiceFactory;

public class TestAddTestSuite extends AbstractRestModuleTest {
	
	@Autowired
	private TfmDaoService daoService;
	
	@Test
	public void testAddTestSuite() {
//		String result = serviceFactory.process(getSuiteYaml(), RegisteredService.esfvideoservice);
//		assertTrue(result.contains("SUCCESS"));
		//String result = serviceFactory.process(getStepYaml(), RegisteredService.esfvideoservice);
		//daoService.getTfmTestStepDaoService().fi
		//assertTrue(result.contains("SUCCESS"));
//		daoService.getTfmTestStepDaoService().findByTfmTsId(tfmTsId)
	}
	
}
