package global;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Class responsible for managing database connections.
 */
public class Database {

    // Database credentials
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/client_schedule?connectionTimeZone=SERVER";
    // Create a connection object
    private static Connection connection = null;

    /**
     * Retrieves the database connection.
     * If the connection is null, it establishes a new connection.
     * @return The database connection.
     */
    public static Connection getConnection(){
        if(connection == null)
            connect();
        return  connection;
    }
    /**
     * Closes the database connection.
     * @throws SQLException if a database access error occurs.
     */
    public static void closeConnection() throws SQLException {
        if(connection != null)
            connection.close();
    }

    /**
     * Establishes a connection to the database.
     */
    private static void connect() {
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            //System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Display success message
            //System.out.println("Connected to the database successfully!");

        } catch (SQLException | ClassNotFoundException e) {
            Main.dialogue("Error",e.getMessage());
        }
    }
}
