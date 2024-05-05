package model;

/**
 * Represents a first-level division entity in the system.
 */
public class FirstDivision {
    /** The unique identifier of the division. */
    private int divisionId;

    /** The name of the division. */
    private String division;

    /** The date when the division was created. */
    private String createDate;

    /** The user who created the division. */
    private String createdBy;

    /** The last update date of the division. */
    private String lastUpdate;

    /** The user who last updated the division. */
    private String lastUpdatedBy;

    /** The ID of the country to which the division belongs. */
    private int countryId;

    /**
     * Gets the unique identifier of the division.
     * @return The division ID.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Sets the unique identifier of the division.
     * @param divisionId The division ID to set.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Gets the name of the division.
     * @return The division name.
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets the name of the division.
     * @param division The division name to set.
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Gets the creation date of the division.
     * @return The creation date.
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Sets the creation date of the division.
     * @param createDate The creation date to set.
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the user who created the division.
     * @return The user who created the division.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the division.
     * @param createdBy The user who created the division.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the last update date of the division.
     * @return The last update date.
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the last update date of the division.
     * @param lastUpdate The last update date to set.
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the user who last updated the division.
     * @return The user who last updated the division.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated the division.
     * @param lastUpdatedBy The user who last updated the division.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Gets the ID of the country to which the division belongs.
     * @return The country ID.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets the ID of the country to which the division belongs.
     * @param countryId The country ID to set.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
