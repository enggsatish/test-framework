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

import hpe.tfm.service.TfmTestCaseService;

@RestController
public class TfmTestCaseController {
	
	private static final Logger log = LoggerFactory.getLogger(TfmTestCaseController.class.getSimpleName());

	@Autowired
	private TfmTestCaseService tfmTestCaseService;
	
	@RequestMapping(value = "tfm/testcase/service", method = RequestMethod.POST, consumes = "text/yaml")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String processSuiteYaml(@RequestBody final String yaml) {
		log.debug("Request Received:: " + yaml);
		String response = tfmTestCaseService.process(yaml);
		return response;
	}
	
	@RequestMapping(value = "tfm/testcase/{testcasename}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String getTestSteps(@PathVariable("testcasename") String testCaseName) {
		log.debug("Accepted::Request::getTestSteps::testcasename:>" + testCaseName);		
		String message = tfmTestCaseService.findAllStepForCase(testCaseName);
		return message;
	}
	
}
