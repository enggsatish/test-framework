package hpe.tfm.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hpe.tfm.dao.model.TfmParamPt;
import hpe.tfm.dao.model.TfmParamRuleMap;
import hpe.tfm.dao.service.TfmDaoService;


public class TestLoadSuiteRepository extends TestAbstractLoad {

	@Autowired
	private TfmDaoService daoService;
	
//	@Test
//	public void addTestSuite() {
//		//int id = daoService.getTfmTestSuiteDaoService().setValues("value", "valuse").addTestSuite();
//		//assertEquals(daoService.getTfmTestSuiteDaoService().getById(id).getTfm_suite_name(), "value");
//	}
	
	@Test
	public void findAllParamTest() {
		List<TfmParamPt> paramList = daoService.getTfmParamPtDaoService().findAll();
		assertNotNull(paramList);		
	}
	
	@Test
	public void findAllParamRuleTest() {
		List<TfmParamRuleMap> paramList = daoService.getTfmParamRuleMapDaoService().findAll();
		assertNotNull(paramList);
		
	}

}
