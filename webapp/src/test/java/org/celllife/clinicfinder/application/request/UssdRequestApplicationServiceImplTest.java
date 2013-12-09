package org.celllife.clinicfinder.application.request;

import java.util.Date;

import junit.framework.Assert;

import org.celllife.clinicfinder.domain.datamart.UssdClinicFinder;
import org.celllife.clinicfinder.domain.ussd.LocationData;
import org.celllife.clinicfinder.domain.ussd.Request;
import org.celllife.clinicfinder.domain.ussd.User;
import org.celllife.clinicfinder.domain.ussd.UssdRequest;
import org.celllife.clinicfinder.test.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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


        UssdClinicFinder ussdSubmission = ussdRequestApplicationServiceImpl.convertToUssdClinicFinder(request);

        Assert.assertEquals("1",ussdSubmission.getUssdRequestId());
        Assert.assertEquals("*130*555*1000#", ussdSubmission.getUssdString());
        Assert.assertEquals(today, ussdSubmission.getDateTime());
        Assert.assertEquals("27721234567", ussdSubmission.getMsisdn());
        Assert.assertEquals("1", ussdSubmission.getMnoCode());
        Assert.assertEquals(28.0918827056885, ussdSubmission.getXCoordinate());
        Assert.assertEquals(-25.892427444458, ussdSubmission.getYCoordinate());
    }

}
