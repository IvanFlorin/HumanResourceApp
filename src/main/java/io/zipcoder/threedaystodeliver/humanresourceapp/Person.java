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
    private ArrayList<IncidentReport> incidentReports = new ArrayList<>(10);

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
                "Employee ID:  " + id + "\n" +
                "Title:  " + title + "\n" +
                "Employment Status:  " + employmentStatus + "\n" +
                "Entrance Interview Date:  " + interviewDate + "\n" +
                "Resume Abstract:  " + resume + "\n" +
                "Hire Date:  " + hiredDate + "\n" +
                "Termination Date:  " + terminationDate + "\n" +
                "Reason For Termination:  " + reasonForTermination + "\n" +
                "Exit Interview Notes:  " + exitInterview + "\n" +
                "Employee Score:  " + score + "\n\n";

        if (compensation!=null)
        {
            returnMe+= (contactInfo.getName()+"'s Compensation:\n" + compensation.toString() + "\n\n");
        }
        else
        {
            returnMe+= "\n";
        }

        return (returnMe);

    }

    public static String printProspectReportHeader() {
        String report = String.format("%-20s| %-10s| %-10s| %-30s| %-15s", "Name", "ID", "Score", "Resume", "Interview Date");
        char[] chars = new char[93];
        Arrays.fill(chars, '_');
        String line = new String(chars);
        report += "\n" + line;
        return report;
    }

    public String printProspectForReport() {
        String report = String.format("%-20s| %-10s| %-10.2f| %-30s| %-15s", getContactInfo().getName(), getId(), getScore(), getResume(), getInterviewDate());
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

    public String printEmployeeForReport() {
        String report = String.format("%-20s| %-10s| %-20s| $%-10.2f| %-23s| %-15s", getContactInfo().getName(), getId(), getTitle(), getCompensation().getPayrate(), getCompensation().getCompensationType(), getHiredDate());
        return report;
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
