package hpe.tfm.request;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class TfmXmlRequestMarshaller {

	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("xsd.generated");
        
        marshaller.setMarshallerProperties(new HashMap<String, Object>() {{
          put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
          // additional properties here.
        }});

        return marshaller;
    }
}
