package hpe.tfm.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hpe.tfm.dao.model.TfmParamPt;
import hpe.tfm.dao.model.TfmParamRuleMap;
import hpe.tfm.dao.service.TfmDaoService;

public class TestReadTestStep extends TestAbstractLoad {
	
	@Autowired
	private TfmDaoService daoService;
	
	@Test
	public void testRetrieveTestStep() {
		int id1 = daoService.getTfmParamPtDaoService().setParamPt().setValues("Message", "Message", null).addTfmRequestParam();		
		int id2 = daoService.getTfmParamPtDaoService().setParamPt().setValues("Body", "Body", null).addTfmRequestParam();
		int id3 = daoService.getTfmParamPtDaoService().setParamPt().setValues("Header", "Header", null).addTfmRequestParam();
		int id4 = daoService.getTfmParamPtDaoService().setParamPt().setValues("TechnicalContext", "TechnicalContext", null).addTfmRequestParam();
		assertNotNull(id1);
		assertNotNull(id2);
		assertNotNull(id3);
		assertNotNull(id4);
		int ruleid1 = daoService.getTfmParamRuleMapDaoService().setTfmParamRuleMap().setValues(id2, 1, id1).addTfmRequestParam();
		int ruleid2 = daoService.getTfmParamRuleMapDaoService().setTfmParamRuleMap().setValues(id3, 1, id1).addTfmRequestParam();
		int ruleid3 = daoService.getTfmParamRuleMapDaoService().setTfmParamRuleMap().setValues(id4, 1, id2).addTfmRequestParam();
		assertNotNull(ruleid1);
		assertNotNull(ruleid2);
		assertNotNull(ruleid3);
		TfmParamPt parampt = daoService.getTfmParamPtDaoService().getById(id4);
		assertEquals(parampt.getTfmParamKey(), "TechnicalContext");
		TfmParamRuleMap map = daoService.getTfmParamRuleMapDaoService().findByTfmParamId(ruleid3).get(0);
		assertNotNull(map);
		//assertNotNull(parampt.getParamRuleList());
		//assertEquals(parampt.getParamRuleList().get(0).getTfm_param_rule_value(), id2);
	}
}
