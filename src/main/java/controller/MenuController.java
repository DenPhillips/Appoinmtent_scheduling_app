package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MenuController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * sends to customer records scene
     * @param actionEvent
     * @throws IOException
     */
    public void Customer_click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customer-Record-view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene customer_record_scene = new Scene(root, 650, 315);
        stage.setTitle("Customer Records");
        stage.setScene(customer_record_scene);
        stage.show();
        stage.centerOnScreen();;
    }

    /**
     * sends to Contact Appointment scene
     * @param actionEvent
     * @throws IOException
     */
    public void Contact_click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Contact-Appointment-view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene menu_scene = new Scene(root, 740, 400);
        stage.setTitle("Contact Appointment");
        stage.setScene(menu_scene);
        stage.show();
        stage.centerOnScreen();;
    }

    /**
     * sends to Appointments scene
     * @param actionEvent
     * @throws IOException
     */
    public void Appointment_click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointment-view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene appointment_scene = new Scene(root, 900, 400);
        stage.setTitle("Appointments");
        stage.setScene(appointment_scene);
        stage.show();
        stage.centerOnScreen();
    }


    /**
     * sends to customer appointment records scene
     * @param actionEvent
     * @throws IOException
     */
    public void CAR_click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customer-Appointment-view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene menu_scene = new Scene(root, 600, 200);
        stage.setTitle("Customer Appointment Report");
        stage.setScene(menu_scene);
        stage.show();
        stage.centerOnScreen();
    }

    public void Add_click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Additional-Report-view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene menu_scene = new Scene(root, 800, 400);
        stage.setTitle("Additional Report");
        stage.setScene(menu_scene);
        stage.show();
        stage.centerOnScreen();
    }

    /**
     * sends to the main scene
     * @param actionEvent
     * @throws IOException
     */
    public void Log_out(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main-view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene menu_scene = new Scene(root, 600, 400);
        stage.setTitle("Scheduling Application");
        stage.setScene(menu_scene);
        stage.show();
        stage.centerOnScreen();
    }
}
