package io.zipcoder.threedaystodeliver.humanresourceapp;

public class Compensation {

    public enum compensationType {
        Monthly,
        Hourly,
        Project
    }

    private double ptoUsed;
    private double payrate;
    private double bonus;
    private compensationType compensationType;
    private double ptoMaxPerYear;
    private double ptoRemaining;
    private boolean vision;
    private boolean dental;
    private boolean medical;
    private boolean prescription;
    private double retirementMatching;

    public void setPtoMaxPerYear(double ptoMaxPerYear) {
        this.ptoMaxPerYear = ptoMaxPerYear;
    }

    public double getPtoMaxPerYear() {
        return ptoMaxPerYear;
    }

    @Override
    public String toString() {

        String formattedEmployeeData = "";

        formattedEmployeeData += String.format("PAY: $%.2f per ", payrate);
        if(compensationType.Monthly.equals(compensationType)) {
            formattedEmployeeData += "month";
        }
        else if(compensationType.Hourly.equals(compensationType)){
            formattedEmployeeData += "hour";
        } else {
            formattedEmployeeData += "project";
        }

        formattedEmployeeData += String.format("\nBONUS: %.1f%%", bonus);
        formattedEmployeeData += String.format("\n401k MATCH: %.1f%%", retirementMatching);
        formattedEmployeeData += String.format("\nPTO: %.1f hours remaining out of %.1f max per year", getPtoRemaining(), ptoMaxPerYear);
        formattedEmployeeData += String.format("\nMEDICAL BENEFITS: MEDICAL: %s PRESCRIPTION: %s DENTAL: %s VISION: %s", formatBooleanYOrN(medical), formatBooleanYOrN(prescription), formatBooleanYOrN(dental), formatBooleanYOrN(vision));

        return formattedEmployeeData;
    }

    public void setUsePTO(double amount) {
        this.ptoUsed = amount;
    }

    public double getUsePTO() {
        return ptoUsed;
    }

    public void setPayrate(double payrate) {
        this.payrate = payrate;
    }

    public double getPtoRemaining() {

        ptoRemaining = ptoMaxPerYear - ptoUsed;

        return ptoRemaining;
    }

    public double getPTOUsedThisYear() {
        return ptoUsed;
    }


    public double getPayrate() {
        return payrate;
    }

    public void setCompensationType(Compensation.compensationType compensationType) {
        this.compensationType = compensationType;
    }

    public boolean getIsVision() {
        return vision;
    }

    public void setVision(boolean vision) {
        this.vision = vision;
    }

    public void setDental(boolean dental) {
        this.dental = dental;
    }

    public boolean isDental() {
        return dental;
    }

    public void setMedical(boolean medical) {
        this.medical = medical;
    }

    public boolean getIsMedical() {
        return medical;
    }

    public void setPrescription(boolean prescription) {
        this.prescription = prescription;
    }

    public boolean isPrescription() {
        return prescription;
    }

    public void setRetirementMatching(double retirementMatching) {
        this.retirementMatching = retirementMatching;
    }

    public double getRetirementMatching() {
        return retirementMatching;
    }

    public Compensation() {

    }

    public void setTypeAndAmount(compensationType compType, double payRate) {
        this.compensationType = compType;
        this.payrate = payRate;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public compensationType getCompensationType() {
        return this.compensationType;
    }

    private static String formatBooleanYOrN(boolean input) {
        if(input) {
            return "Y";
        }
        else {
            return "N";
        }
    }


}
