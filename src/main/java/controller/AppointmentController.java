package controller;

import DAO.AppointmentDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;



public class AppointmentController implements Initializable {
    public TableView<Appointments> appointment_Table;
    public TableColumn appointment_Id;
    public TableColumn title;
    public TableColumn description;
    public TableColumn location;
    public TableColumn contact;
    public TableColumn type;
    public TableColumn start;
    public TableColumn end;
    public TableColumn customer_Id;
    public TableColumn user_Id;
    public RadioButton Week;
    public RadioButton Month;

    /**
     * populate table with data from the database
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointment_Id.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        customer_Id.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        user_Id.setCellValueFactory(new PropertyValueFactory<>("userId"));

        try {
            appointment_Table.setItems(AppointmentDAO.getAllAppointments());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * send to the add appointment scene
     * @param actionEvent
     * @throws IOException
     */
    public void Add(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Add-Appointment-view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene menu_scene = new Scene(root, 640, 535);
        stage.setTitle("Add Appointment");
        stage.setScene(menu_scene);
        stage.show();
        stage.centerOnScreen();;
    }

    /**
     * send to the update appointment scene
     * set data to be sent to the update appointment scene
     * @param actionEvent
     * @throws IOException
     * @throws SQLException
     */
    public void update(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Update-Appointment-view.fxml"));
        loader.load();
        UpdateAppointmentController uac = loader.getController();
        uac.setData(appointment_Table.getSelectionModel().getSelectedItem());

        Parent root = loader.getRoot();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene update_appointment_scene = new Scene(root, 640, 535);
        stage.setTitle("Update Appointment");
        stage.setScene(update_appointment_scene);
        stage.show();
        stage.centerOnScreen();
    }

    /**
     * delete appointment from the database after confirmation
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
    public void delete(ActionEvent actionEvent) throws SQLException, IOException {

        Alert deleteConfirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this Appointment with Appointment ID: "
        + appointment_Table.getSelectionModel().getSelectedItem().getAppointmentId()
                        + " and Appointment Type: " + appointment_Table.getSelectionModel().getSelectedItem().getType());
        deleteConfirm.setTitle("Confirm Delete");
        Optional<ButtonType> result = deleteConfirm.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            int appointmentId = appointment_Table.getSelectionModel().getSelectedItem().getAppointmentId();
            AppointmentDAO.deleteAppointment(appointmentId);
            appointment_Table.setItems(AppointmentDAO.getAllAppointments());
        }
    }

    /**
     * send to the menu scene
     * @param actionEvent
     * @throws IOException
     */
    public void menu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Menu-view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene menu_scene = new Scene(root, 450, 300);
        stage.setTitle("Menu");
        stage.setScene(menu_scene);
        stage.show();
        stage.centerOnScreen();;
    }

    /**
     * sorts appointments to appointments which are in the current week
     * @param actionEvent
     */
    public void Week_Click(ActionEvent actionEvent) {
        try {
            appointment_Table.setItems(AppointmentDAO.getAllWeekAppointments());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * shows all appointments
     * @param actionEvent
     */
    public void All_Click(ActionEvent actionEvent) {
        try {
            appointment_Table.setItems(AppointmentDAO.getAllAppointments());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * sorts appointments to appointments which are in the current month
     * @param actionEvent
     */
    public void Month_Click(ActionEvent actionEvent) {
        try {
            appointment_Table.setItems(AppointmentDAO.getAllMonthAppointments());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
