package controller;

import DAO.AppointmentDAO;
import helper.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.ResourceBundle;


public class CustomerAppopintmentController implements Initializable {
    public ComboBox month;
    public ComboBox type;
    public Label result;


    /**
     * fills combo boxes with data
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i =0;i < Helper.Months().size(); i++){
            month.getItems().addAll(i+1 + " " + Helper.Months().get(i));

        }
        try{
        for (int i = 0; i < AppointmentDAO.getAllAppointments().size(); i++){
            type.getItems().addAll(AppointmentDAO.getAllAppointments().get(i).getType());
        }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * shows result from query in database
     * @param actionEvent
     * @throws SQLException
     */
    public void report(ActionEvent actionEvent) throws SQLException {

        if(month.getValue() != null && type.getValue() != null){
            int m = Integer.parseInt(month.getSelectionModel().getSelectedItem().toString().substring(0,1));
            String t = type.getSelectionModel().getSelectedItem().toString();
            result.setText(AppointmentDAO.CustomerReport(m,t));
        }
    }

    /**
     * send back to the menu scene
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
}
