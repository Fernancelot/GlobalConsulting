package global;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Appointment;


/**
 * This class provides data access methods for managing appointments in the system.
 */
public class AppointmentDAO {
    /** The connection to the database. */
    private static Connection con;

    /** The prepared statement for executing SQL queries. */
    private static PreparedStatement ps;

    /** The result set containing the query results. */
    private static ResultSet rs;


    /**
     * Retrieves all appointments from the database.
     *
     * @return A list of all appointments.
     */
    public static List<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try {
            con = Database.getConnection();
            String query = "SELECT * FROM appointments";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Appointment appointment = extractAppointmentFromResultSet(rs);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            Main.dialogue("Error",e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                Main.dialogue("Error",e.getMessage());
            }
        }

        return appointments;
    }
    /**
     * Retrieves a specific appointment by its ID from the database.
     *
     * @param appointmentId The ID of the appointment to retrieve.
     * @return The appointment with the specified ID.
     */
    public static Appointment getAppointment(int appointmentId) {
        Appointment appointment = null;
        try {
            con = Database.getConnection();
            String query = "SELECT * FROM appointments WHERE Appointment_ID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, appointmentId);
            rs = ps.executeQuery();
            if (rs.next()) {
                appointment = extractAppointmentFromResultSet(rs);
            }
        } catch (SQLException e) {
            Main.dialogue("Error",e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                Main.dialogue("Error",e.getMessage());
            }
        }

        return appointment;
    }
    /**
     * Saves a new appointment to the database.
     *
     * @param appointment The appointment to save.
     * @return True if the appointment was saved successfully, false otherwise.
     */
    public static boolean saveAppointment(Appointment appointment) {
        Savable object = (obj) -> {
            Appointment app = (Appointment) obj;
            try {
                con = Database.getConnection();
                String query = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(query);
                ps.setString(1, app.getTitle());
                ps.setString(2, app.getDescription());
                ps.setString(3, app.getLocation());
                ps.setString(4, app.getType());
                ps.setString(5, app.getStartUTC());
                ps.setString(6, app.getEndUTC());
                ps.setString(7, app.getCreateDateUTC());
                ps.setString(8, app.getCreatedBy());
                ps.setString(9, app.getLastUpdateUTC());
                ps.setString(10, app.getLastUpdatedBy());
                ps.setInt(11, app.getCustomerId());
                ps.setInt(12, app.getUserId());
                ps.setInt(13, app.getContactId());

                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Main.dialogue("Error", e.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                } catch (SQLException e) {
                    Main.dialogue("Error", e.getMessage());
                }
            }
            return false;
        };
        return object.save(appointment);
    }

    /**
     * Updates an existing appointment in the database.
     *
     * @param appointment The appointment to update.
     * @return True if the appointment was updated successfully, false otherwise.
     */
    public static boolean updateAppointment(Appointment appointment) {
        try {
            con = Database.getConnection();
            String query = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Create_Date=?, Created_By=?, Last_Update=?, Last_Updated_By=?, Customer_ID=?, User_ID=?, Contact_ID=? WHERE Appointment_ID=?";
            ps = con.prepareStatement(query);
            ps.setString(1, appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3, appointment.getLocation());
            ps.setString(4, appointment.getType());
            ps.setString(5, appointment.getStart());
            ps.setString(6, appointment.getEnd());
            ps.setString(7, appointment.getCreateDate());
            ps.setString(8, appointment.getCreatedBy());
            ps.setString(9, appointment.getLastUpdate());
            ps.setString(10, appointment.getLastUpdatedBy());
            ps.setInt(11, appointment.getCustomerId());
            ps.setInt(12, appointment.getUserId());
            ps.setInt(13, appointment.getContactId());
            ps.setInt(14, appointment.getAppointmentId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Main.dialogue("Error",e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                Main.dialogue("Error",e.getMessage());
            }
        }
        return false;
    }
    /**
     * Deletes a specific appointment by its ID from the database.
     *
     * @param appointmentId The ID of the appointment to delete.
     * @return True if the appointment was deleted successfully, false otherwise.
     */
    public static boolean deleteAppointment(int appointmentId) {
        try {
            con = Database.getConnection();
            String query = "DELETE FROM appointments WHERE Appointment_ID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, appointmentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Main.dialogue("Error",e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                Main.dialogue("Error",e.getMessage());
            }
        }
        return false;
    }
    /**
     * Deletes all appointments associated with a specific customer from the database.
     *
     * @param customerId The ID of the customer whose appointments should be deleted.
     * @return True if the appointments were deleted successfully, false otherwise.
     */
    public static boolean deleteAppointmentByCustomerID(int customerId) {
        try {
            con = Database.getConnection();
            String query = "DELETE FROM appointments WHERE Customer_ID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, customerId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Main.dialogue("Error",e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                Main.dialogue("Error",e.getMessage());
            }
        }
        return false;
    }
    /**
     * Extracts an appointment object from the result set obtained from a database query.
     *
     * @param rs The result set containing the appointment data.
     * @return An appointment object extracted from the result set.
     * @throws SQLException If an SQL error occurs while extracting the appointment data.
     */
    private static Appointment extractAppointmentFromResultSet(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(rs.getInt("Appointment_ID"));
        appointment.setTitle(rs.getString("Title"));
        appointment.setDescription(rs.getString("Description"));
        appointment.setLocation(rs.getString("Location"));
        appointment.setType(rs.getString("Type"));
        appointment.setStart(rs.getString("Start"));
        appointment.setEnd(rs.getString("End"));
        appointment.setCreateDate(rs.getString("Create_Date"));
        appointment.setCreatedBy(rs.getString("Created_By"));
        appointment.setLastUpdate(rs.getString("Last_Update"));
        appointment.setLastUpdatedBy(rs.getString("Last_Updated_By"));
        appointment.setCustomerId(rs.getInt("Customer_ID"));
        appointment.setUserId(rs.getInt("User_ID"));
        appointment.setContactId(rs.getInt("Contact_ID"));
        return appointment;
    }
}
