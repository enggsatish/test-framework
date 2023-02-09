package hpe.tfm.request.mgmt.util;

import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

@Component
public class TfmXmlRequestMarshallerWrapper {

	@Autowired
	private Jaxb2Marshaller marshaller;
	
	public void setMarshaller(Jaxb2Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public <T> String marshallXml(final T obj) throws JAXBException {
		StringWriter sw = new StringWriter();
		Result result = new StreamResult(sw);
		marshaller.marshal(obj, result);
		return sw.toString();
	}

	@SuppressWarnings("unchecked")
	public <T> T unmarshallXml(final InputStream xml) throws JAXBException {
		return (T) marshaller.unmarshal(new StreamSource(xml));
	}
}
