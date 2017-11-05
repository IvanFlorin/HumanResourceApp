package io.zipcoder.threedaystodeliver.humanresourceapp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class IncidentReportTest {

    @Test
    public void addPersonToIncidentReportTest() {
        IncidentReport testReport = new IncidentReport(IncidentReport.IncidentCategory.type1, "test description");
        Person testPerson = new Person();

        testReport.addPersonToIncidentReport(testPerson);
        Assert.assertEquals(testPerson, testReport.getPersonsInvolved().get(0));
    }

    @Test
    public void removePersonFromIncidentReportTest() {
        IncidentReport testReport = new IncidentReport(IncidentReport.IncidentCategory.type1, "test description");
        Person testPerson = new Person();
        testReport.addPersonToIncidentReport(testPerson);
        int sizeBeforeRemoving = testReport.getPersonsInvolved().size();
        testReport.removePersonFromIncidentReport(testPerson);
        Assert.assertEquals(sizeBeforeRemoving - 1, testReport.getPersonsInvolved().size());
    }

    @Test
    public void updateIncidentReportCategoryTest() {
        IncidentReport testReport = new IncidentReport(IncidentReport.IncidentCategory.type1, "test description");
        testReport.updateIncidentReport(IncidentReport.IncidentCategory.type2);
        Assert.assertEquals(IncidentReport.IncidentCategory.type2, testReport.getIncidentCategory());
    }

    @Test
    public void updateIncidentReportDescriptionTest() {
        IncidentReport testReport = new IncidentReport(IncidentReport.IncidentCategory.type1, "test description");
        testReport.updateIncidentReport("smells bad");
        ArrayList<String> reportDescriptions = testReport.getDescription();
        Assert.assertEquals("smells bad", reportDescriptions.get(reportDescriptions.size() - 1));
    }

    @Test
    public void printIncidentForReportTest() {
        IncidentReport testReport = new IncidentReport(IncidentReport.IncidentCategory.type1, "test description");
        String expected = "";
        String actual = testReport.printIncidentForReport();
        Assert.assertEquals(expected, actual);
    }
}
