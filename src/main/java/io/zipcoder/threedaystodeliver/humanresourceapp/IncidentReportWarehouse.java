package io.zipcoder.threedaystodeliver.humanresourceapp;

import java.util.ArrayList;

public class IncidentReportWarehouse {


    private static IncidentReportWarehouse INSTANCE = null;
    private static ArrayList<IncidentReport> allIncidents = new ArrayList<>();

    private IncidentReportWarehouse(){}

    public static IncidentReportWarehouse getInstance(){

        if(INSTANCE == null) {
            INSTANCE = new IncidentReportWarehouse();
            return INSTANCE;
        }
        else {return INSTANCE;}
    }

    public void addIncident(IncidentReport incidentReport) {
        allIncidents.add(incidentReport);
    }

    public static ArrayList<IncidentReport> getAllIncidents() {
        return allIncidents;

    }

}
