package global;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import model.Report;

import java.util.List;

/**
 * Controller class for managing reports in the application.
 */
public class ReportController {

    @FXML
    private Menu userLoggedIn;
    @FXML
    private TableView tableView;

    /**
     * Initializes the controller.
     * Retrieves the report data and populates the table view.
     */
    @FXML
    private void initialize(){
        userLoggedIn.setText("Welcome "+Main.userName);
        List<Report> reports = ReportDAO.getReport();
        tableView.getItems().addAll(reports);

    }
    /**
     * Closes the application.
     *
     * @param actionEvent The action event triggered.
     */
    public void close(ActionEvent actionEvent)
    {
        System.exit(0);
    }

    /**
     * Navigates to the customers view.
     *
     * @param actionEvent The action event triggered.
     */
    public void goToCustomers(ActionEvent actionEvent)
    {
        Main.loadFXML("customers.fxml","Customers");
    }

    /**
     * Navigates to the login view.
     *
     * @param actionEvent The action event triggered.
     */
    public void goToLogin(ActionEvent actionEvent) {
        Main.userName = "";
        Main.userID = -1;
        Main.loadFXML("login_en.fxml","User Login");
    }

    /**
     * Navigates to the appointments view.
     *
     * @param actionEvent The action event triggered.
     */
    public void goToAppointments(ActionEvent actionEvent) {
        Main.loadFXML("appointments.fxml","Appointments");
    }

    /**
     * Loads the "customers.fxml" file, displaying the Customers view.
     *
     * @param actionEvent The action event that triggers the method.
     */
    public void goToCustomer(ActionEvent actionEvent) {
        Main.loadFXML("customers.fxml", "Customers");
    }

    /**
     * Loads the "appReport.fxml" file, displaying the Appointment Reports view.
     *
     * @param actionEvent The action event that triggers the method.
     */
    public void goToAppReport(ActionEvent actionEvent) {
        Main.loadFXML("appReport.fxml", "Appointment Reports");
    }

    /**
     * Loads the "report.fxml" file, displaying the Customer Reports view.
     *
     * @param actionEvent The action event that triggers the method.
     */
    public void goToReport(ActionEvent actionEvent) {
        Main.loadFXML("report.fxml", "Customer Reports");
    }

    /**
     * Navigates to the customer summary report view.
     *
     * @param actionEvent The action event triggered.
     */
    public void goToSummaryReport(ActionEvent actionEvent) {
        Main.loadFXML("customerSummary.fxml", "Customer Summary Report");
    }

}
