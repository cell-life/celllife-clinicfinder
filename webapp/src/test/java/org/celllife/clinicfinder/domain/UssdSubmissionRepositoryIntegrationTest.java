package org.celllife.clinicfinder.domain;

import junit.framework.Assert;
import org.celllife.clinicfinder.domain.datamart.UssdSubmission;
import org.celllife.clinicfinder.domain.datamart.UssdSubmissionRepository;
import org.celllife.clinicfinder.domain.ussd.json.UssdSubmissionRequest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:/META-INF/spring/spring-cache.xml",
        "classpath:/META-INF/spring/spring-config.xml",
        "classpath:/META-INF/spring/spring-domain.xml",
        "classpath:/META-INF/spring/spring-jdbc.xml",
        "classpath:/META-INF/spring/spring-orm.xml",
        "classpath:/META-INF/spring/spring-tx.xml",
        "classpath:/META-INF/spring-data/spring-data-jpa.xml"
})
@Ignore
public class UssdSubmissionRepositoryIntegrationTest {

        @Autowired
        private UssdSubmissionRepository ussdSubmissionRepository;

        private Date yesterday;

        /*@Before
        public void setUp() throws Exception {

            yesterday = new SimpleDateFormat("yyyyMMdd").parse(
                    ""+(Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date()))-1));

            ussdSubmissionRepository.deleteAll();

            UssdPageVisit ussdVisit = new UssdPageVisit();
            ussdVisit.setUssdSessionId("1");
            ussdVisit.setDate(new Date());
            ussdVisit.setMsisdn("27724194158");
            ussdVisit.setContentVersion("1");
            ussdVisit.setServiceId("1");
            ussdVisit.setServiceTitle("smrtSex");
            ussdVisit.setThemeId("1");
            ussdVisit.setThemeTitle("Sex & Family Planning");
            ussdVisitsStoreRepository.save(ussdVisit);

            UssdPageVisit ussdVisit2 = new UssdPageVisit();
            ussdVisit2.setUssdSessionId("2");
            ussdVisit2.setDate(new Date());
            ussdVisit2.setMsisdn("27724194158");
            ussdVisit2.setContentVersion("1");
            ussdVisit2.setServiceId("2");
            ussdVisit2.setServiceTitle("iChooseWhen");
            ussdVisit2.setThemeId("1");
            ussdVisit2.setThemeTitle("Sex & Family Planning");
            ussdVisitsStoreRepository.save(ussdVisit2);

            UssdPageVisit ussdVisit3 = new UssdPageVisit();
            ussdVisit3.setUssdSessionId("3");
            ussdVisit3.setDate(new Date());
            ussdVisit3.setMsisdn("27724194158");
            ussdVisit3.setContentVersion("1");
            ussdVisit3.setServiceId("2");
            ussdVisit3.setServiceTitle("iChooseWhen");
            ussdVisit3.setThemeId("1");
            ussdVisit3.setThemeTitle("Sex & Family Planning");
            ussdVisit3.setSmsId("2");
            ussdVisitsStoreRepository.save(ussdVisit3);

            UssdPageVisit ussdVisit5 = new UssdPageVisit();
            ussdVisit5.setUssdSessionId("5");
            ussdVisit5.setDate(new Date());
            ussdVisit5.setMsisdn("27724194158");
            ussdVisit5.setContentVersion("1");
            ussdVisit5.setThemeId("1");
            ussdVisit5.setThemeTitle("Sex & Family Planning");
            ussdVisitsStoreRepository.save(ussdVisit5);

            UssdPageVisit ussdVisit4 = new UssdPageVisit();
            ussdVisit4.setUssdSessionId("4");
            ussdVisit4.setDate(new Date());
            ussdVisit4.setMsisdn("27724194158");
            ussdVisit4.setContentVersion("1");
            ussdVisit4.setServiceId("3");
            ussdVisit4.setServiceTitle("BabyInfo");
            ussdVisit4.setThemeId("2");
            ussdVisit4.setThemeTitle("Pregnancy & Childcare");
            ussdVisitsStoreRepository.save(ussdVisit4);

            UssdPageVisit ussdVisit6 = new UssdPageVisit();
            ussdVisit6.setUssdSessionId("6");
            ussdVisit6.setDate(new Date());
            ussdVisit6.setMsisdn("27724194158");
            ussdVisit6.setContentVersion("1");
            ussdVisit6.setThemeId("2");
            ussdVisit6.setThemeTitle("Pregnancy & Childcare");
            ussdVisitsStoreRepository.save(ussdVisit6);
        }

        @Test
        public void testFindTotalVisitsPerService() {
            Collection<UssdServiceVisitsDTO> result = ussdVisitsStoreRepository.findTotalVisitsPerService(yesterday, new Date());
            Assert.assertEquals(3, result.size());
            Assert.assertNotNull(result.iterator().next().getServiceVisits());
        }

        @Test
        public void testFindTotalVisitsPerServiceInTheme() {
            Collection<UssdServiceVisitsDTO> result = ussdVisitsStoreRepository.findTotalVisitsPerServiceInTheme(yesterday, new Date(), "1");
            Assert.assertEquals(2, result.size());
            Assert.assertNotNull(result.iterator().next().getServiceVisits());
        }

        @Test
        public void testFindTotalVisitsPerTheme() {
            Collection<UssdThemeVisitsDTO> result = ussdVisitsStoreRepository.findTotalVisitsPerTheme(yesterday, new Date());
            Assert.assertEquals(2,result.size());
            Assert.assertNotNull(result.iterator().next().getThemeVisits());
        } */

}
