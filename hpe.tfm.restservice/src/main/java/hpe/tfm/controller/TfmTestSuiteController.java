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

import hpe.tfm.service.TfmTestSuiteService;

@RestController
public class TfmTestSuiteController {
	
	private static final Logger log = LoggerFactory.getLogger(TfmTestSuiteController.class.getSimpleName());
	
	@Autowired
	private TfmTestSuiteService tfmTestSuiteService;

	@RequestMapping(value = "tfm/testsuite/service", method = RequestMethod.POST, consumes = "text/yaml")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String receiveStringYaml(@RequestBody final String yaml) {
		log.debug("Request Received:: " + yaml);
		String response = tfmTestSuiteService.process(yaml);
		return response;
	}
	
	@RequestMapping(value = "tfm/testsuite/{testsuitename}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String getTestCases(@PathVariable("testsuitename") String testSuiteName) {
		log.debug("Accepted::Request::getTestCases::testsuitename:>" + testSuiteName);		
		String message = tfmTestSuiteService.findAllCaseBySuiteName(testSuiteName);
		return message;
	}
}
