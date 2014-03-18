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
		Request saveRequest = request;
		Request duplicateRequest = getDuplicateRequest(request);
		if (duplicateRequest != null) {
			if (duplicateRequest.getLocationData().equals(request.getLocationData())) {
				return null;
			}
			saveRequest = duplicateRequest;
			saveRequest.getLocationData().setXCoordinate(request.getLocationData().getXCoordinate());
			saveRequest.getLocationData().setYCoordinate(request.getLocationData().getYCoordinate());
		}
        Request savedRequest = requestRepository.save(saveRequest);
		UssdClinicFinder ussdClinicFinder = convertToUssdClinicFinder(request);
		UssdClinicFinder duplicateUssdClinicFinder = getDuplicateUssdClinicFinder(ussdClinicFinder);
		if (duplicateUssdClinicFinder == null) {
			if (log.isTraceEnabled()) {
				log.trace("converted Request into UssdClinicFinder: "+ ussdClinicFinder);
			}
			ussdClinicFinderRepository.save(ussdClinicFinder);
		}
		return savedRequest;
	}

	Request getDuplicateRequest(Request request) {
		Request duplicateRequest = null;
		try {
			duplicateRequest = requestRepository.findOneByUssdRequestId(request.getUssdRequest().getId());
			if (duplicateRequest != null) {
				log.warn("Ignoring this request because one already exists with the same ussd_request_id. "+request);
			}
		} catch (Exception e) {
			log.warn("Could not find duplicate requests with ussd session id '"+request.getUssdRequest().getId()+"' due to error '"+e.getMessage()+"'");
		}
		return duplicateRequest;
	}

	UssdClinicFinder getDuplicateUssdClinicFinder(UssdClinicFinder ussdClinicFinder) {
		UssdClinicFinder duplicateUssdClinicFinder = null;
		try {
			duplicateUssdClinicFinder = ussdClinicFinderRepository.findOneByUssdRequestId(ussdClinicFinder.getUssdRequestId());
			if (duplicateUssdClinicFinder != null) {
				log.warn("Not exporting this request to the datamart because one already exists with the same ussd_request_id. "+duplicateUssdClinicFinder);
			}
		} catch (Exception e) {
			log.warn("Could not find duplicate requests with ussd session id '"+ussdClinicFinder.getUssdRequestId()+"' due to error '"+e.getMessage()+"'");
		}
		return duplicateUssdClinicFinder;
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