package hpe.tfm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hpe.tfm.request.yaml.vo.YamlRequestTemplate;
import hpe.tfm.service.TfmRequestTemplateService;

@RestController
public class TfmAddTemplateController {

	private static final Logger log = LoggerFactory.getLogger(TfmAddTemplateController.class.getSimpleName());
	
	@Autowired
	private TfmRequestTemplateService tfmRequestTemplateService;
	
	@RequestMapping(value = "tfm/requesttemplate/create", method = RequestMethod.POST, consumes = "text/yaml", produces = "text/yaml")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String createRequestTemplate(@RequestBody final YamlRequestTemplate yaml) {
		log.debug("Accepted::Request::createRequestTemplate::>" + yaml);
		StringBuffer buffer = new StringBuffer();
		buffer.append(tfmRequestTemplateService.process(yaml));
		buffer.append("\n");
		buffer.append(tfmRequestTemplateService.populateRules());
		return buffer.toString();
	}
}
