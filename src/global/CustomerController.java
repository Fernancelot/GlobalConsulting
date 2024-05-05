package global;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

import model.Customer;
import model.FirstDivision;

/**
 * Controller class for managing customers in the application.
 */
public class CustomerController {

    @FXML
    private Menu userLoggedIn;
    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TextField get_CustomerId;


    @FXML
    private TextField update_CustomerId;

    @FXML
    private TextField update_CustomerName;

    @FXML
    private TextField update_Address;

    @FXML
    private TextField update_Postal_Code;

    @FXML
    private TextField update_Phone;

    @FXML
    private TextField update_Create_Date;

    @FXML
    private TextField update_Created_By;

    @FXML
    private TextField update_Last_Update;

    @FXML
    private TextField update_Last_Update_By;

    @FXML
    private ComboBox<String> update_divisionId;

    @FXML
    private Button add_Customer;

    @FXML
    private Button update_Customer;

    @FXML
    ComboBox<String> countryComboBox;

    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        userLoggedIn.setText("Welcome "+Main.userName);

        List<Customer> customers = CustomerDAO.getCustomers();
        tableView.getItems().addAll(customers);


        countryComboBox.getItems().addAll(CountryDAO.getCountryNames());
        countryComboBox.setValue("Select Country");

        List<String> divs = FirstDivisionDAO.getDivisionNames();
        ObservableList<String> divisions = FXCollections.observableList(divs);
        update_divisionId.getItems().addAll(divisions);
        update_divisionId.setValue("Select Division");

        update_divisionId.setDisable(true);

    }
    /**
     * Clears the customer form fields.
     */
    @FXML
    private void clearForm(){
        clearTextFields();
    }

    /**
     * Handles the event when the division combo box value is changed.
     */
    @FXML
    private void comboBoxDivision() {
        String selectedValue = update_divisionId.getValue();
        if(selectedValue == null)
            return;
        try {
            String id = CountryDAO.getCountryID(countryComboBox.getValue());
                FirstDivision fd = FirstDivisionDAO.getDivision(id, selectedValue);
                if(fd != null) {
                    update_Create_Date.setText(fd.getCreateDate());
                    update_Created_By.setText(fd.getCreatedBy());
                    update_Last_Update.setText(fd.getLastUpdate());
                    update_Last_Update_By.setText(fd.getLastUpdatedBy());
                }

        }catch (NumberFormatException e){

        }
    }
    /**
     * Handles the event when the country combo box value is changed.
     */
    @FXML
    public void countryComboxChanged(){
        String selection = countryComboBox.getValue();
        if(selection == null)
            return;
        if(selection.equalsIgnoreCase("Select country"))
            return;

        update_divisionId.getItems().clear();

        String countryID = CountryDAO.getCountryID(selection);
            List<String> divs = FirstDivisionDAO.getDivisionNames(countryID);
            update_divisionId.getItems().addAll(divs);
            update_divisionId.setDisable(false);
    }

    /**
     * Refreshes the table view with customer data.
     */
    public void refreshTableView() {
        tableView.getItems().clear();
        List<Customer> customers = CustomerDAO.getCustomers();
        tableView.getItems().addAll(customers);
    }

    /**
     * Adds a new customer.
     */
    @FXML
    public void addCustomer(){
        if (isFormValid()) {

            Customer newCustomer = new Customer();
            newCustomer.setCustomerId(-1);
            newCustomer.setCustomerName(update_CustomerName.getText());
            newCustomer.setAddress(update_Address.getText());
            newCustomer.setPostalCode(update_Postal_Code.getText());
            newCustomer.setPhone(update_Phone.getText());
            newCustomer.setCreateDate(update_Create_Date.getText());
            newCustomer.setCreatedBy(update_Created_By.getText());
            newCustomer.setLastUpdate(update_Last_Update.getText());
            newCustomer.setLastUpdatedBy(update_Last_Update_By.getText());

            String divisionID = FirstDivisionDAO.getDivisionID(update_divisionId.getValue());
            newCustomer.setDivisionId(Integer.parseInt(divisionID));

            if(CustomerDAO.saveCustomer(newCustomer)){
                tableView.getItems().add(newCustomer);

                clearTextFields();
                refreshTableView();
                Main.dialogue("Success","Customer added successfully!");

            }else{
                Main.dialogue("Error","Customer not added.");
            }
        } else {
            Main.dialogue("Error","All fields are required!");
        }
    }

    /**
     * Checks if the customer form is valid.
     *
     * @return True if the form is valid, otherwise false.
     */
    private boolean isFormValid(){
        if (!update_CustomerName.getText().isEmpty()
                && !update_Address.getText().isEmpty()
                && !update_Postal_Code.getText().isEmpty()
                && !update_Phone.getText().isEmpty()
                ) {
            return !update_divisionId.getValue().equals("select");
        }else
            return false;
    }
    /**
     * Clears the text fields.
     */
    private void clearTextFields() {
        update_CustomerId.clear();
        update_CustomerName.clear();
        update_Address.clear();
        update_Postal_Code.clear();
        update_Phone.clear();
        update_Create_Date.clear();
        update_Created_By.clear();
        update_Last_Update.clear();
        update_Last_Update_By.clear();
        get_CustomerId.clear();
    }
    /**
     * Edits an existing customer.
     */
    @FXML
    private void editCustomer(){
        if(get_CustomerId.getText().length() == 0){
            Main.dialogue("Error","Enter Customer ID First");
        }else{
            try {


            int id = Integer.parseInt(get_CustomerId.getText());

            Customer customer = CustomerDAO.getCustomer(id);
            if(customer == null){
                Main.dialogue("Error","Customer Not found");
                return;
            }
            update_CustomerId.setText(customer.getCustomerId()+"");
            update_CustomerName.setText(customer.getCustomerName());
            update_Address.setText(customer.getAddress());
            update_Postal_Code.setText(customer.getPostalCode());
            update_Phone.setText(customer.getPhone());

            String divName = FirstDivisionDAO.getDivisionName(customer.getDivisionId()+"");
            String countryId = FirstDivisionDAO.getDivisionCountryID(divName);
            String countryName = CountryDAO.getCountryName(countryId);
            countryComboBox.setValue(countryName);

            update_divisionId.setValue(divName);
            update_Create_Date.setText(customer.getCreateDate());
            update_Created_By.setText(customer.getCreatedBy());
            update_Last_Update.setText(customer.getLastUpdate());
            update_Last_Update_By.setText(customer.getLastUpdatedBy());


            update_Customer.setDisable(false);
            //add_Customer.setDisable(true);
            }catch (NumberFormatException e){
                Main.dialogue("Error","Enter Integers Only.");
            }
        }
    }

    /**
     * Updates an existing customer.
     */
    @FXML
    private void updateCustomer(){
        if(isFormValid()){
            Customer customer = CustomerDAO.getCustomer(Integer.parseInt(get_CustomerId.getText()));
            customer.setCustomerName(update_CustomerName.getText());
            customer.setAddress(update_Address.getText());
            customer.setPostalCode(update_Postal_Code.getText());
            customer.setPhone(update_Phone.getText());
            customer.setCreateDate(update_Create_Date.getText());
            customer.setCreatedBy(update_Created_By.getText());
            customer.setLastUpdate(update_Last_Update.getText());
            customer.setLastUpdatedBy(update_Last_Update_By.getText());
            String id = FirstDivisionDAO.getDivisionID(update_divisionId.getValue());
            customer.setDivisionId(Integer.parseInt(id));

            if(CustomerDAO.updateCustomer(customer)) {

                update_Customer.setDisable(true);
                clearTextFields();
                refreshTableView();
                add_Customer.setDisable(false);
                get_CustomerId.clear();
                Main.dialogue("Success", "Update was Successful.");
            }else
            {
                Main.dialogue("Error", "Update failure.");
            }
        }else{
            Main.dialogue("Error", "Make sure all fields are not Empty.");
        }

    }

    /**
     * Deletes a customer.
     */
    @FXML
    private void deleteCustomer(){

        if(get_CustomerId.getText().length() == 0){
            Main.dialogue("Error","Enter Customer ID First");
        }else{
            try {
                int id = Integer.parseInt(get_CustomerId.getText());
                AppointmentDAO.deleteAppointmentByCustomerID(id);
                if(CustomerDAO.deleteCustomer(id)) {
                    refreshTableView();
                    clearTextFields();
                    Main.dialogue("Success", "Appointment and Customer Data Deleted Successful.");
                }else{
                    Main.dialogue("Error", "Customer Not Found, ID: "+id);
                }
            }catch (NumberFormatException e){
                Main.dialogue("Error", "Enter an Integer Value.");
            }
        }
    }

    /**
     * Closes the application.
     *
     * @param actionEvent The action event triggered.
     */
    public void close(ActionEvent actionEvent) {
        System.exit(0);
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
     * Navigates to the login view.
     *
     * @param actionEvent The action event triggered.
     */
    public void goToLogin(ActionEvent actionEvent) {

        Main.loadFXML("login_en.fxml","User Login");
        Main.userID = -1;
        Main.userName = "";
    }

    /**
     * Navigates to the reports view.
     *
     * @param actionEvent The action event triggered.
     */
    public void goToReport(ActionEvent actionEvent) {
        Main.loadFXML("report.fxml","Reports");
    }

    /**
     * Navigates to the  reports appointment view.
     *
     * @param actionEvent The action event triggered.
     */
    public void goToAppReport(ActionEvent actionEvent) {
        Main.loadFXML("appReport.fxml","Appointment Reports");
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


