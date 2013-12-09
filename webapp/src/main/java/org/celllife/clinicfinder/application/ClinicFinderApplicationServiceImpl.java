package org.celllife.clinicfinder.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.celllife.clinicfinder.application.clinicservice.Clinic;
import org.celllife.clinicfinder.application.clinicservice.ClinicServiceApplicationService;
import org.celllife.clinicfinder.domain.datamart.UssdClinicFinder;
import org.celllife.clinicfinder.domain.datamart.UssdClinicFinderRepository;
import org.celllife.clinicfinder.domain.ussd.Request;
import org.celllife.mobilisr.api.rest.ContactDto;
import org.celllife.mobilisr.client.MobilisrClient;
import org.celllife.mobilisr.client.exception.RestCommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class ClinicFinderApplicationServiceImpl implements ClinicFinderApplicationService {
	
	private static Logger log = LoggerFactory.getLogger(ClinicFinderApplicationServiceImpl.class);
	
	@Autowired
	MobilisrClient communicateClient;
	
	@Autowired
	ClinicServiceApplicationService clinicService;
	
	@Autowired
	UssdClinicFinderRepository ussdClinicFinderRepository;
	
	@Autowired
	MessageSource messageSource;

	@Override
	//@Async
	public void findClinicAndSendSms(Request request) {
		// Execute in a background task
		SimpleAsyncTaskExecutor sate = new SimpleAsyncTaskExecutor();
		sate.createThread(new ClinicTask(request));
	}
	
	class ClinicTask implements Runnable {
		Request request;
		
		public ClinicTask(Request request) {
			this.request = request;
		}

		@Override
		public void run() {
			// get the clinic
			Clinic clinic = getNearestClinic(request);

			// get the message to send
			String smsText = getSmsText(clinic);
			
			// store the new data in the datamart for reporting purposes
			updateUssdClinicFinder(request, clinic, smsText);
			
			// send an SMS
			sendSms(request, smsText);
		}
	}
	
	Clinic getNearestClinic(Request request) {
		Double latitude = request.getLocationData().getXCoordinate();
		Double longitude = request.getLocationData().getYCoordinate();
		Clinic clinic = clinicService.getNearestClinic(latitude, longitude);
		log.debug("Found clinic: "+clinic.getName());
		return clinic;
	}
	
	String getSmsText(Clinic clinic) {
		String phone = (clinic.getAddress() == null || clinic.getAddress().trim().isEmpty() ? "unknown" : clinic.getAddress());
		String address = (clinic.getPhoneNumber() == null || clinic.getPhoneNumber().trim().isEmpty() ? "unknown" : clinic.getPhoneNumber());
		String[] args = new String[] { clinic.getName(), address, phone };
		String smsText = messageSource.getMessage("clinicSmsText", args, null, null);
		log.debug("SmsText="+smsText);
		return smsText;
	}
	
	void updateUssdClinicFinder(Request request, Clinic clinic, String smsText) {
		UssdClinicFinder datamart = ussdClinicFinderRepository.findOneByUssdRequestId(request.getUssdRequest().getId());
		datamart.setClosestClinicId(clinic.getId());
		datamart.setClosestClinicName(clinic.getShortName());
		datamart.setProvinceName(clinic.getProvinceName());
		datamart.setDistrictName(clinic.getDistrictName());
		datamart.setSmsText(smsText);
		ussdClinicFinderRepository.save(datamart);		
	}
	
	void sendSms(Request request, String smsText) {
		String msisdn = request.getUser().getMsisdn();
		ContactDto contact = new ContactDto();
		contact.setMsisdn(msisdn);
		List<ContactDto> contacts = new ArrayList<ContactDto>();
		contacts.add(contact);
		String campaignName = "ClinicFinder"+new Date()+"-"+msisdn;
		String campaignDescription = "ClinicFinder JustSendSMS to "+msisdn;
		try {
			communicateClient.getCampaignService().createNewCampaign(campaignName, campaignDescription, smsText, contacts);
		} catch (RestCommandException e) {
			log.error("Could not send an SMS to '"+msisdn+"'.",e);
		}
		log.debug("findClinicAndSendSms [end]. Should have sent SMS '"+smsText+"' to msisdn '"+msisdn+"'.");
	}
}