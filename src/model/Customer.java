package model;

import global.Main;

/**
 * Represents a customer in the system.
 */
public class Customer {
    /** The unique identifier of the customer. */
    private int customerId;

    /** The name of the customer. */
    private String customerName;

    /** The address of the customer. */
    private String address;

    /** The postal code of the customer's address. */
    private String postalCode;

    /** The phone number of the customer. */
    private String phone;

    /** The date when the customer was created. */
    private String createDate;

    /** The user who created the customer. */
    private String createdBy;

    /** The last update date of the customer. */
    private String lastUpdate;

    /** The user who last updated the customer. */
    private String lastUpdatedBy;

    /** The ID of the division to which the customer belongs. */
    private int divisionId;

    /**
     * Constructs a new Customer object with default values.
     */
    public Customer() {
        this.customerId = -1;
        this.customerName = "";
        this.address = "";
        this.postalCode = "";
        this.phone = "";
        this.createDate = "";
        this.createdBy = "";
        this.lastUpdate = "";
        this.lastUpdatedBy = "";
        this.divisionId = -1;
    }

    /**
     * Gets the unique identifier of the customer.
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the unique identifier of the customer.
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the name of the customer.
     * @return The customer name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the name of the customer.
     * @param customerName The customer name to set.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the address of the customer.
     * @return The customer address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer.
     * @param address The customer address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the postal code of the customer's address.
     * @return The postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the customer's address.
     * @param postalCode The postal code to set.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the phone number of the customer.
     * @return The phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the customer.
     * @param phone The phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the creation date of the customer.
     * @return The creation date.
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Sets the creation date of the customer.
     * @param createDate The creation date to set.
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the user who created the customer.
     * @return The user who created the customer.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the customer.
     * @param createdBy The user who created the customer.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the last update date of the customer.
     * @return The last update date.
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the last update date of the customer.
     * @param lastUpdate The last update date to set.
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the user who last updated the customer.
     * @return The user who last updated the customer.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated the customer.
     * @param lastUpdatedBy The user who last updated the customer.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Gets the ID of the division to which the customer belongs.
     * @return The division ID.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Sets the ID of the division to which the customer belongs.
     * @param divisionId The division ID to set.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Returns the create date in default time zone format.
     *
     * @return The create date in default time zone format.
     */
    public String getCreateDateUTC() {
        return Main.fromUTCToDefault(createDate);
    }
    /**
     * Returns the last update time in default time zone format.
     *
     * @return The last update time in default time zone format.
     */
    public String getLastUpdateUTC() {
        return Main.fromUTCToDefault(lastUpdate);
    }
}

