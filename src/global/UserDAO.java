package global;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * This class provides data access methods for managing user information in the database.
 */
public class UserDAO {

    private static Connection con = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    /**
     * Retrieves a list of all users from the database.
     *
     * @return A list of User objects representing all users in the database.
     */
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            String sql = "SELECT * FROM users";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("User_ID"));
                user.setUserName(rs.getString("User_Name"));
                user.setPassword(rs.getString("Password"));
                user.setCreateDate(rs.getString("Create_Date"));
                user.setCreatedBy(rs.getString("Created_By"));
                user.setLastUpdate(rs.getString("Last_Update"));
                user.setLastUpdatedBy(rs.getString("Last_Updated_By"));
                users.add(user);
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
        return users;
    }
    /**
     * Saves a new user to the database.
     *
     * @param user The User object to be saved.
     */
    public static void saveUser(User user) {
        Savable object = (obj) -> {
            User u = (User) obj;
            try {
                con = Database.getConnection();
                String query = "INSERT INTO users (User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(query);
                ps.setString(1, u.getUserName());
                ps.setString(2, u.getPassword());
                ps.setObject(3, u.getCreateDate());
                ps.setString(4, u.getCreatedBy());
                ps.setObject(5, u.getLastUpdate());
                ps.setString(6, u.getLastUpdatedBy());

                int rowsInserted = ps.executeUpdate();

            } catch (SQLException e) {
                Main.dialogue("Error",e.getMessage());
            } finally {
                try {
                    if (ps != null) ps.close();
                } catch (SQLException e) {
                    Main.dialogue("Error",e.getMessage());
                }
            }
            return true;
        };
     object.save(user);
    }
    /**
     * Retrieves a user from the database based on the provided username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object corresponding to the provided username, or null if not found.
     */
    public static User getUser(String username) {
        User user = null;
        try {
            if(con == null)
                con = Database.getConnection();

            String query = "SELECT * FROM users WHERE User_Name = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("User_ID"));
                user.setUserName(rs.getString("User_Name"));
                user.setPassword(rs.getString("Password"));
                user.setCreatedBy(rs.getString("Created_By"));
                user.setCreateDate(rs.getString("Create_Date"));
                user.setLastUpdate(rs.getString("Last_Update"));
                user.setLastUpdatedBy(rs.getString("Last_Updated_By"));
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

        return user;
    }

    /**
     * Deletes a user from the database based on the provided username.
     *
     * @param username The username of the user to delete.
     */
    public static void deleteUser(String username) {
        try {
            con = Database.getConnection();
            String query = "DELETE FROM users WHERE User_Name = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);

             int rowsDeleted = ps.executeUpdate();

        } catch (SQLException e) {
            Main.dialogue("Error",e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                //Database.closeConnection();
            } catch (SQLException e) {
                Main.dialogue("Error",e.getMessage());
            }
        }
    }
    /**
     * Retrieves a list of usernames for all users in the database.
     *
     * @return A list of strings representing the usernames of all users.
     */
    public static List<String> getUserNames() {
        List<User> users = getAllUsers();
        List<String> names = new ArrayList<>();
        for (int i=0; i<users.size(); i++){
            names.add(users.get(i).getUserName());
        }
        return names;
    }
    /**
     * Retrieves the username of a user based on the provided user ID.
     *
     * @param userID The ID of the user to retrieve the username for.
     * @return The username corresponding to the provided user ID, or null if not found.
     */
    public static String getUserName(int userID) {
        List<User> users = getAllUsers();
        for (int i=0; i<users.size(); i++){
             if(users.get(i).getUserId() == userID)
                 return users.get(i).getUserName();
        }
        return null;
    }
    /**
     * Retrieves the user ID of a user based on the provided username.
     *
     * @param userName The username of the user to retrieve the ID for.
     * @return The user ID corresponding to the provided username, or -1 if not found.
     */
    public static int getUserId(String userName) {
        List<User> users = getAllUsers();
        for (int i=0; i<users.size(); i++){
            if(userName.equals(users.get(i).getUserName()))
                return users.get(i).getUserId();
        }
        return -1;
    }
}
