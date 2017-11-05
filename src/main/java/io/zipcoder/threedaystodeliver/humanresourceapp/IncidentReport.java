package io.zipcoder.threedaystodeliver.humanresourceapp;

import java.util.ArrayList;

public class IncidentReport {

    private ArrayList<Person> personsInvolved = new ArrayList<>(10);
    public enum IncidentCategory {
        type1,
        type2,
        type3,
        type4
    }
    private IncidentCategory incidentCategory;
    private ArrayList<String> description = new ArrayList<>(10);
    private int id;
    private static int nextId = 1;
    private static IncidentReportWarehouse warehouse = IncidentReportWarehouse.getInstance();

    public IncidentReport(IncidentCategory category, String initialDescription) {
        incidentCategory = category;
        description.add(initialDescription);
        id = nextId;
        nextId++;
        warehouse.addIncident(this);
    }

    public ArrayList<Person> getPersonsInvolved() {
        return personsInvolved;
    }

    public IncidentCategory getIncidentCategory() {
        return incidentCategory;
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void addPersonToIncidentReport(Person person) {
        personsInvolved.add(person);
        person.addIncidentReport(this);
    }

    public void removePersonFromIncidentReport(Person person) {
        personsInvolved.remove(person);
        person.removeIncidentReport(this);
    }

    public void updateIncidentReport(IncidentCategory category) {
        incidentCategory = category;
    }

    public void updateIncidentReport(String nextDescription) {
        description.add(nextDescription);
    }

    public String printIncidentForReport() {
        String report = String.format("ID: %d\nCategory: %s", getId(), getIncidentCategory());

        if(getPersonsInvolved().size() > 0 ) {
            for(int i = 1; i <= getPersonsInvolved().size(); i++) {
                report += "\nPerson " + i + ": " + getPersonsInvolved().get(i - 1).getId() + " " +getPersonsInvolved().get(i - 1).getContactInfo().getName();
            }
        }

        report += "\nDescription: ";
        for(int i = 0; i < getDescription().size(); i++) {
            report += getDescription().get(i) + "\n";
        }
        return report;
    }

}
