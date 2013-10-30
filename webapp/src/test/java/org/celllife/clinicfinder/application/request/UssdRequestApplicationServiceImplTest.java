package org.celllife.clinicfinder.application.request;

import junit.framework.Assert;
import org.celllife.clinicfinder.domain.datamart.UssdSubmission;
import org.celllife.clinicfinder.domain.ussd.*;
import org.celllife.clinicfinder.test.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class UssdRequestApplicationServiceImplTest {

    @Autowired
    private UssdRequestApplicationServiceImpl ussdRequestApplicationServiceImpl;

    @Test
    public void convertToUssdPageVisitsTest() {

        Request request = new Request();
        Date today = new Date();

        UssdRequest ussdRequest = new UssdRequest("1","*130*555*1000#", today );
        request.setUssdRequest(ussdRequest);
        User user = new User("27721234567","1");
        request.setUser(user);
        LocationData locationData = new LocationData(28.0918827056885, -25.892427444458);
        request.setLocationData(locationData);

        List<ClosestLandmark> closestLandmarks = new ArrayList<ClosestLandmark>();
        closestLandmarks.add(new ClosestLandmark("Location 1"));
        closestLandmarks.add(new ClosestLandmark("Location 2"));
        request.setClosestLandmarks(closestLandmarks);
        request.setSmsText("Thank you. Nearest clinics: Pontshong (Walkerville) Clinic 0214611124, Thula Mntwana Clinic 0214609269.");

        UssdSubmission ussdSubmission = ussdRequestApplicationServiceImpl.convertToUssdPageVisits(request);

        Assert.assertEquals("1",ussdSubmission.getUssdRequestId());
        Assert.assertEquals("*130*555*1000#", ussdSubmission.getUssdString());
        Assert.assertEquals(today, ussdSubmission.getDateTime());
        Assert.assertEquals("27721234567", ussdSubmission.getMsisdn());
        Assert.assertEquals("1", ussdSubmission.getMnoCode());
        Assert.assertEquals(28.0918827056885, ussdSubmission.getXCoordinate());
        Assert.assertEquals(-25.892427444458, ussdSubmission.getYCoordinate());
        Assert.assertEquals("Location 1, Location 2", ussdSubmission.getClosestLandmarks());
        Assert.assertEquals("Thank you. Nearest clinics: Pontshong (Walkerville) Clinic 0214611124, Thula Mntwana Clinic 0214609269.", ussdSubmission.getSmsText());
    }

}
