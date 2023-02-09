package hpe.tfm.request.mgmt;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.sun.tools.xjc.BadCommandLineException;


public class XsdGenerateTest {
	
	public static void main(String[] args) throws IOException, BadCommandLineException, JAXBException {
		
		generate();
		

//		TfmXmlRequestMarshaller tfmXmlRequestMarshaller = new TfmXmlRequestMarshaller();
//		Jaxb2Marshaller marshaller = tfmXmlRequestMarshaller.jaxb2Marshaller();
//		marshaller.setPackagesToScan("hpe.tfm.generated.schema0", "hpe.tfm.generated.schema1", "hpe.tfm.generated.schema2", "hpe.tfm.generated.schema3", "hpe.tfm.generated.schema4");
//		ObjectFactory factory0 = new ObjectFactory();
//		Message message = factory0.createMessage();
//		message.setBody(factory0.createMessageBody());
//		message.setHeader(factory0.createMessageHeader());
//		message.getHeader().setXSHAWORIGINATINGUSERID("sasdfasdf");
//		message.getHeader().setXSHAWTRANSACTIONID("2342342423");
//		message.getBody().setServiceActivationRequest(new hpe.tfm.generated.schema1.ObjectFactory().createServiceActivationRequest());
//		message.getBody().getServiceActivationRequest().setTechnicalContext(new hpe.tfm.generated.schema1.ObjectFactory().createServiceActivationRequestTechnicalContext());
//		message.getBody().getServiceActivationRequest().getTechnicalContext().setMessageId("234234234");
//		
//		TfmXmlRequestMarshallerWrapper marshallerWrapper = new TfmXmlRequestMarshallerWrapper();
//		marshallerWrapper.setMarshaller(marshaller);
//		String value = marshallerWrapper.marshallXml(message);
//		System.out.println(value);
	}
	
	private static void generate() throws IOException, BadCommandLineException {
		new TfmRequest()
		.xsdProcessor()
		.setXsdFilePath("C:\\Users\\skumar19\\OneDrive - Hewlett Packard Enterprise\\EclipseWorkspace\\test-frame\\com.hpe.tfm\\hpe.tfm.request.mgmt\\src\\main\\resources\\schema0.xsd")
		.setBinding("C:\\Users\\skumar19\\OneDrive - Hewlett Packard Enterprise\\EclipseWorkspace\\test-frame\\com.hpe.tfm\\hpe.tfm.request.mgmt\\src\\main\\resources\\global.xjb")
		.setTargetDir("C:\\Users\\skumar19\\OneDrive - Hewlett Packard Enterprise\\EclipseWorkspace\\test-frame\\com.hpe.tfm\\hpe.tfm.request.mgmt\\src\\main\\java")
		.generate();
	}

}
