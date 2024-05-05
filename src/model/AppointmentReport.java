package model;

import global.Main;

/**
 * Represents an appointment report.
 */
public class AppointmentReport {
    private int appointmentId;
    private String title;
    private String type;
    private String description;
    private String startTime;
    private String endTime;
    private int customerId;

    /**
     * Retrieves the appointment ID.
     * @return The appointment ID.
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets the appointment ID.
     * @param appointmentId The appointment ID to set.
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Retrieves the title of the appointment.
     * @return The title of the appointment.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the appointment.
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the type of the appointment.
     * @return The type of the appointment.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the appointment.
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retrieves the description of the appointment.
     * @return The description of the appointment.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the appointment.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the start time of the appointment.
     * @return The start time of the appointment.
     */
    public String getStartTime() {
        return Main.fromUTCToDefault(startTime);
    }

    /**
     * Sets the start time of the appointment.
     * @param startTime The start time to set.
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Retrieves the end time of the appointment.
     * @return The end time of the appointment.
     */
    public String getEndTime() {
        return Main.fromUTCToDefault(endTime);
    }

    /**
     * Sets the end time of the appointment.
     * @param endTime The end time to set.
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Retrieves the customer ID associated with the appointment.
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID associated with the appointment.
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}