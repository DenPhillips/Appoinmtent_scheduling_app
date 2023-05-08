package controller;

import DAO.AppointmentDAO;
import DAO.ContactDAO;
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


public class ContactScheduleController implements Initializable {
    public TableView<Appointments> appointment_Table;
    public TableColumn appointment_Id;
    public TableColumn title;
    public TableColumn description;
    public TableColumn type;
    public TableColumn start;
    public TableColumn end;
    public TableColumn customer_Id;
    public ComboBox Contact;

    /**
     * adds data to the contact combo box
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
        for (int i =0; i < ContactDAO.getAllContacts().size();i++){
            Contact.getItems().addAll(ContactDAO.getAllContacts().get(i).getContactId() + " "
                    + ContactDAO.getAllContacts().get(i).getContactName());
        }
        }catch (Exception e){
                throw new RuntimeException(e);
            }
    }

    /**
     * sends back to the menu scene
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
        stage.centerOnScreen();
    }

    /**
     * fills table with data from the query of the database
     * @param actionEvent
     * @throws SQLException
     */
    public void get_Schedule(ActionEvent actionEvent) throws SQLException {
       if(Contact.getValue() != null){
           int contact_id = Integer.parseInt(Contact.getValue().toString().substring(0,1));
           appointment_Id.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
           title.setCellValueFactory(new PropertyValueFactory<>("title"));
           description.setCellValueFactory(new PropertyValueFactory<>("description"));
           type.setCellValueFactory(new PropertyValueFactory<>("type"));
           start.setCellValueFactory(new PropertyValueFactory<>("start"));
           end.setCellValueFactory(new PropertyValueFactory<>("end"));
           customer_Id.setCellValueFactory(new PropertyValueFactory<>("customerId"));
           appointment_Table.setItems(AppointmentDAO.getAllContactAppointment(contact_id));
       }
    }

}
