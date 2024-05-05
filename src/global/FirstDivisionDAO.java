package global;
import model.Country;
import model.FirstDivision;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides data access methods for managing first-level divisions in the system.
 */
public class FirstDivisionDAO {

    /**
     * Retrieves all first-level divisions from the database.
     *
     * @return A list of all first-level divisions.
     */
    public static List<FirstDivision> getAllFirstDivisions() {
        List<FirstDivision> firstDivisions = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            String sql = "SELECT * FROM first_level_divisions";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                FirstDivision firstDivision = new FirstDivision();
                firstDivision.setDivisionId(rs.getInt("Division_ID"));
                firstDivision.setDivision(rs.getString("Division"));
                firstDivision.setCreateDate(rs.getString("Create_Date"));
                firstDivision.setCreatedBy(rs.getString("Created_By"));
                firstDivision.setLastUpdate(rs.getString("Last_Update"));
                firstDivision.setLastUpdatedBy(rs.getString("Last_Updated_By"));
                firstDivision.setCountryId(rs.getInt("Country_ID"));
                firstDivisions.add(firstDivision);
            }
        } catch (Exception e) {
            Main.dialogue("Error",e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                Main.dialogue("Error",e.getMessage());
            }
        }
        return firstDivisions;
    }

    /**
     * Retrieves the division names for a specific country from the database.
     *
     * @param countryID The ID of the country.
     * @return A list of division names for the specified country.
     */
    public static List<String> getDivisionNames(String countryID) {
        List<FirstDivision> divs = getAllFirstDivisions();
        List<String> names = new ArrayList<>();
        for (FirstDivision div : divs) {
            if (countryID.equals(div.getCountryId() + ""))
                names.add(div.getDivision());
        }
        return names;
    }
    /**
     * Retrieves a specific division for a given country and division name from the database.
     *
     * @param countryID    The ID of the country.
     * @param divisionName The name of the division.
     * @return The division object if found, otherwise null.
     */
    public static FirstDivision getDivision(String countryID, String divisionName) {
        List<FirstDivision> divs = getAllFirstDivisions();
        for (FirstDivision div : divs) {
            if (countryID.equals(div.getCountryId() + "") && divisionName.equals(div.getDivision()))
                return div;
        }
        return null;
    }


    /**
     * Retrieves all division names from the database.
     *
     * @return A list of all division names.
     */
    public static List<String> getDivisionNames() {
        List<FirstDivision> divs = getAllFirstDivisions();
        List<String> names = new ArrayList<>();
        for (FirstDivision div : divs) {
            names.add(div.getDivision());
        }
        return names;
    }
    /**
     * Retrieves the name of a division by its ID from the database.
     *
     * @param divisionID The ID of the division.
     * @return The name of the division.
     */
    public static String getDivisionName(String divisionID) {
        List<FirstDivision> divs = getAllFirstDivisions();
        List<String> names = new ArrayList<>();
        for (FirstDivision div : divs) {
            if (divisionID.equals(div.getDivisionId() + ""))
                return div.getDivision();
        }
        return null;
    }
    /**
     * Retrieves the country ID of a division by its name from the database.
     *
     * @param divisionName The name of the division.
     * @return The ID of the country to which the division belongs.
     */
    public static String getDivisionCountryID(String divisionName) {
        List<FirstDivision> divs = getAllFirstDivisions();
        List<String> names = new ArrayList<>();
        for (FirstDivision div : divs) {
            if (divisionName.equals(div.getDivision() + ""))
                return div.getCountryId() + "";
        }
        return null;
    }

    /**
     * Retrieves the ID of a division by its name from the database.
     *
     * @param divisionName The name of the division.
     * @return The ID of the division.
     */
    public static String getDivisionID(String divisionName) {
        List<FirstDivision> divs = getAllFirstDivisions();
        for (FirstDivision div : divs) {
            if (divisionName.equals(div.getDivision()))
                return div.getDivisionId() + "";
        }
        return null;
    }
}
