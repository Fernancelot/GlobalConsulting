package global;
import model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides data access methods for managing countries in the system.
 */
public class CountryDAO {

    /**
     * Retrieves all countries from the database.
     *
     * @return A list of all countries.
     */
    public static List<Country> getAllCountries() {
        List<Country> countries = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            String sql = "SELECT * FROM countries";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Country country = new Country();
                country.setCountryId(rs.getInt("Country_ID"));
                country.setCountry(rs.getString("Country"));
                country.setCreateDate(rs.getString("Create_Date"));
                country.setCreatedBy(rs.getString("Created_By"));
                country.setLastUpdate(rs.getString("Last_Update"));
                country.setLastUpdatedBy(rs.getString("Last_Updated_By"));
                countries.add(country);
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
        return countries;
    }
    /**
     * Retrieves the names of all countries from the database.
     *
     * @return A list of country names.
     */
    public static List<String> getCountryNames() {
        List<Country> countries = getAllCountries();
        List<String> names = new ArrayList<>();
        for (Country country : countries) {
            names.add(country.getCountry());
        }
        return names;
    }
    /**
     * Retrieves the ID of a country by its name from the database.
     *
     * @param countryName The name of the country to retrieve the ID for.
     * @return The ID of the country.
     */
    public static String getCountryID(String countryName) {
        List<Country> countries = getAllCountries();
        for (Country country : countries) {
            if (country.getCountry().equals(countryName)) {
                return country.getCountryId() + "";
            }
        }
         return null;
    }
    /**
     * Retrieves the name of a country by its ID from the database.
     *
     * @param countryID The ID of the country to retrieve the name for.
     * @return The name of the country.
     */
    public static String getCountryName(String countryID) {
        List<Country> countries = getAllCountries();
        for (Country country : countries) {
            if (countryID.equals(country.getCountryId() + "")) {
                return country.getCountry();
            }
        }
        return null;
    }
}

