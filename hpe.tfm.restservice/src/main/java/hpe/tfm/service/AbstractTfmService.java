package hpe.tfm.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import hpe.tfm.dao.model.TfmParamPt;
import hpe.tfm.dao.service.TfmDaoService;

public abstract class AbstractTfmService {
	
	@Autowired
	private TfmDaoService daoService;
	
	protected Map<String, Integer> aliasParamIdMap = null;
	
	protected void init() {
		List<TfmParamPt> paramPt = daoService.getTfmParamPtDaoService().findAll();
		aliasParamIdMap = Collections.unmodifiableList(paramPt).stream()
				.collect(Collectors.toMap(TfmParamPt::getTfmParamAlias, TfmParamPt::getTfmParamId));
	}
	
	protected enum TemplateType {
		test_suite, test_case, test_step;
	}

}
