package global;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Appointment;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Controller class for managing appointments in the application.
 */
public class AppointmentController {

    @FXML
    private Menu userLoggedIn;
    @FXML
    private TextField get_AppointmentId;

    @FXML
    private TextField update_AppointmentId;

    @FXML
    private TextField update_Create_Date;

    @FXML
    private TextField update_Created_By;

    @FXML
    private TextField update_Last_Update;

    @FXML
    private TextField update_Last_Update_By;

    @FXML
    private TableView tableView;

    @FXML
    private TextField update_Title;

    @FXML
    private TextField update_Description;

    @FXML
    private TextField update_Location;

    @FXML
    private TextField update_Type;

    @FXML
    private TextField update_Start;

    @FXML
    private TextField update_End;

    @FXML
    private ComboBox<String> update_CustomerId;

    @FXML
    private ComboBox<String> update_UserId;

    @FXML
    private ComboBox<String> update_ContactId;

    @FXML
    private Button update_Appointment;

    @FXML
    private ComboBox<String> sortCombo;
    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        userLoggedIn.setText("Welcome "+Main.userName);
        loadTableView();
        List<String> contactNames = ContactDAO.getContactNames();
        List<String> userNames = UserDAO.getUserNames();
        List<String> customerNames = CustomerDAO.getCustomersNames();
        update_ContactId.getItems().addAll(contactNames);
        update_ContactId.setValue("Select Contact");
        update_CustomerId.getItems().addAll(customerNames);
        update_CustomerId.setValue("Select Customer");
        update_UserId.getItems().addAll(userNames);
        update_UserId.setValue("Select User");

        //set form tooltips
        get_AppointmentId.setTooltip(new Tooltip("Enter Appointment's ID"));
        update_Title.setTooltip(new Tooltip("Enter Appointment's Title"));
        update_Description.setTooltip(new Tooltip("Enter Appointment's Description"));
        update_Location.setTooltip(new Tooltip("Enter Appointment's Location"));
        update_Type.setTooltip(new Tooltip("Enter Appointment's Type"));
        update_Start.setTooltip(new Tooltip("Enter Appointment's Start time"));
        update_End.setTooltip(new Tooltip("Enter Appointment's End time"));
        update_Create_Date.setTooltip(new Tooltip("Enter Appointment's Create Date"));
        update_Created_By.setTooltip(new Tooltip("Enter Appointment's Created By"));
        update_Last_Update.setTooltip(new Tooltip("Enter Last Updated date and time"));
        update_Last_Update_By.setTooltip(new Tooltip("Enter Appointment's Last updated By"));
        update_CustomerId.setTooltip(new Tooltip("Select Customer name"));
        update_UserId.setTooltip(new Tooltip("Select User"));
        update_ContactId.setTooltip(new Tooltip("Select Contact name"));
    }

    /**
     * Sorts appointments based on the selected sorting criteria (month or week of year).
     * Clears the table view, retrieves appointments from the data access object (DAO),
     * sorts them based on the specified criteria, and populates the table view with the sorted appointments.
     *
     */
    public void sortAppointments() {
        tableView.getItems().clear();
        String sortBy = sortCombo.getValue();
        List<Appointment> appointments = AppointmentDAO.getAppointments();
        Collections.sort(appointments, (ap1, ap2) -> {
            int value1 = 0;
            int value2 = 0;
            if(sortBy.equalsIgnoreCase("month")){
                value1 = ap1.getStartMonth();
                value2 = ap2.getStartMonth();
            }else{
                value1 = ap1.getStartWeekOfYear();
                value2 = ap2.getStartWeekOfYear();
            }

            return Integer.compare(value1, value2);
        });

        tableView.getItems().addAll(appointments);
    }

    /**
     * Loads appointments into the table view.
     */
    private void loadTableView(){
        List<Appointment> appointments = AppointmentDAO.getAppointments();
        tableView.getItems().addAll(appointments);

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
     * Navigates to the customers view.
     *
     * @param actionEvent The action event triggered.
     */
    public void goToCustomers(ActionEvent actionEvent) {
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
     * Checks if any of the fields in the appointment form are empty.
     *
     * @return {@code true} if any of the fields are empty, indicating that the form is invalid;
     *         {@code false} otherwise, indicating that the form is valid.
     */
    private boolean isFormInValid(){
        return update_Title.getText().isEmpty() || update_Description.getText().isEmpty() || update_Location.getText().isEmpty() ||
                update_Type.getText().isEmpty() || update_Start.getText().isEmpty() || update_End.getText().isEmpty() ||
                update_Create_Date.getText().isEmpty() || update_Created_By.getText().isEmpty() || update_Last_Update.getText().isEmpty() ||
                update_Last_Update_By.getText().isEmpty();
    }
    /**
     * Adds a new appointment.
     *
     * @param actionEvent The action event triggered.
     */
    public void addAppointment(ActionEvent actionEvent) {
        String date = Main.getCurrentTimeString();
        update_Created_By.setText("script");
        update_Last_Update_By.setText("script");
        update_Last_Update.setText(date);
        update_Create_Date.setText(date);

        if (isFormInValid()) {
            Main.dialogue("Error", "All fields must be filled");
        } else {
            Appointment appointment = new Appointment();
            appointment.setTitle(update_Title.getText());
            appointment.setDescription(update_Description.getText());
            appointment.setLocation(update_Location.getText());
            appointment.setType(update_Type.getText());
            appointment.setStart(update_Start.getText());
            appointment.setEnd(update_End.getText());
            appointment.setCreateDate(update_Create_Date.getText());
            appointment.setCreatedBy(update_Created_By.getText());
            appointment.setLastUpdate(update_Last_Update.getText());
            appointment.setLastUpdatedBy(update_Last_Update_By.getText());

            String customerName = update_CustomerId.getValue();
            appointment.setCustomerId(  CustomerDAO.getCustomerID(customerName) );

            String userName = update_UserId.getValue();
            appointment.setUserId( UserDAO.getUserId(userName));

            String contactName = update_ContactId.getValue();
            appointment.setContactId(ContactDAO.getContactID(contactName));

            AppointmentDAO.saveAppointment(appointment);

            clearUpdateFields();
            refreshTableView();
            // Main.dialogue("Error", "Failed to add appointment");

        }
    }

    /**
     * Refreshes the tableview
     */
    private void refreshTableView() {
        tableView.getItems().clear();
        loadTableView();
    }

    /**
     * Updates an existing appointment.
     *
     * @param actionEvent The action event triggered.
     */
    public void updateAppointment(ActionEvent actionEvent) {
        if (isFormInValid()) {
            Main.dialogue("Error", "All fields must be filled");
        } else {

            Appointment appointment = new Appointment();
            appointment.setAppointmentId(Integer.parseInt(update_AppointmentId.getText()));
            appointment.setTitle(update_Title.getText());
            appointment.setDescription(update_Description.getText());
            appointment.setLocation(update_Location.getText());
            appointment.setType(update_Type.getText());
            appointment.setStart(update_Start.getText());
            appointment.setEnd(update_End.getText());
            appointment.setCreateDate(update_Create_Date.getText());
            appointment.setCreatedBy(update_Created_By.getText());
            appointment.setLastUpdate(update_Last_Update.getText());
            appointment.setLastUpdatedBy(update_Last_Update_By.getText());

            String customerName = update_CustomerId.getValue();
            appointment.setCustomerId(  CustomerDAO.getCustomerID(customerName) );

            String userName = update_UserId.getValue();
            appointment.setUserId( UserDAO.getUserId(userName));

            String contactName = update_ContactId.getValue();
            appointment.setContactId(ContactDAO.getContactID(contactName));

            if (AppointmentDAO.updateAppointment(appointment)) {
                Main.dialogue("Success", "Appointment updated successfully");
                clearUpdateFields();
                //update_Appointment.setDisable(true);
                //add_Appointment.setDisable(false);
                refreshTableView();
            } else {
                Main.dialogue("Error", "Failed to update appointment");
            }
        }
    }

    /**
     * Deletes an appointment.
     *
     * @param actionEvent The action event triggered.
     */
    public void deleteAppointment(ActionEvent actionEvent) {
        if(get_AppointmentId.getText().length() == 0){
            Main.dialogue("Error","Enter Appointment ID first");
        }else{
            try
            {
            int id = Integer.parseInt(get_AppointmentId.getText());
            Appointment ap = AppointmentDAO.getAppointment(id);
           if( AppointmentDAO.deleteAppointment(id) ) {
               refreshTableView();
               Main.dialogue("Success", "Appointment Deleted Successfully.\nID: "+id+"\nType: "+ap.getType());
           }else
               Main.dialogue("Error", "Failed to delete appointment");
            }catch (NumberFormatException e){
                Main.dialogue("Error","Please Input An Integer.");
            }
        }
    }

    /**
     * Edits an appointment.
     *
     * @param actionEvent The action event triggered.
     */
    public void editAppointment(ActionEvent actionEvent) {
        if(get_AppointmentId.getText().length() == 0){
            Main.dialogue("Error","Enter Appointment ID first");
        }else{
            try {


            Appointment ap = AppointmentDAO.getAppointment(Integer.parseInt(get_AppointmentId.getText()));

            if (ap != null) {
                update_AppointmentId.setText(String.valueOf(ap.getAppointmentId()));
                update_Title.setText(ap.getTitle());
                update_Description.setText(ap.getDescription());
                update_Location.setText(ap.getLocation());
                update_Type.setText(ap.getType());
                update_Start.setText(ap.getStart());
                update_End.setText(ap.getEnd());
                update_Create_Date.setText(ap.getCreateDate());
                update_Created_By.setText(ap.getCreatedBy());
                update_Last_Update.setText(ap.getLastUpdate());
                update_Last_Update_By.setText(ap.getLastUpdatedBy());

                update_CustomerId.setValue( CustomerDAO.getCustomer(ap.getCustomerId()).getCustomerName() );
                update_UserId.setValue( UserDAO.getUserName(ap.getUserId()) );
                update_ContactId.setValue(ContactDAO.getContactName(ap.getContactId())); ;

                //add_Appointment.setDisable(true);
                //update_Appointment.setDisable(false);
            } else {
                Main.dialogue("Error", "Appointment not found");
            }
            }catch (NumberFormatException e){
                Main.dialogue("Error","Enter An Integer Value.");
            }
        }
    }
    /**
     * Clears the update fields.
     */
    public void clearUpdateFields() {
        update_Title.clear();
        update_Description.clear();
        update_Location.clear();
        update_Type.clear();
        update_Start.clear();
        update_End.clear();
        update_Create_Date.clear();
        update_Created_By.clear();
        update_Last_Update.clear();
        update_Last_Update_By.clear();
        update_CustomerId.setValue("Select Customer");
        update_UserId.setValue("Select User");
        update_ContactId.setValue("Select Contact");
        update_AppointmentId.clear();
        get_AppointmentId.clear();
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
     * Redirects the user to the appointment report page.
     *
     * @param actionEvent The ActionEvent associated with the button click.
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
