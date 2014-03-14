package org.celllife.clinicfinder.integration.ussd;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.celllife.clinicfinder.domain.ussd.LocationData;
import org.celllife.clinicfinder.domain.ussd.Request;
import org.celllife.clinicfinder.domain.ussd.json.Root;
import org.celllife.clinicfinder.test.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.message.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class UssdSubmissionMediatorIntegrationTest {

    @Autowired
    private UssdSubmissionMediator ussdSubmissionMediator;

    @Test
    public void testHandleUssdSubmission() throws Exception {
    	String json = IOUtils.toString(ClassLoader.getSystemResourceAsStream("json/request.json"));
        final GenericMessage<byte[]> input = new GenericMessage<>(json.getBytes());
        Message<String> result = ussdSubmissionMediator.handleUssdSubmission(input);
        Assert.assertEquals(UssdSubmissionMediator.HAPPY_RESULT, result.getPayload());
    }
    
    @Test
    public void testConvertPayloadToRoot() throws Exception {

        String json = IOUtils.toString(ClassLoader.getSystemResourceAsStream("json/request.json"));

        Root root = ussdSubmissionMediator.convertPayloadToRoot(json);
    	Assert.assertNotNull(root);
    	Assert.assertNotNull(root.getUssdSubmissionRequest());

        Assert.assertNotNull(root.getUssdSubmissionRequest().getUssdRequest());
        Assert.assertEquals(root.getUssdSubmissionRequest().getUssdRequest().getUssdString(), "*130*555*1000#");
        Assert.assertEquals(root.getUssdSubmissionRequest().getUssdRequest().getId(), "1");
        Assert.assertEquals(root.getUssdSubmissionRequest().getUssdRequest().getDateTime().toString(), "Sun Jan 01 22:00:00 SAST 2012");

    	Assert.assertNotNull(root.getUssdSubmissionRequest().getUser());
    	Assert.assertEquals(root.getUssdSubmissionRequest().getUser().getMsisdn(), "27721234567");
    	Assert.assertEquals(root.getUssdSubmissionRequest().getUser().getMnoCode(), "1");

        Assert.assertNotNull(root.getUssdSubmissionRequest().getLocationData());
        Assert.assertEquals(root.getUssdSubmissionRequest().getLocationData().getxCoordinate(), 28.0918827056885);
        Assert.assertEquals(root.getUssdSubmissionRequest().getLocationData().getyCoordinate(), -25.892427444458);
    }
    
    @Test
    public void testConvertRootToRequest() throws Exception {

        String json = IOUtils.toString(ClassLoader.getSystemResourceAsStream("json/request.json"));
    	Root root = ussdSubmissionMediator.convertPayloadToRoot(json);
    	Request request = ussdSubmissionMediator.convertRootToRequest(root);
    	
    	Assert.assertNotNull(request);

    	Assert.assertNotNull(request.getUser());
    	Assert.assertEquals(request.getUser().getMsisdn(), "27721234567");
    	Assert.assertEquals(request.getUser().getMnoCode(), "1");
    	
        Assert.assertNotNull(request.getLocationData());
        Assert.assertEquals(request.getLocationData().getXCoordinate(), 28.0918827056885);
        Assert.assertEquals(request.getLocationData().getYCoordinate(), -25.892427444458);
    }

    @Test
    public void testEmptyLocationData() throws Exception {
    	LocationData locationData = new LocationData();
    	Assert.assertTrue("Both null", locationData.isEmpty());
    	locationData.setXCoordinate(0d);
    	Assert.assertTrue("One null, one zero", locationData.isEmpty());
    	locationData.setXCoordinate(10d);
    	Assert.assertTrue("One null, one not zero", locationData.isEmpty());
    	locationData.setXCoordinate(0d);
    	locationData.setYCoordinate(0d);
    	Assert.assertTrue("Both zero", locationData.isEmpty());
    }

    @Test
    public void testNotEmptyLocationData() throws Exception {
    	LocationData locationData = new LocationData();
    	locationData.setXCoordinate(0d);
    	locationData.setYCoordinate(10d);
    	Assert.assertFalse(locationData.isEmpty());
    	locationData.setXCoordinate(10d);
    	locationData.setYCoordinate(10d);
    	Assert.assertFalse(locationData.isEmpty());
    }
}