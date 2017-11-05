package io.zipcoder.threedaystodeliver.humanresourceapp.Utils;

import io.zipcoder.threedaystodeliver.humanresourceapp.Compensation;
import io.zipcoder.threedaystodeliver.humanresourceapp.EmploymentStatus;
import io.zipcoder.threedaystodeliver.humanresourceapp.HrContactInfo;
import io.zipcoder.threedaystodeliver.humanresourceapp.Person;
import io.zipcoder.threedaystodeliver.humanresourceapp.PersonFactory;
import io.zipcoder.threedaystodeliver.humanresourceapp.StreetAddress;

import java.time.LocalDate;
import java.util.Random;

/**
 * This is also mostly Leon's stuff
 * */

public class RandomUtilsHR {

    private static final Random random = new Random();

    public static Float createFloat(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }

    public static Integer createInteger(Integer min, Integer max) {
        return createFloat(min, max).intValue();
    }

    public static Boolean createBoolean(Integer skew){
        int seed = createInteger(0, 100);
        if (skew > seed){
            return true;
        } else { return false; }
    }

    public static Character createCharacter(char min, char max) {
        return (char) createInteger((int) min, (int) max).intValue();
    }

    public static String createString(char min, char max, int stringLength) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringLength; i++) {
            sb.append(createCharacter(min, max));
        }
        return sb.toString();
    }

    public static String createID(int idLength){
        String id = "";
        for (int i = 0; i < idLength; i++) {
            id += i;
        }
        return id;
    }

    public static void createPerson() {
        //take name
        //random employment type
        //random addresses
        String name = Names.nameList[createInteger(0, Names.nameList.length)];
        StreetAddress address = new StreetAddress((createInteger(100, 99999) + " " + createString('a', 'z', 10)),
                createString('a', 'z', 10),
                createString('a', 'z', 10),
                createString('a', 'z', 10),
                (createInteger(10000, 99999)).toString());
        String phone = createString('0', '9', 10);
        String email = name + "@" + "ranchycorp.com";
        HrContactInfo info = new HrContactInfo(name, address, phone, email);
        Person randomPerson = PersonFactory.createPerson(info);
        randomPerson.setEmploymentStatus(EmploymentStatus.values()[createInteger(0, EmploymentStatus.values().length)]);
        if (EmploymentStatus.PROSPECT == randomPerson.getEmploymentStatus()){
            randomPerson.setScore(createFloat(0, 100));
            randomPerson.setResume(createString('a','z', 20));
            randomPerson.setInterviewDate(LocalDate.of(createInteger(1500, 2500), createInteger(1, 12), createInteger(1, 28)));
        }
        if (EmploymentStatus.EMPLOYEE == randomPerson.getEmploymentStatus()) {
            randomPerson.setTitle(createString('A', 'Z', 10));
            Compensation comp = new Compensation();
            randomPerson.setCompensation(comp);
            randomPerson.getCompensation().setPayrate((double) createFloat(200, 2000000));
            randomPerson.getCompensation().setCompensationType(Compensation.compensationType.values()[createInteger(0, Compensation.compensationType.values().length)]);
            randomPerson.setHiredDate(LocalDate.of(createInteger(1500, 2500), createInteger(1, 12), createInteger(1, 28)));
        }
    }
}
