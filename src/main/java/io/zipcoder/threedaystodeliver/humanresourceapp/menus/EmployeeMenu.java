package io.zipcoder.threedaystodeliver.humanresourceapp.menus;

import io.zipcoder.threedaystodeliver.humanresourceapp.EmploymentStatus;
import io.zipcoder.threedaystodeliver.humanresourceapp.HrContactInfo;

import java.time.LocalDate;
import java.util.ArrayList;

import io.zipcoder.threedaystodeliver.humanresourceapp.Person;
import io.zipcoder.threedaystodeliver.humanresourceapp.PersonHandler;
import io.zipcoder.threedaystodeliver.humanresourceapp.PersonWarehouse;

public class EmployeeMenu extends Menu{

    enum EmployeeSelectionOptions { ADD, UPDATE, VIEWALL, CLEAR, HOME, EXIT}

    public static final EmployeeMenu INSTANCE = new EmployeeMenu();

    private static Person currentPerson = null;

    private EmployeeMenu(){
        super(EmployeeSelectionOptions.values());
    }

    @Override
    public void selectOption(String userInput) {
        switch (EmployeeSelectionOptions.valueOf(userInput)){
            case ADD:
                addNewEmployee();
                System.out.println("Hired: " + currentPerson.toString());
                currentPerson=null;
                //something
                break;
            case UPDATE:
                updateEmployee();
                //
                break;
            case CLEAR:
                currentPerson = null;
                break;
            case HOME:
                //go up
                break;
            case VIEWALL:
                //
                viewAllAndSelect(EmploymentStatus.EMPLOYEE);
                currentPerson=null;
                break;
            case EXIT:
                System.exit(0);
                return;
        }
    }

    private void updateEmployee(){
        if (personWarehouse.getAllOfType(EmploymentStatus.EMPLOYEE).size()==0)
        {
            System.out.println("There are no employees currently in the system.");
        }
        else {
            if (currentPerson == null) {
                setActiveEmployee();
            }
            updateEmployeeField();
        }
        currentPerson=null;
    }

    private void setActiveEmployee() {
        String input;
        do {
            System.out.println("\nFind by [ID] or [Name]?");
            input = this.getUserInput();
        } while (!"ID".equalsIgnoreCase(input) && !"Name".equalsIgnoreCase(input));

        if ("ID".equalsIgnoreCase(input)){
            currentPerson=getPersonById(EmploymentStatus.EMPLOYEE);
        }
        else{
            currentPerson=getPersonByName(EmploymentStatus.EMPLOYEE);
        }
    }

    private void updateEmployeeField() {
        String input;
        do {
            System.out.println("Pick [back] to return to the employee menu.");
            System.out.println("Selected: \n"+currentPerson+"\nPlease pick from the list of update options.\nEnter [ BACK ] when your updates are complete.\n" +
                    "[ NAME ] [ EMAIL ] [ ADDRESS ] [ PHONE ] [ SCORE ] [ RESUME ] [ ID ] [ INTERVIEW DATE ] [ COMPENSATION ]\n" +
                    "To terminate employee, enter [ TERMINATE ]: ");
            input = this.getUserInput().toLowerCase();
            switch(input) {
                case "score":
                    System.out.println("Please enter new score.");
                    Double newScore = SanitizeTools.getEnforcedPositiveDoubleInput();
                    currentPerson.setScore(newScore);
                    System.out.println("The new score is ["+currentPerson.getScore()+"].");
                    break;
                case "resume":
                    System.out.println("Please enter new resume.");
                    String newResume = this.getUserInput();
                    currentPerson.setResume(newResume);
                    System.out.println("The new resume is ["+currentPerson.getResume()+"].");
                    break;
                case "id":
                    System.out.println("Please enter new ID.");
                    String newId = this.getUserInput();
                    currentPerson.setId(newId);
                    System.out.println("The new ID is ["+currentPerson.getId()+"].");
                    break;
                case "interview date":
                    System.out.println("Please enter new interview date in YYYY-MM-DD format.");
                    LocalDate newIntDate = SanitizeTools.getEnforcedLocalDateInput();
                    currentPerson.setInterviewDate(newIntDate);
                    System.out.println("The new interview date is ["+currentPerson.getInterviewDate()+"].");
                    break;
                case "name":
                    System.out.println("Please enter new name.");
                    String newName = this.getUserInput();
                    currentPerson.getContactInfo().setName(newName);
                    System.out.println("The new name is ["+currentPerson.getContactInfo().getName()+"].");
                    break;
                case "email":
                    System.out.println("Please enter new email address.");
                    String newEmail = this.getUserInput();
                    currentPerson.getContactInfo().setEmail(newEmail);
                    System.out.println("The new email is ["+currentPerson.getContactInfo().getEmail()+"].");
                    break;
                case "address":
                    System.out.print("Enter address line 1: ");
                    String inputAddressLine1 = this.getUserInput();
                    System.out.print("Enter address line 2: ");
                    String inputAddressLine2 = this.getUserInput();
                    System.out.print("Enter city: ");
                    String inputCity = this.getUserInput();
                    System.out.print("Enter state: ");
                    String inputState = this.getUserInput();
                    System.out.print("Enter zip code: ");
                    String inputZipCode = this.getUserInput();
                    currentPerson.getContactInfo().getStreetAddress().changeStreetAddress(inputAddressLine1, inputAddressLine2, inputCity, inputState, inputZipCode);
                    System.out.println("The new address is ["+currentPerson.getContactInfo().getStreetAddress()+"].");
                    break;
                case "phone":
                    System.out.println("Please enter new phone number");
                    String newPhone = this.getUserInput();
                    currentPerson.getContactInfo().setPhone(newPhone);
                    System.out.println("The new ID is ["+currentPerson.getContactInfo().getPhone()+"].");
                    break;
                case "compensation":
                    currentPerson.setCompensation(changeCompensation());
                    break;
                case "terminate":
                    terminateEmployee();
                    break;

                case "back":
                    return;
            }
        } while (!"back".equalsIgnoreCase(input));
    }

    private void terminateEmployee(){
        String input;
        System.out.println(currentPerson.getContactInfo().getName()+" will be terminated. Continue? [Y] [N]:  ");
        input=getUserInput();
        if ("Y".equalsIgnoreCase(input)) {

            LocalDate terminationDate;
            System.out.print("Enter termination date in YYYY-MM-DD format: ");
            terminationDate = SanitizeTools.getEnforcedLocalDateInput();

            String reason;
            System.out.print("Enter reason for termination: ");
            reason = getUserInput();

            String exitInterview;
            System.out.print("Enter exit interview note: ");
            exitInterview=getUserInput();

            PersonHandler.terminate(currentPerson, terminationDate, reason, exitInterview, null);
        }
    }

    ////////

    private void viewAllAndSelect(EmploymentStatus employmentStatus){
        PersonWarehouse.getInstance().printAllOfType(employmentStatus);
        String input = "";
        String id = "";
        do {
            System.out.println("\n[SELECT] "+employmentStatus+" from list or go [BACK]:");
            input = this.getUserInput();
            if("BACK".equalsIgnoreCase(input)) {
                break;
            } else if ("SELECT".equalsIgnoreCase(input)){
                setActiveEmployee();
                System.out.println(currentPerson.getContactInfo().getName()+" selected.");
                break;
            } else {
                System.out.println("Please try again.");
            }
        } while (!"BACK".equalsIgnoreCase(input) && !"SELECT".equalsIgnoreCase(input));
    }

    private void addNewEmployee(){
        createAndSetActiveNewEmployee();
        System.out.println("New employee, "+currentPerson.getContactInfo().getName()+", added with ID: "+
                currentPerson.getId()+".");

        System.out.println("Please add resume. Enter [Skip] to add one later.");
        String resume = this.getUserInput();
        if (!"skip".equalsIgnoreCase(resume)){
            currentPerson.setResume(resume);
            System.out.println("Resume added");
        } else {
            System.out.println("Resume addition skipped.");
        }

        System.out.println("Please update employee score. Enter [Add] to do so now or [Skip] to do so later.");
        String input = this.getUserInput();
        if(!"skip".equalsIgnoreCase(input)) {
            System.out.println("Please enter score:");
            Double score = SanitizeTools.getEnforcedPositiveDoubleInput();
            currentPerson.setScore(score);
        } else {
            System.out.println("Score update skipped.");
        }

        currentPerson.setCompensation(changeCompensation());


    }

    private void createAndSetActiveNewEmployee() {
        HrContactInfo requestedInfo = this.inputAllContactInfo();
        Person newEmployee = PersonHandler.createEmployee(requestedInfo);
        currentPerson = newEmployee;
    }

//    private void prospectMenu() {
//        ProspectMenu prospectMenu = new ProspectMenu();
//        prospectMenu.display();
//    }
//
//    private void employeeMenu() {
//        EmployeeMenu employeeMenu = new EmployeeMenu();
//        employeeMenu.display();
//    }
//
//    private void reportingMenu() {
//        ReportingMenu reportingMenu = new ReportingMenu();
//        reportingMenu.display();
//    }

}
