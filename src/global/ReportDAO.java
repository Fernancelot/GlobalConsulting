package global;

import model.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides data access methods for generating reports based on appointment data.
 */
public class ReportDAO {

    /**
     * Retrieves a report summarizing the number of appointments for each planning type and month.
     *
     * @return A list of report objects containing the planning type, month, and count of appointments.
     */
    public static List<Report> getReport() {
        try {
            Connection conn = Database.getConnection();

            String sql = "SELECT Type, Start FROM appointments";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            List<Report> reports = new ArrayList<>();

            while (rs.next()) {
                String planningType = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                int month = start.getMonthValue();

                boolean found = false;
                for (Report report : reports) {
                    if (report.getPlanningType().equals(planningType) && report.getIntegerMonth() == month) {
                        report.setCount(report.getCount() + 1);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    reports.add(new Report(planningType, month, 1));
                }
            }
            rs.close();
            stmt.close();
            return reports;
        } catch (SQLException e) {
            Main.dialogue("Error",e.getMessage());
        }
        return null;
    }
}
