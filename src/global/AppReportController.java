package global;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import model.Appointment;
import model.AppointmentReport;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class responsible for managing the Appointment Reports view.
 * This class handles interactions with the UI components in the Appointment Reports view.
 */
public class AppReportController {

    /**
     * Menu displaying the currently logged-in user's name.
     */
    @FXML
    private Menu userLoggedIn;

    /**
     * TableView for displaying appointment reports.
     */
    @FXML
    private TableView tableView;

    /**
     * ComboBox for selecting contacts.
     */
    @FXML
    private ComboBox<String> contactComboBox;

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets the welcome message with the current user's name, retrieves appointment reports for a specific contact,
     * populates the table view with the retrieved appointment reports, and sets up the contact combo box.
     */
    @FXML
    private void initialize(){
        userLoggedIn.setText("Welcome "+Main.userName);
        List<AppointmentReport> apr = getAppointmentReports(1);
        tableView.getItems().addAll(apr);
        contactComboBox.setValue("select Contact Name");
        List<String> selections = ContactDAO.getContactNames();
        contactComboBox.getItems().addAll(selections);
    }

    /**
     * Retrieves appointment reports associated with a specific contact ID.
     *
     * @param contactID The ID of the contact for which to retrieve appointment reports.
     * @return A list of appointment reports associated with the specified contact ID.
     */
    private List<AppointmentReport> getAppointmentReports(int contactID){
        List<Appointment> app = AppointmentDAO.getAppointments();
        List<AppointmentReport> contactReports = new ArrayList<>();
        for (int i=0; i<app.size(); i++){
            if(contactID == app.get(i).getContactId()) {
                AppointmentReport report = new AppointmentReport();
                report.setCustomerId(app.get(i).getCustomerId());
                report.setAppointmentId(app.get(i).getAppointmentId());
                report.setTitle(app.get(i).getTitle());
                report.setType(app.get(i).getType());
                report.setStartTime(app.get(i).getStart());
                report.setDescription(app.get(i).getDescription());
                report.setEndTime(app.get(i).getEnd());
                contactReports.add(report);
            }
        }
        return contactReports;
    }
    /**
     * Closes the application when triggered by an action event.
     *
     * @param actionEvent The ActionEvent associated with the button click.
     */
    @FXML
    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Navigates to the Appointments view.
     *
     * @param actionEvent The event triggered by the action.
     */
    @FXML
    public void goToAppointments(ActionEvent actionEvent) {
        Main.loadFXML("appointments.fxml", "Appointments");
    }

    /**
     * Navigates to the Appointment Report view.
     *
     * @param actionEvent The event triggered by the action.
     */
    @FXML
    public void goToAppReport(ActionEvent actionEvent) {
        Main.loadFXML("appReport.fxml", "Appointment Report");
    }

    /**
     * Navigates to the Report view.
     *
     * @param actionEvent The event triggered by the action.
     */
    @FXML
    public void goToReport(ActionEvent actionEvent) {
        Main.loadFXML("report.fxml", "Customer Report");
    }

    /**
     * Navigates to the Login view.
     *
     * @param actionEvent The event triggered by the action.
     */
    @FXML
    public void goToLogin(ActionEvent actionEvent) {
        Main.userName = "";
        Main.userID = -1;
        Main.loadFXML("login_en.fxml", "User Login");
    }

    /**
     * Navigates to the Customers view.
     *
     * @param actionEvent The event triggered by the action.
     */
    @FXML
    public void goToCustomer(ActionEvent actionEvent) {
        Main.loadFXML("customers.fxml", "Customers");
    }

    /**
     * Navigates to the customer summary report view.
     *
     * @param actionEvent The action event triggered.
     */
    public void goToSummaryReport(ActionEvent actionEvent) {
        Main.loadFXML("customerSummary.fxml", "Customer Summary Report");
    }

    /**
     * Handles the action triggered by combobox change in the Appointments view.
     *
     * @param actionEvent The event triggered by the action.
     */
    @FXML
    public void appComboxChanged(ActionEvent actionEvent) {
            String contactName = contactComboBox.getValue();
            int contactID = ContactDAO.getContactID(contactName);
            List<AppointmentReport> apr = getAppointmentReports(contactID);
            tableView.getItems().clear();
            tableView.getItems().addAll(apr);
    }
}