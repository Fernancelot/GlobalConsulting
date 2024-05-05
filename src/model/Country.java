package model;

/**
 * Represents a country in the system.
 */
public class Country {
    /** The unique identifier of the country. */
    private int countryId;

    /** The name of the country. */
    private String country;

    /** The date when the country was created. */
    private String createDate;

    /** The user who created the country. */
    private String createdBy;

    /** The last update date of the country. */
    private String lastUpdate;

    /** The user who last updated the country. */
    private String lastUpdatedBy;

    /**
     * Gets the unique identifier of the country.
     * @return The country ID.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets the unique identifier of the country.
     * @param countryId The country ID to set.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Gets the name of the country.
     * @return The country name.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the name of the country.
     * @param country The country name to set.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the creation date of the country.
     * @return The creation date.
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Sets the creation date of the country.
     * @param createDate The creation date to set.
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the user who created the country.
     * @return The user who created the country.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the country.
     * @param createdBy The user who created the country.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the last update date of the country.
     * @return The last update date.
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the last update date of the country.
     * @param lastUpdate The last update date to set.
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the user who last updated the country.
     * @return The user who last updated the country.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated the country.
     * @param lastUpdatedBy The user who last updated the country.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
