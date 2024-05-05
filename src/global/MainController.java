package global;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Appointment;
import model.User;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Date;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Controller class for the main view of the application.
 */
public class MainController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Label locationLabel;

    /**
     * Initializes the controller.
     * Updates the location label with the user's time zone.
     */
    @FXML
    private void initialize() {

        ZoneId zoneId = ZoneId.systemDefault();

        if (Main.isEnglish()) {
            locationLabel.setText("Your location: " + zoneId.getId());
        } else {
            locationLabel.setText("Utilisateur en ligne: " + zoneId.getId());
        }
    }

    /**
     * Handles the login action.
     * Checks if the username and password fields are filled.
     * Verifies the user's credentials and navigates to the customers view if successful.
     * Displays appropriate error messages if login fails.
     */
    @FXML
    private void login() {
        if(username.getText().isEmpty()){
            if (Main.isEnglish()){
                Main.dialogue("Error", "Username cannot be empty");
                this.writeReport("Username cannot be empty","FAILED");
            }else {
                Main.dialogue("Erreur", "Username cannot be empty");
                this.writeReport("Le nom d'utilisateur ne peut pas être vide","ÉCHOUÉ");
            }
        } else if(password.getText().isEmpty()){
            if (Main.isEnglish()){
                Main.dialogue("Error", "Password cannot be empty");
                this.writeReport("Password cannot be empty","FAILED");
            }else {
                Main.dialogue("Erreur", "Le mot de passe ne peut pas être vide");
                this.writeReport("Le mot de passe ne peut pas être vide","ÉCHOUÉ");
            }
        }else{

            User user = UserDAO.getUser(username.getText());
            if(user == null){
                if (Main.isEnglish()){
                    Main.dialogue("Error","Incorrect Username or password!!!");
                    this.writeReport("Incorrect Username or password!!!","FAILED");
                }else {
                    Main.dialogue("Erreur","Identifiant ou mot de passe incorrect!!!");
                    this.writeReport("Identifiant ou mot de passe incorrect!!!","ÉCHOUÉ");
                }
            }else{
                if(password.getText().equals(user.getPassword())){
                    Main.loadFXML("customers.fxml","Customers");
                    Main.userID = user.getUserId();
                    Main.userName = user.getUserName();
                    checkAppointments();
                    if (Main.isEnglish()){
                        this.writeReport("Login successful...","SUCCESS");
                    }else {
                        this.writeReport("Connexion réussie...","SUCCÈS");
                    }
                }else{
                    if (Main.isEnglish()){
                        Main.dialogue("Error","Incorrect Username or password!!!");
                        this.writeReport("Incorrect Username or password!!!","FAILED");
                    }else {
                        Main.dialogue("Erreur","Identifiant ou mot de passe incorrect!!!");
                        this.writeReport("Identifiant ou mot de passe incorrect!!!","ÉCHOUÉ");
                    }
                }
            }
        }

    }
    /**
     * Checks for upcoming appointments due within the next 15 minutes for the current user.
     * Displays a message dialog with the details of the upcoming appointments if any are found.
     */
    private void checkAppointments() {
        List<Appointment> appointments = AppointmentDAO.getAppointments();
        String appDue = "";
        for (Appointment appointment : appointments) {
            if (appointment.getUserId() == Main.userID) {
                if (isDue15min(appointment)) {
                    appDue += "ID: " + appointment.getAppointmentId() + " Title: " + appointment.getTitle() + " Description: " + appointment.getDescription() + "\n";
                }
            }
        }
//        MainController mainController = new MainController();
        if(appDue.isEmpty())
            Main.dialogue("Upcoming Appointments","Hello "+Main.userName+".\nYou have no Upcoming Appointment Due 15 minutes Time");
        else
            Main.dialogue("Upcoming Appointments",appDue);
    }


    /**
     * Checks if the given appointment is due within the next 15 minutes from the current time.
     *
     * @param appointment The appointment to check.
     * @return true if the appointment is due within the next 15 minutes, false otherwise.
     */
    private boolean isDue15min(Appointment appointment) {
        try {
            LocalTime currentTime = LocalTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalTime localTimeToCheck = LocalTime.parse(appointment.getStart(), formatter);

            long timeDif = ChronoUnit.MINUTES.between(currentTime, localTimeToCheck);
            // System.out.println("Up "+timeDif);

            return timeDif > 0 && timeDif <= 15;
        } catch (Exception e){
            return false;
        }

    }
    /**
     * Writes a report message to a file.
     *
     * @param message the message to be written to the report
     * @param status  the status associated with the message
     */
    private void writeReport(String message, String status) {
        Long currentTimestamp = new Date().getTime();
        String currentDate = new Date().toString();
        String filename = "login_activity.txt";
        try {
            File file = new File(filename);
            String header = "";
            if(!file.exists()){
                if(Main.isEnglish()){
                    header = "Date,Timestamp,System Message,Status";
                }else {
                    header = "Date,Horodatage,Message système,Statut";
                }

                file.createNewFile();
            }
            String content = header + String.format("\r\n%s,%s,%s,%s",currentDate,currentTimestamp,message,status);

            FileWriter writer = new FileWriter(file.getName(), true);
            BufferedWriter bWriter = new BufferedWriter(writer);
            bWriter.write(content);
            bWriter.close();

        } catch(IOException e){
            e.printStackTrace();
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

}
