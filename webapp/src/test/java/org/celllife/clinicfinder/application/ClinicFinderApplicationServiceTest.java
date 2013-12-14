package org.celllife.clinicfinder.application;

import java.util.Date;

import junit.framework.Assert;

import org.celllife.clinicfinder.application.clinicservice.Clinic;
import org.celllife.clinicfinder.application.clinicservice.ClinicServiceApplicationService;
import org.celllife.clinicfinder.domain.datamart.UssdClinicFinder;
import org.celllife.clinicfinder.domain.datamart.UssdClinicFinderRepository;
import org.celllife.clinicfinder.domain.ussd.LocationData;
import org.celllife.clinicfinder.domain.ussd.Request;
import org.celllife.clinicfinder.domain.ussd.User;
import org.celllife.clinicfinder.domain.ussd.UssdRequest;
import org.celllife.clinicfinder.test.TestConfiguration;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ClinicFinderApplicationServiceTest {
	
	//@Autowired
	ClinicFinderApplicationServiceImpl clinicFinderApplicationService;
	
	@Autowired
	ClinicServiceApplicationService clinicService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	UssdClinicFinderRepository ussdClinicFinderRepository;
	
	@Before
	public void setup() throws Exception {
		clinicFinderApplicationService = new ClinicFinderApplicationServiceImpl();
		clinicFinderApplicationService.setClinicService(clinicService);
		clinicFinderApplicationService.setMessageSource(messageSource);
		clinicFinderApplicationService.setUssdClinicFinderRepository(ussdClinicFinderRepository);
	}

	@Test
	@Ignore("This actually sends an SMS")
	public void testSomething() throws Exception {
		Request request = new Request();
		request.setLocationData(new LocationData(18.417606, -33.933782));
		request.setUssdRequest(new UssdRequest("123", "string", new Date()));
		request.setUser(new User("27768198075", "3"));
		//request.setUser(new User("27828699920", "3")); // Annie's phone number
		UssdClinicFinder datamart = ussdClinicFinderRepository.findOneByUssdRequestId("123");
		if (datamart == null) {
			datamart = new  UssdClinicFinder();
			datamart.setUssdRequestId("123");
			ussdClinicFinderRepository.save(datamart);
		}
		clinicFinderApplicationService.findClinicAndSendSms(request);
		Thread.sleep(10000);
	}
	
	@Test
	@Ignore("requires a clinic service")
	public void testGetNearestClinic() throws Exception {
		Request request = new Request();
		request.setLocationData(new LocationData(18.417606, -33.933782));
		Clinic clinic = clinicFinderApplicationService.getNearestClinic(request);
		Assert.assertNotNull(clinic);
		Assert.assertTrue(clinic.getName().contains("Nurock"));
	}
	
	@Test
	public void testGetSmsText() throws Exception {
		Clinic clinic = new Clinic("Test Clinic", "Test Clinic", "123 Hope Street", "021555555");
		String smsText = clinicFinderApplicationService.getSmsText(clinic);
		Assert.assertNotNull(smsText);
		Assert.assertTrue(smsText.contains("Test Clinic"));
		Assert.assertTrue(smsText.contains("123 Hope Street"));
		Assert.assertTrue(smsText.contains("021555555"));
	}
	
	@Test
	public void testGetSmsTextNullAddress() throws Exception {
		Clinic clinic = new Clinic("Test Clinic", "Test Clinic", null, "021555555");
		String smsText = clinicFinderApplicationService.getSmsText(clinic);
		Assert.assertNotNull(smsText);
		Assert.assertTrue(smsText.contains("Test Clinic"));
		Assert.assertTrue(smsText.contains("unknown"));
		Assert.assertTrue(smsText.contains("021555555"));
	}
	
	@Test
	public void testGetSmsTextNullPhone() throws Exception {
		Clinic clinic = new Clinic("Test Clinic", "Test Clinic", "123 Hope Street", "");
		String smsText = clinicFinderApplicationService.getSmsText(clinic);
		Assert.assertNotNull(smsText);
		Assert.assertTrue(smsText.contains("Test Clinic"));
		Assert.assertTrue(smsText.contains("123 Hope Street"));
		Assert.assertTrue(smsText.contains("unknown"));
	}
	
	@Test
	public void testUpdateUssdClinicFinder() throws Exception {
		Request request = new Request();
		request.setUssdRequest(new UssdRequest("567", "string", new Date()));
		Clinic clinic = new Clinic("Test Clinic", "Test Clinic", null, "021555555");
		clinic.setProvinceName("My Province");
		clinic.setDistrictName("My District");
		String smsText = "Sms text";
		UssdClinicFinder datamart1 = ussdClinicFinderRepository.findOneByUssdRequestId("567");
		if (datamart1 == null) {
			datamart1 = new UssdClinicFinder("567", new Date(), "2767879872", "1", 1.22, 3.44);
			ussdClinicFinderRepository.save(datamart1);
		}
		clinicFinderApplicationService.updateUssdClinicFinder(request, clinic, smsText);
		UssdClinicFinder datamart2 = ussdClinicFinderRepository.findOneByUssdRequestId(request.getUssdRequest().getId());
		Assert.assertNotNull(datamart2);
		Assert.assertEquals(clinic.getName(), datamart2.getClosestClinicName());
		Assert.assertEquals(clinic.getProvinceName(), datamart2.getProvinceName());
		Assert.assertEquals(clinic.getDistrictName(), datamart2.getDistrictName());
		Assert.assertEquals(smsText, datamart2.getSmsText());
	}
}
