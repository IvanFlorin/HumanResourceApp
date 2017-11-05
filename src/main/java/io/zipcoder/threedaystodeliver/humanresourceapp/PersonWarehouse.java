package io.zipcoder.threedaystodeliver.humanresourceapp;

import java.util.ArrayList;
import java.util.Arrays;

public class PersonWarehouse {

    private static PersonWarehouse INSTANCE = null;

    private static ArrayList<Person> people = new ArrayList<>();

   private PersonWarehouse(){}

    public static PersonWarehouse getInstance(){
        if(INSTANCE == null){
            INSTANCE = new PersonWarehouse();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }



    public void addPerson(Person person){
        people.add(person);
    }

    public ArrayList<Person> getAllFormerEmployees() {
        ArrayList<Person> listOfFormerEmployees = new ArrayList<>(3);
        for (Person person : people) {
            if (EmploymentStatus.TERMINATED.equals(person.getEmploymentStatus())){
                listOfFormerEmployees.add(person);
            }
        }return listOfFormerEmployees;
    }

    public ArrayList<Person> getAllEmployees() {
        ArrayList<Person> listOfAllEmployees = new ArrayList<>(100);
        for (Person person : people) {
            if (EmploymentStatus.EMPLOYEE.equals(person.getEmploymentStatus())) {
                listOfAllEmployees.add(person);
            }
        }
        return listOfAllEmployees;
    }

    public ArrayList<Person> getAllOfType(EmploymentStatus employmentStatus) {
        ArrayList<Person> listOfAllOfType = new ArrayList<>(100);
        for (Person person : people) {
            if (employmentStatus == person.getEmploymentStatus()) {
                listOfAllOfType.add(person);
            }
        }
        return listOfAllOfType;
    }

    public ArrayList<Person> getAllProspects() {
        ArrayList<Person> listOfProspects = new ArrayList<>(100);
        for (Person person : people) {
            if (EmploymentStatus.PROSPECT.equals(person.getEmploymentStatus())) {
                listOfProspects.add(person);
            }
        }
        return listOfProspects;
    }

    public Person getPersonById(String id){
        for(Person person: people) {
            if(person.getId().equals(id))
                return person;
        }
        return null;
    }

    public Person getPersonById(String id, ArrayList<Person> specificPeople){
        for(Person person: specificPeople) {
            if (person.getId().equals(id))
                return person;
        }
        return null;
    }

    public ArrayList<Person> getAllPeople() {
        return people;
    }

    public ArrayList<Person> getPersonByName(String name){
        ArrayList<Person> listOfMatchedNames = new ArrayList<>(10);
        for(Person person: people) {
            if(person.getContactInfo().getName().equalsIgnoreCase(name)) {
                listOfMatchedNames.add(person);
            }
        }
        return listOfMatchedNames;
    }

    public ArrayList<Person> getPersonByName(String name, EmploymentStatus employmentStatus){
        ArrayList<Person> listOfMatchedNames = new ArrayList<>(10);
        for(Person person: people) {
            if(person.getContactInfo().getName().equalsIgnoreCase(name) && person.getEmploymentStatus() == employmentStatus) {
                listOfMatchedNames.add(person);
            }
        }
        return listOfMatchedNames;
    }

    public void printAllOfType(EmploymentStatus employmentStatus) {
        ArrayList<Person> allOfType = this.getAllOfType(employmentStatus);

        if (allOfType.size() > 0) {
            System.out.println(employmentStatus + "S:");
            String report = "";
            if (EmploymentStatus.PROSPECT == employmentStatus) {
                report = printProspectReportHeader();
                for (Person prospect : allOfType) {
                    report += "\n" + printProspectForReport(prospect);
                }
            }
            if (EmploymentStatus.EMPLOYEE == employmentStatus) {
                report = printEmployeeReportHeader();
                for (Person employee : allOfType) {
                    report += "\n" + printEmployeeForReport(employee);
                }
            }

            System.out.println(report);
        } else {
            System.out.println("No " + employmentStatus + "S available to report");
        }
    }

    public static String printProspectReportHeader() {
        String report = String.format("%-20s| %-10s| %-10s| %-30s| %-15s", "Name", "ID", "Score", "Resume", "Interview Date");
        char[] chars = new char[93];
        Arrays.fill(chars, '_');
        String line = new String(chars);
        report += "\n" + line;
        return report;
    }

    public String printProspectForReport(Person prospect) {
        String report = String.format("%-20s| %-10s| %-10.2f| %-30s| %-15s", prospect.getContactInfo().getName(),
                prospect.getId(), prospect.getScore(), prospect.getResume(), prospect.getInterviewDate());
        return report;
    }

    public static String printEmployeeReportHeader() {
        String report = String.format("%-20s| %-10s| %-20s| %-11s| %-23s| %-15s", "Name", "ID", "Job Title", "Salary", "Monthly/Hourly/Project", "Date Hired");
        char[] chars = new char[109];
        Arrays.fill(chars, '_');
        String line = new String(chars);
        report += "\n" + line;
        return report;
    }

    public String printEmployeeForReport(Person employee) {
        String report = String.format("%-20s| %-10s| %-20s| $%-10.2f| %-23s| %-15s", employee.getContactInfo().getName(),
                employee.getId(), employee.getTitle(), employee.getCompensation().getPayrate(),
                employee.getCompensation().getCompensationType(), employee.getHiredDate());
        return report;
    }

}
