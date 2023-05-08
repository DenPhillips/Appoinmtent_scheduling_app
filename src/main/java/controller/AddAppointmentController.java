package controller;

import DAO.AppointmentDAO;
import DAO.ContactDAO;
import DAO.CustomerDAO;
import DAO.UserDAO;
import helper.Helper;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;



public class AddAppointmentController implements Initializable {
    public ComboBox Start_hour;
    public ComboBox End_minute;
    public ComboBox End_hour;
    public ComboBox Start_minute;
    public TextField Title;
    public TextField Appointment_ID;
    public TextField Type;
    public TextField Description;
    public TextField Location;
    public ComboBox Contact;
    public DatePicker End_date;
    public DatePicker Start_date;
    public ComboBox Customer_ID;
    public ComboBox User_ID;
    public ComboBox EAP;
    public ComboBox SAP;


    /**
     * sets all combo boxes with data
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EAP.setItems(Helper.AMPM());
        SAP.setItems(Helper.AMPM());
        Start_hour.setItems(Helper.hours());
        End_hour.setItems(Helper.hours());
        Start_minute.setItems(Helper.minutes());
        End_minute.setItems(Helper.minutes());

        try {
            for (int i = 0; i < CustomerDAO.getAllCustomers().size(); i++) {
                Customer_ID.getItems().addAll(CustomerDAO.getAllCustomers().get(i).getCustomerId());
            }
            for (int i = 0; i < UserDAO.getAllUsers().size(); i++) {
                User_ID.getItems().addAll(UserDAO.getAllUsers().get(i).getUserId());
            }
            for (int i = 0; i < ContactDAO.getAllContacts().size(); i++) {
                Contact.getItems().addAll(ContactDAO.getAllContacts().get(i).getContactId() + "  " +
                        ContactDAO.getAllContacts().get(i).getContactName());

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * add appointments to appointments table in database
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
        public void Add_press(ActionEvent actionEvent) throws SQLException, IOException {
        String title = Title.getText();
        String type = Type.getText();
        String description = Description.getText();
        String location = Location.getText();
        String startDate = Start_date.getValue().toString();
        String endDate = End_date.getValue().toString();
        String startHour = Start_hour.getValue().toString();
        String startMin = Start_minute.getValue().toString();
        String endHour = End_hour.getValue().toString();
        String endMin = End_minute.getValue().toString();
        String userName = Helper.loggedInUser.getUserName();
        String contact = Contact.getValue().toString();
        int userId = Integer.valueOf(User_ID.getValue().toString());
        int customerId = Integer.valueOf(Customer_ID.getValue().toString());
        int contactId = Integer.parseInt(contact.substring(0,1));
        String SAPM = String.valueOf(SAP.getValue());
        String EAPM = String.valueOf(EAP.getValue());


        if (SAPM.equals("PM")&& Integer.valueOf(startHour) != 12) {
            int startHourI = Integer.valueOf(startHour) + 12;
            startHour = String.valueOf(startHourI);
        }
        if(SAPM.equals("AM") && Integer.valueOf(startHour) == 12){
            String startHourII = String.format("%02d", 0);
            endHour = startHourII;
        }
        if(EAPM.equals("PM") && Integer.valueOf(endHour) != 12){
            int endHourI = Integer.valueOf(endHour) + 12;
            endHour = String.valueOf(endHourI);
        }
        if(EAPM.equals("AM") && Integer.valueOf(endHour) == 12){
            String endHourII = String.format("%02d", 0);
            endHour = endHourII;
        }

        String SDT = Helper.UTCtime(startHour, startMin, startDate);
        String EDT = Helper.UTCtime(endHour, endMin, endDate);
        int openStart = Helper.ESTOpen(startHour, startMin, startDate);
        int openEnd = Helper.ESTOpen(endHour, endMin, endDate);
        int closeStart = Helper.ESTClose(startHour, startMin, startDate);
        int closeEnd = Helper.ESTClose(endHour, endMin, endDate);


        if(openStart < 0 || openEnd < 0 || closeStart > 0 || closeEnd > 0){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Company Hours are from 8:00 AM to 10:00 PM EST");
            alert.setTitle("Choose another time");
            alert.showAndWait().filter(response -> response == ButtonType.OK);
            return;
        }
        try {
            ObservableList<Appointments> cusApp = AppointmentDAO.getAllCustomerAppointment(customerId);
            for(int i =0; i < cusApp.size();i++){
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dS = LocalDateTime.parse(cusApp.get(i).getStart(), Helper.formatter).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
                LocalDateTime dE = LocalDateTime.parse(cusApp.get(i).getEnd(), Helper.formatter).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
                LocalDateTime iS= LocalDateTime.parse(SDT, format);
                LocalDateTime iE = LocalDateTime.parse(EDT, format);

                if((iS.isAfter(dS) || iS.isEqual(dS)) && iS.isBefore(dE)){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"This customer already has an appointment at this time.");
                    alert.setTitle("Appointment already scheduled");
                    alert.showAndWait().filter(response -> response == ButtonType.OK);
                    return;
                }
                else if(iE.isAfter(dS) && (iE.isBefore(dE) || iE.isEqual(dE))){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"This customer already has an appointment at this time.");
                    alert.setTitle("Appointment already scheduled");
                    alert.showAndWait().filter(response -> response == ButtonType.OK);
                    return;
                }
                else if((iS.isBefore(dS) || iS.isEqual(dS)) && (iE.isAfter(dE) || iE.isEqual(dE))){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"This customer already has an appointment at this time.");
                    alert.setTitle("Appointment already scheduled");
                    alert.showAndWait().filter(response -> response == ButtonType.OK);
                    return;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        AppointmentDAO.addAppointment(title, description, location, type, Timestamp.valueOf(SDT), Timestamp.valueOf(EDT), userName, userName,
                customerId, userId, contactId);

        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointment-view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene appointment_scene = new Scene(root, 900, 400);
        stage.setTitle("Appointments");
        stage.setScene(appointment_scene);
        stage.show();
        stage.centerOnScreen();

    }

    /**
     * sends back to the appointments scene
     * @param actionEvent
     * @throws IOException
     */
    public void Back_press(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointment-view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene appointment_scene = new Scene(root, 900, 400);
        stage.setTitle("Appointments");
        stage.setScene(appointment_scene);
        stage.show();
        stage.centerOnScreen();
    }

}
