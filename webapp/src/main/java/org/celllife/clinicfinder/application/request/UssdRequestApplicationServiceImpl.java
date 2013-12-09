package org.celllife.clinicfinder.application.request;

import org.celllife.clinicfinder.domain.datamart.UssdClinicFinder;
import org.celllife.clinicfinder.domain.datamart.UssdClinicFinderRepository;
import org.celllife.clinicfinder.domain.ussd.Request;
import org.celllife.clinicfinder.domain.ussd.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class UssdRequestApplicationServiceImpl implements UssdRequestApplicationService {
	
	private static Logger log = LoggerFactory.getLogger(UssdRequestApplicationServiceImpl.class);
	
	@Autowired
    RequestRepository requestRepository;
	
	@Autowired
    UssdClinicFinderRepository ussdClinicFinderRepository;

	@Override
	public Request save(Request request) {
        Request savedRequest = requestRepository.save(request);
		UssdClinicFinder ussdClinicFinder = convertToUssdClinicFinder(request);
		if (log.isTraceEnabled()) {
			log.trace("converted Request into UssdClinicFinder: "+ ussdClinicFinder);
		}
        ussdClinicFinderRepository.save(ussdClinicFinder);
		return savedRequest;
	}
	
	UssdClinicFinder convertToUssdClinicFinder(Request request) {

		UssdClinicFinder ussdSubmission = new UssdClinicFinder();
        ussdSubmission.setUssdRequestId(request.getUssdRequest().getId());
        ussdSubmission.setUssdString(request.getUssdRequest().getUssdString());
        ussdSubmission.setDateTime(request.getUssdRequest().getDateTime());
        ussdSubmission.setMsisdn(request.getUser().getMsisdn());
        ussdSubmission.setMnoCode(request.getUser().getMnoCode());
        ussdSubmission.setXCoordinate(request.getLocationData().getXCoordinate());
        ussdSubmission.setYCoordinate(request.getLocationData().getYCoordinate());

        return ussdSubmission;

	}
}