package io.zipcoder.threedaystodeliver.humanresourceapp.menus;

import io.zipcoder.threedaystodeliver.humanresourceapp.EmploymentStatus;
import io.zipcoder.threedaystodeliver.humanresourceapp.Person;
import io.zipcoder.threedaystodeliver.humanresourceapp.PersonWarehouse;

import java.util.ArrayList;

public class ReportingMenu extends Menu{

    enum ReportingSelectionOptions {ALL, PROSPECT, EMPLOYEE, HOME, EXIT}

    public static final ReportingMenu INSTANCE = new ReportingMenu();

    private ReportingMenu(){
        super(ReportingSelectionOptions.values());
    }

    @Override
    public void selectOption(String userInput) {
        switch (ReportingSelectionOptions.valueOf(userInput)){
            case PROSPECT:
                PersonWarehouse.getInstance().printAllOfType(EmploymentStatus.PROSPECT);
                break;
            case EMPLOYEE:
                PersonWarehouse.getInstance().printAllOfType(EmploymentStatus.EMPLOYEE);
                break;
            case ALL:
                PersonWarehouse.getInstance().printAllOfType(EmploymentStatus.PROSPECT);
                System.out.println();
                PersonWarehouse.getInstance().printAllOfType(EmploymentStatus.EMPLOYEE);
                break;
            case HOME:
                return;
            case EXIT:
                System.exit(0);
                return;
        }
    }


}
