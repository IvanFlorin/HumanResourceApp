package io.zipcoder.threedaystodeliver.humanresourceapp.menus;

import io.zipcoder.threedaystodeliver.humanresourceapp.IncidentReport;
import io.zipcoder.threedaystodeliver.humanresourceapp.IncidentReportWarehouse;
import io.zipcoder.threedaystodeliver.humanresourceapp.Person;
import static io.zipcoder.threedaystodeliver.humanresourceapp.menus.SanitizeTools.getEnforcedIncidentCategory;

import java.util.ArrayList;

public class ReportingMenu extends Menu{

    enum ReportingSelectionOptions {ALL, PROSPECT, EMPLOYEE, INCIDENTS, HOME, EXIT}

    public static final ReportingMenu INSTANCE = new ReportingMenu();

    private ReportingMenu(){
        super(ReportingSelectionOptions.values());
    }

    @Override
    public void selectOption(String userInput) {
        switch (ReportingSelectionOptions.valueOf(userInput)){
            case PROSPECT:
                printAllProspects();
                break;
            case EMPLOYEE:
                printAllEmployees();
                break;
            case ALL:
                printAllEmployees();
                System.out.println();
                printAllProspects();
                break;
            case INCIDENTS:
                addOrViewAllIncidents();
            case HOME:
                return;
            case EXIT:
                System.exit(0);
                return;
        }
    }

    public void printAllProspects() {
        ArrayList<Person> allProspects = personWarehouse.getAllProspects();

        if(allProspects.size() > 0) {
            System.out.println("PROSPECTS:");
            String report = Person.printProspectReportHeader();
            for (Person prospect : allProspects) {
                report += "\n" + prospect.printProspectForReport();
            }

            System.out.println(report);
        }
        else {
            System.out.println("No prospects available to report");
        }
    }

    public void printAllEmployees() {
        ArrayList<Person> allEmployees = personWarehouse.getAllEmployees();

        if(allEmployees.size() > 0) {
            System.out.println("EMPLOYEES:");
            String report = Person.printEmployeeReportHeader();
            for (Person employee : allEmployees) {
                report += "\n" + employee.printEmployeeForReport();
            }

            System.out.println(report);
        }
        else {
            System.out.println("No employees available to report");
        }
    }

    private void addOrViewAllIncidents() {
        String input;
        do {
            System.out.println("===== Incident Reporting =====");
            System.out.println("[ ADD ] [ VIEWALL ] [ BACK ]");
            input = this.getUserInput().toLowerCase();
            switch (input) {
                case "add":
                    addNewIncident();
                    break;
                case "viewall":
                    printAllIncidents();
                    break;
                case "back":
                    return;
            }
        }while (!"back".equalsIgnoreCase(input));
    }

    private void addNewIncident() {
        System.out.println("Please enter the category of incident:");
        System.out.println("[ TYPE1 ] [ TYPE2 ] [ TYPE3 ] [ TYPE4 ]");
        IncidentReport.IncidentCategory inputCategory = getEnforcedIncidentCategory();

        System.out.println("Please enter a description of the incident:");
        String inputDescription = getUserInput();

        IncidentReport newIncidentReport = new IncidentReport(inputCategory, inputDescription);

        String isPersonInvolved;
        Person personInvolved;
        do {
            System.out.println("Add person involved? [Y] [N]");
            isPersonInvolved = getUserInput();
            if("y".equalsIgnoreCase(isPersonInvolved)) {
                String input;
                do {
                    System.out.println("Add by [ID] or [Name]?");
                    input = this.getUserInput();
                } while (!"ID".equalsIgnoreCase(input) && !"Name".equalsIgnoreCase(input));

                if ("ID".equalsIgnoreCase(input)){
                    personInvolved=getPersonById();
                }
                else{
                    personInvolved=getPersonByName();
                }

                if(newIncidentReport.getPersonsInvolved().size() == 0 || !newIncidentReport.getPersonsInvolved().contains(personInvolved)) {
                    newIncidentReport.addPersonToIncidentReport(personInvolved);
                }
                else {
                    System.out.println(personInvolved.getContactInfo().getName() + " already added to incident.");
                }
            }
        } while ("y".equalsIgnoreCase(isPersonInvolved));
    }

    private void printAllIncidents() {
        ArrayList<IncidentReport> allIncidents = IncidentReportWarehouse.getAllIncidents();

        if(allIncidents.size() > 0) {
            System.out.println("Incidents:");
            for(int i = 0; i < allIncidents.size(); i++) {
                System.out.println(allIncidents.get(i).printIncidentForReport());
                System.out.println("==========");
            }
        }
        else {
            System.out.println("No incidents available to report");
        }
    }
}
