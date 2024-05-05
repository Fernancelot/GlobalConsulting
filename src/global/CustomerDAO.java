package global;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 * This class provides data access methods for managing customers in the system.
 */
public class CustomerDAO {
    private static Connection con = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    /**
     * Retrieves a customer by their ID from the database.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return The customer object if found, otherwise null.
     */
    public static Customer getCustomer(int customerId) {

        try {
            con = Database.getConnection();
            String query = "SELECT * FROM customers WHERE Customer_ID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return extractCustomerFromResultSet(rs);
            }else{
                return null;
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

        return null;
    }

    /**
     * Retrieves all customers from the database.
     *
     * @return A list of all customers.
     */
    public static List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();

        try {
            con = Database.getConnection();
            String query = "SELECT * FROM customers";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = extractCustomerFromResultSet(rs);
                customers.add(customer);
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

        return customers;
    }


    /**
     * Saves a new customer to the database.
     *
     * @param customer The customer object to save.
     * @return True if the operation is successful, otherwise false.
     */
    public static boolean saveCustomer(Customer customer) {
        Savable object = (obj) -> {
            Customer cust = (Customer) obj;
            try {
                con = Database.getConnection();
                String query = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(query);
                ps.setString(1, cust.getCustomerName());
                ps.setString(2, cust.getAddress());
                ps.setString(3, cust.getPostalCode());
                ps.setString(4, cust.getPhone());
                ps.setString(5, cust.getCreateDateUTC());
                ps.setString(6, cust.getCreatedBy());
                ps.setString(7, cust.getLastUpdateUTC());
                ps.setString(8, cust.getLastUpdatedBy());
                ps.setInt(9, cust.getDivisionId());

                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Main.dialogue("Error", e.getMessage());
            } finally {
                try {
                    if (ps != null) ps.close();
                } catch (SQLException e) {
                    Main.dialogue("Error", e.getMessage());
                }
            }
            return false;
        };
        return object.save(customer);
    }


    /**
     * Updates an existing customer in the database.
     *
     * @param customer The customer object to update.
     * @return True if the operation is successful, otherwise false.
     */
    public static boolean updateCustomer(Customer customer) {
        try {
            con = Database.getConnection();
            String query = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, " +
                    "Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? " +
                    "WHERE Customer_ID = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPostalCode());
            ps.setString(4, customer.getPhone());
            ps.setString(5, customer.getCreateDateUTC());
            ps.setString(6, customer.getCreatedBy());
            ps.setString(7, customer.getLastUpdateUTC());
            ps.setString(8, customer.getLastUpdatedBy());
            ps.setInt(9, customer.getDivisionId());
            ps.setInt(10, customer.getCustomerId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Main.dialogue("Error",e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                Main.dialogue("Error",e.getMessage());
            }
        }
        return false;
    }

    /**
     * Deletes a customer from the database.
     *
     * @param customerId The ID of the customer to delete.
     * @return True if the operation is successful, otherwise false.
     */
    public static boolean deleteCustomer(int customerId) {
        try {
            con = Database.getConnection();
            String query = "DELETE FROM customers WHERE Customer_ID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, customerId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Main.dialogue("Error",e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                Main.dialogue("Error",e.getMessage());
            }
        }
        return false;
    }


    /**
     * Extracts a customer object from the result set.
     *
     * @param rs The result set containing customer data.
     * @return The extracted customer object.
     * @throws SQLException If an SQL exception occurs.
     */
    private static Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("Customer_ID"));
        customer.setCustomerName(rs.getString("Customer_Name"));
        customer.setAddress(rs.getString("Address"));
        customer.setPostalCode(rs.getString("Postal_Code"));
        customer.setPhone(rs.getString("Phone"));
        customer.setCreateDate(rs.getString("Create_Date"));
        customer.setCreatedBy(rs.getString("Created_By"));
        customer.setLastUpdate(rs.getString("Last_Update"));
        customer.setLastUpdatedBy(rs.getString("Last_Updated_By"));
        customer.setDivisionId(rs.getInt("Division_ID"));
        return customer;
    }

    /**
     * Retrieves the names of all customers from the database.
     *
     * @return A list of customer names.
     */
    public static List<String> getCustomersNames() {
        List<Customer> customers = getCustomers();
        List<String> customerNames = new ArrayList<>();
        for (Customer customer : customers) {
            customerNames.add(customer.getCustomerName());
        }
        return customerNames;
    }

    /**
     * Retrieves the ID of a customer by their name from the database.
     *
     * @param customerName The name of the customer.
     * @return The ID of the customer.
     */
    public static int getCustomerID(String customerName) {
        List<Customer> customers = getCustomers();
        for (Customer customer : customers) {
            if (customerName.equals(customer.getCustomerName())) {
                return customer.getCustomerId();
            }
        }
        return -1;
    }
}

