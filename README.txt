DAO means "Direct Access Object" as used in the naming of some classes in the global package (e.g ContactDAO.java, CountryDAO.java, CustomerDAO.java e.t.c)

Title: WGU Performance Assessment
-----Purpose of the application:
Learn to create GUI applications that interact with the database, learn to handle exceptions, learn how to read and write files, learn to work with lambda expressions, and finally learn localization of APIs and Date/Time APIs in Java programming


Author: Christopher Powell
Student application version: v1.03
Date: 2024-05-05

IDE including version number: IntelliJ IDEA 2023.2.2 (Community Edition) Build #IC-232.9921.47, built on September 12, 2023
JDK of version used: Java SE 17.0.10
JavaFX version compatible with JDK version: JavaFX-SDK-17.0.10
MySQL Connector driver version number: mysql-connector-java-8.0.26


------Directions for how to run the program-------
    To run the program, go-to Main.java in the global package, run the main method.
    use test login credentials to log in the system.
    Username: test
    Password: test
    -------------OR----------
    Username: admin
    Password: admin

    NOTE: The default entered is the 'admin' credentials

------A description of the additional report of your choice you ran in part A3f

    ----------The total number of customer appointments by type and month-----------
    Once logged-in, navigate to the Reports menu and click the Customer link. It will display the report of the total number of customer appointments by type and month.


    ---------A schedule for each contact in your organization that includes appointment ID, title, type and description, start date and time, end date and time, and customer ID-------------------------------
    Once logged-in, navigate to the Reports menu and click the Appointments link. it will display a table view of a
    schedule for each contact in your organization that includes appointment ID, title, type and description,
    start date and time, end date and time, and customer ID.

    At the bottom, there is a combo box that is used to choose a specific contact name.


    --------- The third report (Customer summary report)------------
    Once logged-in, navigate to the Reports menu and click the Customer summary link. This report shows the count of customers per first level division. For example, Florida has 10 customers and Wales has 3 customers based on the data in the Database

WHERE LAMBDA EXPRESSIONS ARE USED IN THE PROGRAM
1. In the global package, there is an interface called "Savable" which saves the state of the specified object.
    the lambda expression comes here.
    (a.) Line 94 of the AppointmentDAO.java class is an example use of the lambda expression "Savable object = (obj) -> {"
    (b.) Line 93 of the CustomerDAO.java class
    (c.) Line 67 of the UserDAO.java class

2. in the same global package under the AppointmentController.java class in line 116 in the sortAppointments() method.
    the lambda expression takes 2 arguments, and it's used to sort appointments weekly and monthly from the combobox in the appointment view.
