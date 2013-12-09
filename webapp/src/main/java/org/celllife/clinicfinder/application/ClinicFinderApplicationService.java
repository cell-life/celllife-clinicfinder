package org.celllife.clinicfinder.application;

import org.celllife.clinicfinder.domain.ussd.Request;

/**
 * This service does everything that the clinic finder needs.
 */
public interface ClinicFinderApplicationService {

	/**
	 * Find a clinic and send an SMS to the person who requested it. Also saves information in the datamart.
	 * @param request Request
	 */
	void findClinicAndSendSms(Request request);
	
}
