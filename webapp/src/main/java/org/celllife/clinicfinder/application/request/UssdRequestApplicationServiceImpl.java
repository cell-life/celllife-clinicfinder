package org.celllife.clinicfinder.application.request;

import org.celllife.clinicfinder.domain.ussd.Request;
import org.celllife.clinicfinder.domain.ussd.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.celllife.clinicfinder.domain.datamart.*;

@org.springframework.stereotype.Service
public class UssdRequestApplicationServiceImpl implements UssdRequestApplicationService {
	
	private static Logger log = LoggerFactory.getLogger(UssdRequestApplicationServiceImpl.class);
	
	@Autowired
    RequestRepository requestRepository;
	
	@Autowired
    UssdSubmissionRepository ussdSubmissionRepository;

	@Override
	public Request save(Request request) {
		//Request savedRequest = requestRepository.save(request);
		UssdSubmission ussdSubmissions = convertToUssdPageVisits(request);
		if (log.isTraceEnabled()) {
			log.trace("converted Request into UssdPageVisits: "+ ussdSubmissions);
		}
        ussdSubmissionRepository.save(ussdSubmissions);
		return request;
	}
	
	UssdSubmission convertToUssdPageVisits(Request request) {

		UssdSubmission ussdSubmission = new UssdSubmission();
        ussdSubmission.setId(request.getId());
        ussdSubmission.setUssdString(request.getUssdRequest().getUssdString());
        ussdSubmission.setDateTime(request.getUssdRequest().getDateTime());
        ussdSubmission.setMsisdn(request.getUser().getMsisdn());
        ussdSubmission.setMnoCode(request.getUser().getMnoCode());
        ussdSubmission.setXCoordinate(request.getLocationData().getXCoordinate());
        ussdSubmission.setYCoordinate(request.getLocationData().getYCoordinate());
        //ussdSubmission.setClosestLandmarks(request.getClosestLandmarks().toString());

        return ussdSubmission;

	}
}