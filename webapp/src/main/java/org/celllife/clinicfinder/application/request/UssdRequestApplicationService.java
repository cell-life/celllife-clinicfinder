package org.celllife.clinicfinder.application.request;

import org.celllife.clinicfinder.domain.ussd.Request;

/**
 * Application service for the USSD Request entity
 */
public interface UssdRequestApplicationService {
	
	/**
	 * Persists the Request object into the storage and also creates an entry in the datamart table
	 * @param request Request object to save 
	 * @return Request object, saved (with ids)
	 */
	Request save(Request request);
}
