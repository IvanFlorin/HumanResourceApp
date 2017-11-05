package io.zipcoder.threedaystodeliver.humanresourceapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Person {

    // name and contact stuff
    private HrContactInfo contactInfo = new HrContactInfo();

    //current
    private String id="";
    private String title="";
    private EmploymentStatus employmentStatus;
    private Compensation compensation;
    private LocalDate hiredDate;
    private ArrayList<IncidentReport> incidentReports;

    //former
    private LocalDate terminationDate;
    private String reasonForTermination = "";
    private String exitInterview = "";

    //prospect
    private String resume = "";
    private LocalDate interviewDate;
    private double score = 0;

    @Override
    public String toString(){

////        private Compensation compensation=new Compensation();
        String returnMe = contactInfo.toString()+
                "ID:  " + id + "\n" +

                "Employment Status:  " + employmentStatus + "\n" +
                "Entrance Interview Date:  " + interviewDate + "\n" +
                "Resume Abstract:  " + resume + "\n" +
                String.format("Score:  %.2f\n", score);

        if (EmploymentStatus.EMPLOYEE == this.getEmploymentStatus()){
            returnMe += "Hire Date:  " + hiredDate + "\n";
            if (compensation!=null)
            {
                returnMe+= "Title:  " + title + "\n" +
                        (contactInfo.getName()+"'s Compensation:\n" + compensation.toString() + "\n\n");
            }
            else
            {
                returnMe+= "\n";
            }
        }

        if (EmploymentStatus.TERMINATED == this.getEmploymentStatus()){
            returnMe += "Termination Date:  " + terminationDate + "\n" +
                    "Reason For Termination:  " + reasonForTermination + "\n" +
                    "Exit Interview Notes:  " + exitInterview + "\n";
        }
        return (returnMe);
    }


    public void setTitle(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }

    public void setContactInfo(HrContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Compensation getCompensation() {
        return compensation;
    }

    public void setCompensation(Compensation compensation) {
        this.compensation = compensation;
    }

    public LocalDate getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(LocalDate hiredDate) {
        this.hiredDate = hiredDate;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getReasonForTermination() {
        return reasonForTermination;
    }

    public void setReasonForTermination(String reasonForTermination) {
        this.reasonForTermination = reasonForTermination;
    }

    public String getExitInterview() {
        return exitInterview;
    }

    public void setExitInterview(String exitInterview) {
        this.exitInterview = exitInterview;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public LocalDate getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }


    public HrContactInfo getContactInfo() {
        return contactInfo;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Enum getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public void addIncidentReport(IncidentReport incidentReport) {
        incidentReports.add(incidentReport);
    }

    public void removeIncidentReport(IncidentReport incidentReport) {
        incidentReports.remove(incidentReport);
    }
}
