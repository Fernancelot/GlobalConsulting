package model;

public class CustomerSummary {
        /** The planning type of the report. */
    private String divisionName;

    /** The month of the report. */
    private int summaryID;

    /** The count of the report. */
    private int frequency;

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getSummaryID() {
        return summaryID;
    }

    public CustomerSummary() {
        divisionName = "";
        summaryID = -1;
        frequency = 1;
    }


    public void setSummaryID(int summaryID) {
        this.summaryID = summaryID;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    public void incrementFrequency(){
        this.frequency ++;
    }
}
