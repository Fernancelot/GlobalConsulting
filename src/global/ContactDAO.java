package global;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Contact;

/**
 * This class provides data access methods for managing contacts in the system.
 */
public class ContactDAO {

    /**
     * Retrieves all contacts from the database.
     *
     * @return A list of all contacts.
     */
    public static List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            String sql = "SELECT * FROM contacts";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Contact contact = new Contact();
                contact.setContactId(rs.getInt("Contact_ID"));
                contact.setContactName(rs.getString("Contact_Name"));
                contact.setEmail(rs.getString("Email"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            Main.dialogue("Error",e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                Main.dialogue("Error",e.getMessage());
            }
        }
        return contacts;
    }
    /**
     * Retrieves the names of all contacts from the database.
     *
     * @return A list of contact names.
     */
    public static List<String> getContactNames() {
        List<Contact> contacts = getAllContacts();
        List<String> names = new ArrayList<>();
        for (Contact contact : contacts) {
            names.add(contact.getContactName());
        }
        return names;
    }
    /**
     * Retrieves the ID of a contact by its name from the database.
     *
     * @param contactName The name of the contact to retrieve the ID for.
     * @return The ID of the contact.
     */
    public static int getContactID(String contactName) {
        List<Contact> contacts = getAllContacts();
        for (Contact contact : contacts) {
            if (contactName.equals(contact.getContactName()))
                return contact.getContactId();
        }
        return -1;
    }

    /**
     * Retrieves the name of a contact by its ID from the database.
     *
     * @param contactId The ID of the contact to retrieve the name for.
     * @return The name of the contact.
     */
    public static String getContactName(int contactId) {
        List<Contact> contacts = getAllContacts();
        for (Contact contact : contacts) {
            if (contact.getContactId() == contactId)
                return contact.getContactName();
        }
        return null;
    }
}

