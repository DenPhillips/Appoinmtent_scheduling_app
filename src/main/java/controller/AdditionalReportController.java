package controller;

import DAO.AppointmentDAO;
import DAO.ContactDAO;
import DAO.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdditionalReportController implements Initializable {
    public TableView<Appointments> appointment_Table;
    public TableColumn appointment_Id;
    public TableColumn title;
    public TableColumn description;
    public TableColumn type;
    public TableColumn start;
    public TableColumn end;
    public TableColumn customer_Id;
    public ComboBox User;
    public TableColumn contact_Id;

    /**
     * adds data to the User combo box
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for (int i = 0; i < UserDAO.getAllUsers().size(); i++) {
                User.getItems().addAll(UserDAO.getAllUsers().get(i).getUserId() + " "
                        + UserDAO.getAllUsers().get(i).getUserName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * sends back to the menu scene
     *
     * @param actionEvent
     * @throws IOException
     */
    public void menu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Menu-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene menu_scene = new Scene(root, 450, 300);
        stage.setTitle("Menu");
        stage.setScene(menu_scene);
        stage.show();
        stage.centerOnScreen();
    }

    /**
     * fills table with data from the query of the database
     * Lambda expression used to filter Appointment list where user_id = User ID
     * @param actionEvent
     * @throws SQLException
     */
    public void get_Schedule(ActionEvent actionEvent) throws SQLException {
        if (User.getValue() != null) {
            int user_id = Integer.parseInt(User.getValue().toString().substring(0, 1));
            appointment_Id.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            start.setCellValueFactory(new PropertyValueFactory<>("start"));
            end.setCellValueFactory(new PropertyValueFactory<>("end"));
            customer_Id.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            contact_Id.setCellValueFactory(new PropertyValueFactory<>("contactId"));
            ObservableList<Appointments> UserApp = AppointmentDAO.getAllAppointments().stream().filter(t -> String.valueOf(t.getUserId())
                    .equals(String.valueOf(user_id))).collect(Collectors.toCollection(FXCollections::observableArrayList));
            appointment_Table.setItems(UserApp);
        }
    }
}



