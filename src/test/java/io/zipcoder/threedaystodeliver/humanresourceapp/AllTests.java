package io.zipcoder.threedaystodeliver.humanresourceapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PersonTest.class,
        PersonFactoryTest.class,
        //PersonHandlerTest.class,
        PersonWarehouseTest.class,
        HrContactInfoTest.class,
        StreetAddressTest.class,
        CompensationTest.class,
        IncidentReportTest.class,
        //IncidentWarehouseTest.class,

})
public class AllTests {
}
