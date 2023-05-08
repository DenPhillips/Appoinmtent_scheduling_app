package controller;

import DAO.AppointmentDAO;
import DAO.UserDAO;
import helper.Helper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import model.User;


import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    public TextField Username_text;
    public TextField Password_text;
    public Label Username_label;
    public Label Password_label;
    public Label Title_text;
    public Label Location_label;
    public Label Location_text;
    public Button Login_button;
    /**
     * gets default language
     */
    private String lang = Locale.getDefault().getLanguage();
    /**
     * gets system default zone
     */
    private ZoneId zoneId = ZoneId.systemDefault();


    public MainController() {

    }

    /**
     * checks language and changes text to match, writes out zone id
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Location_text.setText(zoneId.toString());
        System.out.println(System.getProperty("javafx.runtime.version"));

        if(lang == "en"){
            Locale english = new Locale("en");
            ResourceBundle rb = ResourceBundle.getBundle("Lang", english);
            Username_label.setText(rb.getString("Username_label"));
            Password_label.setText(rb.getString("Password_label"));
            Location_label.setText(rb.getString("Location_label"));
            Title_text.setText(rb.getString("Title_text"));
            Login_button.setText(rb.getString("Login_button"));
        }

        if(lang == "fr"){
            Locale french = new Locale("fr");
            ResourceBundle rb = ResourceBundle.getBundle("Lang", french);
            Username_label.setText(rb.getString("Username_label"));
            Password_label.setText(rb.getString("Password_label"));
            Location_label.setText(rb.getString("Location_label"));
            Title_text.setText(rb.getString("Title_text"));
            Login_button.setText(rb.getString("Login_button"));
        }
    }

    /**
     * checks is login information is correct
     * writes log in attempts in file
     * logs in user and sends to menu scene
     * @param actionEvent
     * @throws Exception
     */
    public void Login_press(ActionEvent actionEvent) throws Exception {

        ObservableList<User> Users = FXCollections.observableArrayList();
        Users.addAll(UserDAO.getAllUsers());

        String Empty_username = "";
        String Empty_password = "";
        String Error_login = "";
        String Warning_title_username = "";
        String Warning_title_password = "";
        String Error_title = "";




        if (lang == "en") {
            Locale english = new Locale("en");
            ResourceBundle rb = ResourceBundle.getBundle("Lang", english);
            Empty_username = rb.getString("Empty_username");
            Empty_password = rb.getString("Empty_password");
            Error_login = rb.getString("Error_login");
            Warning_title_username = rb.getString("Warning_title_username");
            Warning_title_password = rb.getString("Warning_title_password");
            Error_title = rb.getString("Error_title");
        }

        if (lang == "fr") {
            Locale french = new Locale("fr");
            ResourceBundle rb = ResourceBundle.getBundle("Lang", french);
            Empty_username = rb.getString("Empty_username");
            Empty_password = rb.getString("Empty_password");
            Error_login = rb.getString("Error_login");
            Warning_title_username = rb.getString("Warning_title_username");
            Warning_title_password = rb.getString("Warning_title_password");
            Error_title = rb.getString("Error_title");
        }

        String username = Username_text.getText();

        String password = Password_text.getText();

        if (username.isBlank()) {

            Alert alert = new Alert(Alert.AlertType.WARNING, Empty_username);
            alert.setTitle(Warning_title_username);
            alert.showAndWait().filter(response -> response == ButtonType.OK);
            return;
        }
        if (password.isBlank()) {

            Alert alert = new Alert(Alert.AlertType.WARNING, Empty_password);
            alert.setTitle(Warning_title_password);
            alert.showAndWait().filter(response -> response == ButtonType.OK);
            return;
        }

        boolean auth = false;

        for(int i = 0; i < Users.size(); i++){
            if(username.equals(Users.get(i).getUserName())
                    && password.equals(Users.get(i).getPassword())){
                auth = true;
                Helper.loggedInUser = Users.get(i);
            }
        }


        File file = new File("login_activity.txt");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        String dateTime = ZonedDateTime.ofInstant(Instant.now(), zoneId).format(DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.getDefault()));

        if(auth) {
            bw.write("User " + Helper.loggedInUser.getUserName() + " Login attempt on ");
            bw.write(dateTime);
            bw.write(": Login successful");
            bw.newLine();
            bw.close();

            try {
                Appointments app = AppointmentDAO.getAllUserAppointment(Helper.loggedInUser.getUserId());
                if(app == null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            "No Upcoming Appointments for " + Helper.loggedInUser.getUserName() + ".");
                    alert.setTitle("No Upcoming Appointment");
                    alert.showAndWait().filter(response -> response == ButtonType.OK);
                }
                else{
                    int id = app.getAppointmentId();
                    String start = app.getStart();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            "Appointment ID: " + id + " at " + start + " for " + Helper.loggedInUser.getUserName() + ".");
                    alert.setTitle("Upcoming Appointment");
                    alert.showAndWait().filter(response -> response == ButtonType.OK);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            Parent root = FXMLLoader.load(getClass().getResource("/view/Menu-view.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene menu_scene = new Scene(root, 450, 300);
            stage.setTitle("Menu");
            stage.setScene(menu_scene);
            stage.show();
            stage.centerOnScreen();;

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, Error_login);
            alert.setTitle(Error_title);
            alert.showAndWait().filter(response -> response == ButtonType.OK);

            bw.write("User " + username + " Login attempt on ");
            bw.write(dateTime);
            bw.write(": Login unsuccessful");
            bw.newLine();
            bw.close();
        }
    }

}