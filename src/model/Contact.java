package model;

/**
 * Represents a contact in the system.
 */
public class Contact {
    /** The unique identifier of the contact. */
    private int contactId;

    /** The name of the contact. */
    private String contactName;

    /** The email address of the contact. */
    private String email;

    /**
     * Gets the unique identifier of the contact.
     * @return The contact ID.
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the unique identifier of the contact.
     * @param contactId The contact ID to set.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Gets the name of the contact.
     * @return The contact name.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the name of the contact.
     * @param contactName The contact name to set.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets the email address of the contact.
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the contact.
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
