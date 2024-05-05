package model;

import global.Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * Represents an appointment in the system.
 */
public class Appointment {
    /** The unique identifier of the appointment. */
    private int appointmentId;

    /** The title of the appointment. */
    private String title;

    /** The description of the appointment. */
    private String description;

    /** The location of the appointment. */
    private String location;

    /** The type of the appointment. */
    private String type;

    /** The start time of the appointment. */
    private String start;

    /** The end time of the appointment. */
    private String end;

    /** The date when the appointment was created. */
    private String createDate;

    /** The user who created the appointment. */
    private String createdBy;

    /** The last update date of the appointment. */
    private String lastUpdate;

    /** The user who last updated the appointment. */
    private String lastUpdatedBy;

    /** The ID of the customer associated with the appointment. */
    private int customerId;

    /** The ID of the user associated with the appointment. */
    private int userId;

    /** The ID of the contact associated with the appointment. */
    private int contactId;

    /**
     * Gets the unique identifier of the appointment.
     * @return The appointment ID.
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets the unique identifier of the appointment.
     * @param appointmentId The appointment ID to set.
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Gets the title of the appointment.
     * @return The appointment title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the appointment.
     * @param title The appointment title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the appointment.
     * @return The appointment description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the appointment.
     * @param description The appointment description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the location of the appointment.
     * @return The appointment location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the appointment.
     * @param location The appointment location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the type of the appointment.
     * @return The appointment type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the appointment.
     * @param type The appointment type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the start time of the appointment.
     * @return The appointment start time.
     */
    public String getStart() {
        return Main.fromUTCToDefault(start);
    }

    /**
     * Sets the start time of the appointment.
     * @param start The appointment start time to set.
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Gets the end time of the appointment.
     * @return The appointment end time.
     */
    public String getEnd() {
        return  Main.fromUTCToDefault(end);
    }

    /**
     * Sets the end time of the appointment.
     * @param end The appointment end time to set.
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * Gets the creation date of the appointment.
     * @return The creation date.
     */
    public String getCreateDate() {
        return Main.fromUTCToDefault(createDate);
    }

    /**
     * Sets the creation date of the appointment.
     * @param createDate The creation date to set.
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the user who created the appointment.
     * @return The user who created the appointment.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the appointment.
     * @param createdBy The user who created the appointment.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the last update date of the appointment.
     * @return The last update date.
     */
    public String getLastUpdate() {
        return Main.fromUTCToDefault(lastUpdate);
    }

    /**
     * Sets the last update date of the appointment.
     * @param lastUpdate The last update date to set.
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the user who last updated the appointment.
     * @return The user who last updated the appointment.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated the appointment.
     * @param lastUpdatedBy The user who last updated the appointment.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Gets the ID of the customer associated with the appointment.
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the ID of the customer associated with the appointment.
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the ID of the user associated with the appointment.
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user associated with the appointment.
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the ID of the contact associated with the appointment.
     * @return The contact ID.
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the ID of the contact associated with the appointment.
     * @param contactId The contact ID to set.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Returns the start time in UTC format.
     *
     * @return The start time in UTC format.
     */
    public String getStartUTC() {
        return start;
    }

    /**
     * Returns the end time in UTC format.
     *
     * @return The end time in UTC format.
     */
    public String getEndUTC() {
        return end;
    }

    /**
     * Returns the create date in UTC format.
     *
     * @return The create date in UTC format.
     */
    public String getCreateDateUTC() {
        return createDate;
    }


    /**
     * Returns the last update time in UTC format.
     *
     * @return The last update time in UTC format.
     */
    public String getLastUpdateUTC() {
        return lastUpdate;
    }

    /**
     * Returns the month value of the start time.
     *
     * @return The month value of the start time.
     */
    public int getStartMonth(){
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getStart()));
        } catch (ParseException e) {
            Main.dialogue("Error",e.getMessage());
        }
        return cal.get(Calendar.MONTH);
    }

    /**
     * Returns the week of the year of the start time.
     *
     * @return The week of the year of the start time.
     */
    public int getStartWeekOfYear(){
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getStart()));
        } catch (ParseException e) {
            Main.dialogue("Error",e.getMessage());
        }
        return cal.get(Calendar.WEEK_OF_YEAR);
    }
}


