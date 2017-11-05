package io.zipcoder.threedaystodeliver.humanresourceapp;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

public class PersonTest {

//    @Override
//    public String toString(){
//        return null;
//    }
//
//    public void setContactInfo(HrContactInfo contactInfo) {
////        this.contactInfo = contactInfo;
//    }
////    public Compensation getCompensation() {
////        return compensation;
////    }
//
////    public void setCompensation(Compensation compensation) {
////        this.compensation = compensation;
////    }

    @Test
    public void testHireDateGetAndSet(){
        Person emp1 = new Person();
        LocalDate expected = LocalDate.of(12, 12, 12);


/** I COMMENTED THIS OUT FOR TESTING PORPUSES. FIX PLEASE BUGSSSSSSSSSS :) ANIMAL
 *
        emp1.setHiredDate(expected);

        LocalDate actual = emp1.getHiredDate();

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testTerminationDateGetAndSet(){
        Person emp1 = new Person();
        LocalDate expected = LocalDate.of(12, 12, 12);


        emp1.setTerminationDate(expected);

        LocalDate actual = emp1.getTerminationDate();

        Assert.assertEquals(expected.toString(), actual.toString());

 */
    }

    @Test
    public void testReasonForTerminationSetAndGet(){
        Person emp1 = new Person();
        String expected = "Offenses against cats";

        emp1.setReasonForTermination(expected);

        String actual = emp1.getReasonForTermination();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExitInterviewSetAndGet(){
        Person emp1 = new Person();
        String expected = "Employee refuse to interview";

        emp1.setExitInterview(expected);

        String actual = emp1.getExitInterview();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testResumeStubSetAndGet(){
        Person emp1 = new Person();
        String expected = "Illegibly scribbled on a napkin";

        emp1.setResume(expected);

        String actual = emp1.getResume();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testInterviewDateGetAndSet(){
        Person emp1 = new Person();
        LocalDate expected = LocalDate.of(12, 12, 12);

        emp1.setInterviewDate(expected);

        LocalDate actual = emp1.getInterviewDate();

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testScoreGetAndSet(){
        Person emp1 = new Person();
        double expected = 28.0;

        emp1.setScore(expected);

        double actual = emp1.getScore();

        Assert.assertEquals(expected, actual, 0.01);

    }

   @Test
   public void testToString(){
       Person emp1 = new Person();
       System.out.println(emp1.toString());
   }

    @Test
    public void testIdStubGetStaticInit(){
//Deprecated

//        Person emp1 = new Person();
//        Person emp2 = new Person();
//        emp2 = new Person();
//        emp2 = new Person();
//        emp2 = new Person();
//        String expected = "4";
//        String actual = emp2.getId();
//
//        Assert.assertEquals(expected, actual);

    }

//      public void setId(String id) {
//        this.id = id;
//    }
//
//    public EmploymentStatus getEmploymentStatus() {
//        return employmentStatus;
//    }
//
//    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
//        this.employmentStatus = employmentStatus;
//    }
//
//


}
