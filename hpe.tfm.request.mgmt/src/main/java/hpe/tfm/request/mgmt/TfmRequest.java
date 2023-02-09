package hpe.tfm.request.mgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hpe.tfm.dao.service.TfmDaoService;
import hpe.tfm.request.mgmt.xml.TfmRequestGenerator;
import hpe.tfm.request.mgmt.xml.TfmXsdProcessor;
import hpe.tfm.request.mgmt.xml.impl.TfmXsdProcessorImpl;

@Service
public class TfmRequest {
	
	@Autowired
	private TfmDaoService daoService;
	
	@Autowired
	private TfmRequestGenerator requestGenerator;
	/**
	 * New XSD template to be registered first 
	 * 
	 * @param xsdFilePath
	 */
	public TfmXsdProcessor xsdProcessor() {
		return TfmXsdProcessorImpl.instance;
	}
	
	public TfmRequestGenerator requestGenerator() {
		//TfmTestStep testStep = daoService.getTfmTestStepDaoService().getById(testStepId);
		//if (testStep.getTfmReqTId() == 1)
		return requestGenerator;
		//return null;
	}
}
