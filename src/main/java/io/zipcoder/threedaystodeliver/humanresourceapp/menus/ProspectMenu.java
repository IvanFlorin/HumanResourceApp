package io.zipcoder.threedaystodeliver.humanresourceapp.menus;

import io.zipcoder.threedaystodeliver.humanresourceapp.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static io.zipcoder.threedaystodeliver.humanresourceapp.menus.SanitizeTools.getEnforcedLocalDateInput;

public class ProspectMenu extends Menu{

    enum ProspectSelectionOptions { ADD, UPDATE, CLEAR, VIEWALL, HOME, EXIT}

    public static final ProspectMenu INSTANCE = new ProspectMenu();

    private static Person currentPerson = null;

    private ProspectMenu(){
        super(ProspectSelectionOptions.values());
    }

    @Override
    public void display(){
        String userInput;
        do {
            System.out.printf("\n\n===== %s =====\n", getClass().getSimpleName());
            if (currentPerson == null){
                System.out.println("\nNo prospect selected");
            } else {
                System.out.println("\nSelected prospect: "+currentPerson.getContactInfo().getName());
            }
            userInput = this.getMenuInput().toUpperCase();
            try {
                selectOption(userInput);
            } catch (IllegalArgumentException iae) {
                super.handleIllegalArgumentException(userInput, iae);
            } catch (NullPointerException npe) {
                super.handleNullPointerException(npe);
            }
        } while (!"Home".equalsIgnoreCase(userInput));
    }

    @Override
    public void selectOption(String userInput) {
        switch (ProspectSelectionOptions.valueOf(userInput)){
            case ADD:
                addNewProspect();
                break;
            case UPDATE:
                updateProspect();
                break;
            case CLEAR:
                currentPerson = null;
                break;
            case VIEWALL:
                viewAllAndSelect(EmploymentStatus.PROSPECT);
                break;
            case HOME:
                return;
            case EXIT:
                System.exit(0);
                return;
        }
    }

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
                setActiveProspect();
                System.out.println(currentPerson.getContactInfo().getName()+" selected.");
                break;
            } else {
                System.out.println("Please try again.");
            }
        } while (!"BACK".equalsIgnoreCase(input) && !"SELECT".equalsIgnoreCase(input));
    }

    private void addNewProspect(){
        createAndSetActiveNewProspect();
        addResume();
        addScore();
    }

    private void createAndSetActiveNewProspect() {
        HrContactInfo requestedInfo = this.inputAllContactInfo();
        Person newProspect = PersonHandler.createProspect(requestedInfo);
        currentPerson = newProspect;
        System.out.println("\nNew prospect, " + currentPerson.getContactInfo().getName() + ", added with ID: " +
                currentPerson.getId()+".");
    }

    private void addScore() {
        String input = "";
        do {
            System.out.println("\nPlease update prospect score. Enter [Add] to do so now or [Skip] to do so later.");
            input = this.getUserInput();
            if(!"skip".equalsIgnoreCase(input) && "add".equalsIgnoreCase(input)) {
                System.out.println("\nPlease enter score:");
                Double score = SanitizeTools.getEnforcedPositiveDoubleInput();
                currentPerson.setScore(score);
            } else if ("skip".equalsIgnoreCase(input)){
                System.out.println("\nScore update skipped.");
            } else {
                System.out.println("\nPlease try again.");
            }
        } while (!"skip".equalsIgnoreCase(input) && !"add".equalsIgnoreCase(input));
    }

    private void addResume() {
        System.out.println("\nPlease add resume. Enter [Skip] to add one later.");
        String resume = this.getUserInput();
        if (!"skip".equalsIgnoreCase(resume)){
        currentPerson.setResume(resume);
        System.out.println("\nResume added");
        } else {
            System.out.println("\nResume addition skipped.");
        }
    }

    private void updateProspect(){
        if (personWarehouse.getAllOfType(EmploymentStatus.PROSPECT).size()==0)
        {
            System.out.println("\nThere are no prospects currently in the system.");
        }
        else {
            if (currentPerson == null) {
                setActiveProspect();
            }
            updateProspectField();
        }
    }

    private void setActiveProspect() {
        String input;
        do {
            System.out.println("\nFind by [ID] or [Name]?");
            input = this.getUserInput();
        } while (!"ID".equalsIgnoreCase(input) && !"Name".equalsIgnoreCase(input));

        if ("ID".equalsIgnoreCase(input)){
            currentPerson=getPersonById(EmploymentStatus.PROSPECT);
        }
        else{
            currentPerson=getPersonByName(EmploymentStatus.PROSPECT);
        }
    }

    private void updateProspectField() {
        String input;
        do {
            System.out.println("\nSelected: \n"+currentPerson+"\nPlease pick from the list of update options.\nEnter [ BACK ] when your updates are complete.\n" +
            "[ HIRE ] [ SCORE ] [ RESUME ] [ ID ] [ INTERVIEW DATE ] [ NAME ] [ ADDRESS ] [ PHONE ] [ EMAIL ]");
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
                    LocalDate newIntDate = getEnforcedLocalDateInput();
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
                    System.out.println("The new phone number is ["+currentPerson.getContactInfo().getPhone()+"].");
                    break;
                case "hire":
                    hireEmployee();
                    System.out.println("Hired: \n"+currentPerson.toString());
                    currentPerson = null;
                    input = "back";
                    break;

                case "back":
                    return;
            }
        } while (!"back".equalsIgnoreCase(input));
    }

    public void hireEmployee() {
        inputNewEmployeeInfo();
    }

    public void inputNewEmployeeInfo() {
        System.out.print("Enter hire date (yyyy-mm-dd): ");
        LocalDate inputHireDate = getEnforcedLocalDateInput();
        System.out.println("Enter job title: ");
        String inputJobTitle = this.getUserInput();
        Compensation newCompensation = changeCompensation();
        currentPerson = PersonHandler.hire(currentPerson, inputHireDate, inputJobTitle, newCompensation);
    }

}
