package org.celllife.clinicfinder.application;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.celllife.mobilisr.api.rest.CampaignDto;
import org.celllife.mobilisr.api.rest.ContactDto;
import org.celllife.mobilisr.api.rest.PagedListDto;
import org.celllife.mobilisr.client.CampaignService;
import org.celllife.mobilisr.client.ContactService;
import org.celllife.mobilisr.client.MobilisrClient;
import org.celllife.mobilisr.client.exception.RestCommandException;
import org.celllife.mobilisr.constants.CampaignStatus;
import org.celllife.mobilisr.constants.CampaignType;
import org.junit.Before;
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
	MessageSource messageSource;
	
	@Autowired
	UssdClinicFinderRepository ussdClinicFinderRepository;
	
	// clinic to recommend
	private Clinic recommendClinic;
	
	// results from the sendSms test
	private String createdCampaignName;
	private String createdCampaignMessage;
	private List<ContactDto> createdCampaignContacts;
	
	@Before
	public void setup() throws Exception {
		clinicFinderApplicationService = new ClinicFinderApplicationServiceImpl();
		clinicFinderApplicationService.setClinicService(new MockClinicServiceApplicationService());
		clinicFinderApplicationService.setMessageSource(messageSource);
		clinicFinderApplicationService.setUssdClinicFinderRepository(ussdClinicFinderRepository);
		clinicFinderApplicationService.setCommunicateClient(new MockMobilisrClient());
	}

	@Test(timeout=50000)
	public void testSendSms() throws Exception {
		recommendClinic = new Clinic("Test Clinic", "Test Clinic", "123 Hope Street", "021555555");
		createdCampaignName = null;
		Request request = new Request();
		request.setLocationData(new LocationData(18.417606, -33.933782));
		request.setUssdRequest(new UssdRequest("123", "string", new Date()));
		request.setUser(new User("27768198075", "3"));
		UssdClinicFinder datamart = ussdClinicFinderRepository.findOneByUssdRequestId("123");
		if (datamart == null) {
			datamart = new  UssdClinicFinder();
			datamart.setUssdRequestId("123");
			ussdClinicFinderRepository.save(datamart);
		}
		clinicFinderApplicationService.findClinicAndSendSms(request);
		do {
			Thread.sleep(1000);
		} while (createdCampaignName == null);
		Assert.assertTrue(createdCampaignName.startsWith("ClinicFinder "));
		Assert.assertEquals("Clinic Finder found: Test Clinic\n123 Hope Street\nCall 021555555", createdCampaignMessage);
		Assert.assertNotNull(createdCampaignContacts);
	}
	
	@Test
	public void testGetNearestClinic() throws Exception {
		recommendClinic = new Clinic("Test Clinic", "Test Clinic", "123 Hope Street", "021555555");
		Request request = new Request();
		request.setLocationData(new LocationData(18.417606, -33.933782));
		Clinic clinic = clinicFinderApplicationService.getNearestClinic(request);
		Assert.assertNotNull(clinic);
		Assert.assertEquals("Test Clinic", clinic.getName());
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
		Assert.assertTrue(smsText.contains("Unknown address"));
		Assert.assertTrue(smsText.contains("021555555"));
	}
	
	@Test
	public void testGetSmsTextNullPhone() throws Exception {
		Clinic clinic = new Clinic("Test Clinic", "Test Clinic", "123 Hope Street", "");
		String smsText = clinicFinderApplicationService.getSmsText(clinic);
		Assert.assertNotNull(smsText);
		Assert.assertTrue(smsText.contains("Test Clinic"));
		Assert.assertTrue(smsText.contains("123 Hope Street"));
		Assert.assertTrue(smsText.contains("Unknown telephone number"));
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
	
	class MockClinicServiceApplicationService implements ClinicServiceApplicationService {

		@Override
		public Clinic getNearestClinic(Double latitude, Double longitude) {
			return recommendClinic;
		}
		
	}

	class MockMobilisrClient implements MobilisrClient {

		@Override
		public CampaignService getCampaignService() {
			return new MockCampaignService();
		}

		@Override
		public ContactService getContactService() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	class MockCampaignService implements CampaignService {

		@Override
		public PagedListDto<CampaignDto> getCampaigns() throws RestCommandException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PagedListDto<CampaignDto> getCampaigns(Integer offset, Integer limit) throws RestCommandException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PagedListDto<CampaignDto> getCampaigns(CampaignType type) throws RestCommandException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PagedListDto<CampaignDto> getCampaigns(CampaignType type, Integer offset, Integer limit)
				throws RestCommandException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PagedListDto<CampaignDto> getCampaigns(CampaignStatus status) throws RestCommandException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PagedListDto<CampaignDto> getCampaigns(CampaignStatus status, Integer offset, Integer limit)
				throws RestCommandException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PagedListDto<CampaignDto> getCampaigns(CampaignType type, CampaignStatus status)
				throws RestCommandException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PagedListDto<CampaignDto> getCampaigns(CampaignType type, CampaignStatus status, Integer offset,
				Integer limit) throws RestCommandException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PagedListDto<CampaignDto> getCampaigns(Map<String, Object> parameters) throws RestCommandException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CampaignDto getCampaignDetails(Long campaignId) throws RestCommandException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void addContactToCampaign(Long campaignId, ContactDto contact) throws RestCommandException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addContactsToCampaign(Long campaignId, List<ContactDto> contacts) throws RestCommandException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeContactFromCampaign(Long campaignId, String msisdn) throws RestCommandException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeContactFromCampaign(Long campaignId, ContactDto contact) throws RestCommandException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public long createNewCampaign(CampaignDto campaign) throws RestCommandException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void createNewCampaign(String name, String description, String message, List<ContactDto> contacts)
				throws RestCommandException {
			createdCampaignName = name;
			createdCampaignMessage = message;
			createdCampaignContacts = contacts;
		}
		
	}
}
