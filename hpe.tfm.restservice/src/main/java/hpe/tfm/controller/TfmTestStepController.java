package hpe.tfm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hpe.tfm.service.TfmRequestTemplateService;
import hpe.tfm.service.TfmTestStepService;

@RestController
public class TfmTestStepController {
	
	private static final Logger log = LoggerFactory.getLogger(TfmTestStepController.class.getSimpleName());

	@Autowired
	private TfmTestStepService tfmTestStepService; 
	
	@Autowired
	private TfmRequestTemplateService tfmRequestTemplateService;
	
	/**
	 * Accept generic YAML request as String.
	 * 
	 * @param yaml
	 * @return
	 */
	@RequestMapping(value = "tfm/teststep/template/service", method = RequestMethod.POST, consumes = "text/yaml")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String processSuiteYaml(@RequestBody final String yaml) {
		log.debug("Request Received:: " + yaml);
		String response = tfmTestStepService.processTemplate(yaml);
		return response;
	}
	
	/**
	 * Read all template from system and present 
	 * 
	 * @return
	 */
	@RequestMapping(value = "tfm/teststep/template/getall", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String findAllTemplate() {
		log.debug("Request Received:: Find All registered Template");
		return tfmTestStepService.findAllTemplate();
	}
	
	@RequestMapping(value = "tfm/teststep/template/{teamplatename}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String searchTemplate(@PathVariable("teamplatename") String teamplatename) {
		log.debug("Accepted::Request::searchTemplate::teamplatename:>" + teamplatename);		
		return tfmTestStepService.findTemplateByName(teamplatename);
	}

	/**
	 * Register a test step. 
	 * 
	 * @param yaml
	 * @return test-step-name
	 */
	@RequestMapping(value = "tfm/teststep/service", method = RequestMethod.POST, consumes = "text/yaml")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String receiveStringYaml(@RequestBody final String yaml) {
		log.debug("Request Received:: " + yaml);
		String response = tfmTestStepService.process(yaml);
		return response;
	}
	
	@RequestMapping(value = "tfm/teststep/{teststepid}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String generateTestStep(@PathVariable("teststepid") String testStepId) {
		log.debug("Accepted::Request::generateTestStep::teststepid:>" + testStepId);		
		String message = tfmRequestTemplateService.generateTestStep(testStepId);
		return message;
	}
}
