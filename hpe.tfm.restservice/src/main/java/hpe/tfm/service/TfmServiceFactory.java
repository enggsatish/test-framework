package hpe.tfm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TfmServiceFactory {
	
	private static final Logger log = LoggerFactory.getLogger(TfmServiceFactory.class.getSimpleName());
	
//	private final TfmService tfmService;
	
//	private final List<TfmService> tfmServiceList;
//	
//	@Autowired
//	public TfmServiceFactory(List<TfmService> tfmServiceList) {
//		this.tfmServiceList = tfmServiceList;
//	}
	
//	@Autowired
//	public TfmServiceFactory(@Qualifier("HpeSaVideoService") TfmService tfmService ) {
//		this.tfmService = tfmService;
//	}
//	
//	public String process(String yaml, RegisteredService service) {
//		log.debug("process::start");
//		if (service == RegisteredService.esfvideoservice) {
//			return tfmService.process(yaml);
//		}
//		log.debug("process::end::error");
//		return "ERROR: Count not find processor";
//	}

}
