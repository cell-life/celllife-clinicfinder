package org.celllife.clinicfinder.integration.ussd;

import junit.framework.Assert;
import org.apache.commons.io.IOUtils;
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

    	Assert.assertNotNull(root.getUssdSubmissionRequest().getUser());
    	Assert.assertEquals(root.getUssdSubmissionRequest().getUser().getMsisdn(), "27721234567");
    	Assert.assertEquals(root.getUssdSubmissionRequest().getUser().getMnoCode(), "1");

        Assert.assertNotNull(root.getUssdSubmissionRequest().getLocationData());
        Assert.assertEquals(root.getUssdSubmissionRequest().getLocationData().getxCoordinate(), 28.0918827056885);
        Assert.assertEquals(root.getUssdSubmissionRequest().getLocationData().getyCoordinate(), -25.892427444458);

        Assert.assertNotNull(root.getUssdSubmissionRequest().getClosestLandmarks());
        Assert.assertEquals(root.getUssdSubmissionRequest().getClosestLandmarks().get(0).getLocationName(),"Pontshong (Walkerville) Clinic");

        Assert.assertNotNull(root.getUssdSubmissionRequest().getSmsText());
        Assert.assertEquals(root.getUssdSubmissionRequest().getSmsText(),"Thank you. Nearest clinics: Pontshong (Walkerville) Clinic 0214611124, Thula Mntwana Clinic 0214609269.");
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

        Assert.assertNotNull(request.getClosestLandmarks());
        Assert.assertEquals(request.getClosestLandmarks().get(0).getLocationName(),"Pontshong (Walkerville) Clinic");

        Assert.assertEquals(request.getSmsText(),"Thank you. Nearest clinics: Pontshong (Walkerville) Clinic 0214611124, Thula Mntwana Clinic 0214609269.");

    }
}