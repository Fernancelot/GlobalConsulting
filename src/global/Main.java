package global;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Main class for the application.
 */
public class Main extends Application {
    /**
     * Represents the unique identifier of the user logged into the application.
     * Initialized to -1.
     */
    public static int userID = -1;

    /**
     * Represents the name of the user logged into the application.
     * Initialized to an empty string.
     */
    public static String userName = "";
    /**
     * Defines the parent scene
     */
    public  static  Parent root = null;
    /**
     * A copy of the primary stage
     * Needed while loading another view
     */
    public static Stage copy = null;


    /**
    * This function returns the locale code/locale language code (en/fr)
     * @return  the local language used by the user
     */
    public static boolean isEnglish() {
        String lang = Locale.getDefault().toString().toLowerCase();
        return !lang.startsWith("fr");
    }

    /**
     * This method returns the current time in UTC format as a string.
     * It first gets the current time in UTC using ZonedDateTime.now(ZoneId.of("UTC")).
     * Then it formats the time into a string using a DateTimeFormatter.
     * The format of the time string is "yyyy-MM-dd HH:mm:ss".
     * @return The current time in UTC as a string.
     */
    public static String getCurrentTimeString(){
        ZonedDateTime utcTime = ZonedDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(utcTime);
    }

    /**
     * This method converts a UTC time string to the system's default time zone.
     * It first parses the input string into a LocalDateTime object using a DateTimeFormatter.
     * Then it converts the LocalDateTime object to a ZonedDateTime object in UTC.
     * Finally, it converts the UTC time to the system's default time zone using withZoneSameInstant(ZoneId.systemDefault()).
     * The format of the input and output time string is "yyyy-MM-dd HH:mm:ss".
     * @param dateOBJ The input UTC time string.
     * @return The input time converted to the system's default time zone as a string.
     */
    public static String fromUTCToDefault(String dateOBJ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime utcDateTime = LocalDateTime.parse(dateOBJ, formatter).atZone(ZoneId.of("UTC"));
        ZonedDateTime localDateTime = utcDateTime.withZoneSameInstant(ZoneId.systemDefault());
        return formatter.format(localDateTime);
    }

    /**
     * Entry point of the application.
     * Initializes the primary stage and loads the login form.
     * @param primaryStage The primary stage of the application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        copy = primaryStage;
        if (isEnglish()){
            loadFXML("login_en.fxml","User Login");
        }else {
            loadFXML("login_fr.fxml","Utilisateur en ligne");
        }
    }

    /**
     * Loads an FXML file and sets it as the scene of the primary stage.
     * @param filename The name of the FXML file to load.
     * @param title The title of the stage.
     */
    public static void loadFXML(String filename, String title){
        try {
            root = FXMLLoader.load(Main.class.getResource(filename));
            copy.setTitle(title);
            copy.setScene(new Scene(root, 1024, 768));
            copy.show();
        } catch (Exception e) {
            Main.dialogue("Error",e.getMessage());
        }
    }

    /**
     * Displays a dialogue box with the given title and message.
     * @param title The title of the dialogue box.
     * @param message The message to be displayed.
     */
    public static void dialogue(String title, String message){
        Alert alert;
        if(title.equalsIgnoreCase("error")){
            alert = new Alert(Alert.AlertType.ERROR);
        }else
            alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     * Main method, launches the JavaFX application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
