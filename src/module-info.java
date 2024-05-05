/**
* Module responsible for the global consulting application.
* This module requires JavaFX, Java SQL, and opens packages for reflection.
*/
module globalconsulting {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires mysql.connector.java;

    opens global;
    opens model;
}