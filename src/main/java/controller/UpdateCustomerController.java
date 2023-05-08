package controller;

import DAO.CountryDAO;
import DAO.CustomerDAO;
import DAO.FLDDAO;
import helper.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class UpdateCustomerController implements Initializable {
    public TextField Customer_id_text;
    public TextField Phone_number;
    public TextField Postal_code_text;
    public TextField Address_text;
    public TextField Customer_name_text;
    public ComboBox Country_picker;
    public ComboBox State_picker;
    private int CountryId;

    /**
     * gets data from customer table and sets it
     * @param customer
     * @throws SQLException
     */
    public void setData(Customers customer) throws SQLException {
        CountryId = FLDDAO.getFLDById(customer.getDivisionId()).getCountryId();
        Customer_id_text.setText(String.valueOf(customer.getCustomerId()));
        Customer_name_text.setText(String.valueOf(customer.getCustomerName()));
        Phone_number.setText(String.valueOf(customer.getPhone()));
        Address_text.setText(String.valueOf(customer.getAddress()));
        Postal_code_text.setText(String.valueOf(customer.getPostalCode()));
        Country_picker.setValue(CountryDAO.getCountry(CountryId).getCountryName());
        State_picker.setValue(FLDDAO.getFLDById(customer.getDivisionId()).getDivision());
        try{
            for(int i = 0; i < CountryDAO.getAllCountries().size(); i++) {
                Country_picker.getItems().addAll(CountryDAO.getAllCountries().get(i).getCountryName());
            }

            for(int j = 0; j < FLDDAO.getFLDByCountryId(CountryId).size(); j++){
                State_picker.getItems().addAll(FLDDAO.getFLDByCountryId(CountryId).get(j).getDivision());
            }

        }catch(Exception e){
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        }


    /**
     * If all fields are filled, updates current customer dyta in table
     * @param actionEvent
     * @throws IOException
     * @throws SQLException
     */
    public void Add_update_click(ActionEvent actionEvent) throws IOException, SQLException {
        String userName = Helper.loggedInUser.getUserName();
        int id = Integer.valueOf(Customer_id_text.getText());
        String Cname = Customer_name_text.getText();
        String phone = Phone_number.getText();
        String address = Address_text.getText();
        String postal = Postal_code_text.getText();
        String name;

        if(Cname.isEmpty()){
            Helper.Error("Name");
            return;
        }
        if(address.isEmpty()){
            Helper.Error("Address");
            return;
        }
        if(postal.isEmpty()){
            Helper.Error("Postal Code");
            return;
        }
        if(phone.isEmpty()){
            Helper.Error("Phone Number");
            return;
        }
        if(State_picker.getValue() == null){
            Helper.Error("State");
            return;
        }else {
            name = State_picker.getValue().toString();
        }

        int divisionId = FLDDAO.getFLDByName(name).getDivisionId();


        try {
            CustomerDAO.updateCustomer(id, Cname, address, postal, phone, userName, divisionId);
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
    }

    /**
     * go back to customer records scene
     * @param actionEvent
     * @throws IOException
     */
    public void Back_click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customer-Record-view.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene customer_record_scene = new Scene(root, 650, 315);
        stage.setTitle("Customer Records");
        stage.setScene(customer_record_scene);
        stage.show();
        stage.centerOnScreen();;
    }


    /**
     * sets state list when country is picked
     * @param actionEvent
     * @throws SQLException
     */
    public void Country_action(ActionEvent actionEvent) throws SQLException {
        if(Country_picker.getValue() != null){
            for(int i = 0; i < CountryDAO.getAllCountries().size(); i++){
                if(CountryDAO.getAllCountries().get(i).getCountryName().equals(Country_picker.getValue())){
                    State_picker.getItems().clear();
                    for(int j = 0; j < FLDDAO.getFLDByCountryId(i+1).size(); j++){
                        State_picker.getItems().addAll(FLDDAO.getFLDByCountryId(i+1).get(j).getDivision());
                    }
                }
            }
        }
    }
}
