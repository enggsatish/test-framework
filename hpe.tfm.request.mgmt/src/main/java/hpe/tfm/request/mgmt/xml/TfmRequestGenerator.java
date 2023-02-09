package hpe.tfm.request.mgmt.xml;

import org.springframework.stereotype.Service;

@Service
public interface TfmRequestGenerator {
	
	static final String packageName = "xsd.generated";
	static final String splitregex = "/"; 
	static final String set = "set";
	static final String get = "get";
	static final String JAXBElement = "JAXBElement";
	static final String underscore = "_";
	static final String empty = "";
	
	public String generateRequestPayload()  throws Exception;
	
	public TfmRequestGenerator setTestStepId(int testStepId);

}
