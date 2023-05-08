package controller;

import DAO.AppointmentDAO;
import DAO.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 *
 */
public class CustomerRecordController implements Initializable {
    public TableView<Customers> Customer_Records;
    public TableColumn Customer_ID;
    public TableColumn Customer_Name;
    public TableColumn Address;
    public TableColumn Postal_Code;
    public TableColumn Phone_Number;
    public TableColumn Division_ID;

    /**
     * fills customer table with info from database
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        Customer_Name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        Postal_Code.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        Phone_Number.setCellValueFactory(new PropertyValueFactory<>("phone"));
        Division_ID.setCellValueFactory(new PropertyValueFactory<>("divisionId"));


        try {
            Customer_Records.setItems(CustomerDAO.getAllCustomers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * sends to add customer scene
     * @param actionEvent
     * @throws IOException
     */
    public void Add_click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Add-Customer-view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene add_customer_scene = new Scene(root, 500, 400);
        stage.setTitle("Add Customer");
        stage.setScene(add_customer_scene);
        stage.show();
        stage.centerOnScreen();
    }

    /**
     * sends to update customer scene
     * sets up data to be sent
     * @param actionEvent
     * @throws IOException
     * @throws SQLException
     */
    public void Update_click(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Update-Customer-view.fxml"));
        loader.load();
        UpdateCustomerController upc = loader.getController();
        upc.setData(Customer_Records.getSelectionModel().getSelectedItem());

        Parent root = loader.getRoot();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene update_customer_scene = new Scene(root, 500, 400);
        stage.setTitle("Update Customer");
        stage.setScene(update_customer_scene);
        stage.show();
        stage.centerOnScreen();
    }


    /**
     * delete customer from customer table in database
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
    public void Delete_click(ActionEvent actionEvent) throws SQLException, IOException {
        int customerId = Customer_Records.getSelectionModel().getSelectedItem().getCustomerId();
        if(AppointmentDAO.getAllCustomerAppointment(customerId).size() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer " +
                    Customer_Records.getSelectionModel().getSelectedItem().getCustomerName()+ " deleted.");
            alert.setTitle("Customer Deleted.");
            alert.showAndWait().filter(response -> response == ButtonType.OK);
            CustomerDAO.deleteCustomer(customerId);
            Customer_Records.setItems(CustomerDAO.getAllCustomers());

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Customer has Appointments scheduled.");
            alert.setTitle("Can't Delete This Customer.");
            alert.showAndWait().filter(response -> response == ButtonType.OK);
        }

    }

    /**
     * sends to menu scene
     * @param actionEvent
     * @throws IOException
     */
    public void Back_click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Menu-view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene menu_scene = new Scene(root, 450, 300);
        stage.setTitle("Menu");
        stage.setScene(menu_scene);
        stage.show();
        stage.centerOnScreen();
    }


}
