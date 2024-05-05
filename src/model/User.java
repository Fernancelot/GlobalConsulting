package model;

/**
 * Represents a user in the system.
 */
public class User {
    /** The unique identifier of the user. */
    private int userId;

    /** The username of the user. */
    private String userName;

    /** The password of the user. */
    private String password;

    /** The date when the user was created. */
    private String createDate;

    /** The user who created this user. */
    private String createdBy;

    /** The last update date of the user. */
    private String lastUpdate;

    /** The user who last updated this user. */
    private String lastUpdatedBy;

    /**
     * Gets the unique identifier of the user.
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier of the user.
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the username of the user.
     * @return The username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the user.
     * @param userName The username to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the password of the user.
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the creation date of the user.
     * @return The creation date.
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Sets the creation date of the user.
     * @param createDate The creation date to set.
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the user who created this user.
     * @return The user who created this user.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created this user.
     * @param createdBy The user who created this user.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the last update date of the user.
     * @return The last update date.
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the last update date of the user.
     * @param lastUpdate The last update date to set.
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the user who last updated this user.
     * @return The user who last updated this user.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated this user.
     * @param lastUpdatedBy The user who last updated this user.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}


