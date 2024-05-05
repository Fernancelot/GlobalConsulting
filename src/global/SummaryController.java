package global;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.Customer;
import model.CustomerSummary;

import java.util.ArrayList;
import java.util.List;

public class SummaryController {

    @FXML
    private TableView tableView;
    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {

        List <CustomerSummary> cs = getSummary();
        tableView.getItems().addAll(cs);
    }
    private boolean isInList(int divisionId, List<CustomerSummary>  divIds){
        for(int i=0; i<divIds.size(); ++i){
            if(divIds.get(i).getSummaryID() == divisionId) {
                divIds.get(i).incrementFrequency();
                return true;
            }
        }
        return false;
    }

    private List<CustomerSummary> getSummary() {
        List <Customer> customers = CustomerDAO.getCustomers();
        List <CustomerSummary> customerSummary = new ArrayList<>();
        for (int i=0; i<customers.size(); i++){
            if ( !this.isInList(customers.get(i).getDivisionId(),customerSummary) ){
                CustomerSummary cs = new CustomerSummary();
                cs.setSummaryID(customers.get(i).getDivisionId());
                String name = FirstDivisionDAO.getDivisionName(customers.get(i).getDivisionId()+"");
                cs.setDivisionName(name);
                customerSummary.add(cs);
            }
        }
        return customerSummary;
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
}
