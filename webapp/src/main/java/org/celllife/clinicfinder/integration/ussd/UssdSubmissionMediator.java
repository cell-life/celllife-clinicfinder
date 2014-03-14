package org.celllife.clinicfinder.integration.ussd;

import java.io.IOException;

import org.celllife.clinicfinder.application.ClinicFinderApplicationService;
import org.celllife.clinicfinder.application.request.UssdRequestApplicationService;
import org.celllife.clinicfinder.domain.ussd.LocationData;
import org.celllife.clinicfinder.domain.ussd.Request;
import org.celllife.clinicfinder.domain.ussd.User;
import org.celllife.clinicfinder.domain.ussd.UssdRequest;
import org.celllife.clinicfinder.domain.ussd.json.Root;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.message.GenericMessage;

@org.springframework.stereotype.Service("ussdSubmissionMediator")
public class UssdSubmissionMediator {
	
	private static Logger log = LoggerFactory.getLogger(UssdSubmissionMediator.class);

    public static final String HAPPY_RESULT = "{ \"UssdSubmissionResponse\": { \"message\": \"Completed Successfully\" } }";
    
    public static final String SMS_TEXT = "${clinic.sms.text}";

    @Autowired
    private UssdRequestApplicationService ussdRequestApplicationService;
    
    @Autowired
    private ClinicFinderApplicationService clinicFinderApplicationService;

    public Message<String> handleUssdSubmission(Message<byte[]> message) throws JsonProcessingException, IOException {
    	String payload = new String(message.getPayload());
    	if (log.isDebugEnabled()) {
    		log.debug("ussd submission payload: "+payload);
    	}
		Root root = new ObjectMapper().readValue(payload, Root.class);
		Request request = convertRootToRequest(root);
		request = ussdRequestApplicationService.save(request);
		if (log.isDebugEnabled()) {
    		log.debug("ussd submission request object: "+request);
    	}
		if (request != null && !request.getLocationData().isEmpty()) {
			clinicFinderApplicationService.findClinicAndSendSms(request);
		}
		return new GenericMessage<>(HAPPY_RESULT);
    }
    
    Root convertPayloadToRoot(String payload) throws JsonProcessingException, IOException  {
    	// using jackson not smooks because i found that the recursive/nested array did not work
    	return new ObjectMapper().readValue(payload, Root.class);
    }
    
    Request convertRootToRequest(Root root) {
    	// code smell: could (should) do this using a library
    	Request request = new Request();
    	
    	UssdRequest ussdRequest = new UssdRequest();
    	request.setUssdRequest(ussdRequest);
        ussdRequest.setId(root.getUssdSubmissionRequest().getUssdRequest().getId());
    	ussdRequest.setUssdString(root.getUssdSubmissionRequest().getUssdRequest().getUssdString());
        ussdRequest.setDateTime(root.getUssdSubmissionRequest().getUssdRequest().getDateTime());

    	User user = new User();
    	request.setUser(user);
    	user.setMsisdn(root.getUssdSubmissionRequest().getUser().getMsisdn());
    	user.setMnoCode(root.getUssdSubmissionRequest().getUser().getMnoCode());

        LocationData locationData = new LocationData();
        request.setLocationData(locationData);
        locationData.setXCoordinate(root.getUssdSubmissionRequest().getLocationData().getxCoordinate());
        locationData.setYCoordinate(root.getUssdSubmissionRequest().getLocationData().getyCoordinate());
    	
    	return request;
    }
}