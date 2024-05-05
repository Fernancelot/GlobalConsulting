package model;

/**
 * Represents a report containing planning type, month, and count.
 */
public class Report {
    /** The planning type of the report. */
    private String planningType;

    /** The month of the report. */
    private int month;

    /** The count of the report. */
    private int count;

    /**
     * Constructs a new Report object with the specified planning type, month, and count.
     * @param planningType The planning type of the report.
     * @param month The month of the report.
     * @param count The count of the report.
     */
    public Report(String planningType, int month, int count) {
        this.planningType = planningType;
        this.month = month;
        this.count = count;
    }

    /**
     * Gets the planning type of the report.
     * @return The planning type.
     */
    public String getPlanningType() {
        return planningType;
    }

    /**
     * Sets the planning type of the report.
     * @param planningType The planning type to set.
     */
    public void setPlanningType(String planningType) {
        this.planningType = planningType;
    }

    /**
     * Gets the month name of the report.
     * @return The month.
     */
    public String getMonth() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return months[month-1];
    }

    /**
     * Sets the month of the report.
     * @param month The month to set.
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Gets the count of the report.
     * @return The count.
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count of the report.
     * @param count The count to set.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Gets the month of the report as a number.
     * @return The month.
     */
    public int getIntegerMonth() {
        return month;
    }
}
