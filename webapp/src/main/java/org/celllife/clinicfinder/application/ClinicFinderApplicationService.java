package org.celllife.clinicfinder.application;

import org.celllife.clinicfinder.application.clinicservice.ClinicServiceApplicationService;
import org.celllife.clinicfinder.domain.datamart.UssdClinicFinderRepository;
import org.celllife.clinicfinder.domain.ussd.Request;
import org.celllife.mobilisr.client.MobilisrClient;
import org.springframework.context.MessageSource;

/**
 * This service does everything that the clinic finder needs.
 */
public interface ClinicFinderApplicationService {

	/**
	 * Find a clinic and send an SMS to the person who requested it. Also saves information in the datamart.
	 * @param request Request
	 */
	void findClinicAndSendSms(Request request);

	void setCommunicateClient(MobilisrClient communicateClient);

	void setClinicService(ClinicServiceApplicationService clinicService);

	void setUssdClinicFinderRepository(UssdClinicFinderRepository ussdClinicFinderRepository);

	void setMessageSource(MessageSource messageSource);
}
