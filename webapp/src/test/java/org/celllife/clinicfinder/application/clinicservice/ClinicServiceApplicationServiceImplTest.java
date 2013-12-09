package org.celllife.clinicfinder.application.clinicservice;

import org.celllife.clinicfinder.test.TestConfiguration;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ClinicServiceApplicationServiceImplTest {

	@Autowired
	ClinicServiceApplicationService clinicServiceApplicationService;

	@Test
	@Ignore("requires a working clinic service")
	public void testHappyDays() throws Exception {
		Clinic clinic = clinicServiceApplicationService.getNearestClinic(18.417606, -33.933782);
		Assert.assertNotNull(clinic);
		Assert.assertTrue(clinic.getName().contains("Nurock"));
	}
	
}
