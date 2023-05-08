package DAO;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static DAO.JDBC.connection;

/**
 * Customer data access object to interact with database
 */
public class CustomerDAO {
    public static Customers getCustomer(int customerId ) throws SQLException {
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM customers WHERE Customer_ID = '" + customerId + "'";
        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()){
            customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionId = rs.getInt("Division_ID");
            Customers customer= new Customers(customerId, customerName, address,
                    postalCode, phone, createdBy,
                    lastUpdatedBy,divisionId);
            return customer;
        }
        JDBC.closeConnection();
        return null;

    }

    /**
     * @return
     * all customers
     * @throws SQLException
     */
    public static ObservableList<Customers> getAllCustomers() throws SQLException {
        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM customers";
        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()){
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionId = rs.getInt("Division_ID");
            Customers customer= new Customers(customerId, customerName, address,
                    postalCode, phone, createdBy, lastUpdatedBy,divisionId);
            allCustomers.add(customer);
        }
        JDBC.closeConnection();
        return allCustomers;
    }


    /**
     * add new customer to customers table
     * @param customerName
     * Customer Name
     * @param address
     * Customer Address
     * @param postalCode
     * Customer Postal Code
     * @param phone
     * Customer Phone Number
     * @param createBy
     * Created by
     * @param updateBy
     * Updated by
     * @param divisionId
     * Division ID
     * @throws SQLException
     */
    public static void addCustomer(String customerName, String address, String postalCode, String phone,
                                    String createBy, String updateBy, int divisionId ) throws SQLException {
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "INSERT INTO customers " +
                "(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                "VALUES ('"
                + customerName + "','" + address + "','" + postalCode + "','" + phone + "', UTC_TIMESTAMP,'"
                + createBy + "', UTC_TIMESTAMP,'" + updateBy + "'," + divisionId + ");";
        stmt.executeUpdate(query);

        JDBC.closeConnection();
    }

    /**
     * update customer from customer table
     * @param customerId
     * Customer ID
     * @param customerName
     * Customer Name
     * @param address
     * Customer Address
     * @param postalCode
     * Customer Postal Code
     * @param phone
     * Customer Phone Number
     * @param updateBy
     * Updated by
     * @param divisionId
     * Division ID
     * @throws SQLException
     */
    public static void updateCustomer(int customerId, String customerName, String address, String postalCode, String phone,
                                    String updateBy, int divisionId ) throws SQLException {
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "UPDATE customers SET Customer_Name = '" + customerName + "', Address = '" + address + "', Postal_Code = '" +
                postalCode + "', Phone = '" + phone + "', Last_Update = UTC_TIMESTAMP, Last_Updated_By ='" + updateBy + "', " +
                "Division_ID = '" + divisionId + "' WHERE Customer_ID = '" + customerId + "'";
        stmt.executeUpdate(query);

        JDBC.closeConnection();
    }

    /**
     * deletes customer from database
     * @param customerId
     * @return
     * boolean if customer was deleted
     * @throws SQLException
     */
    public static boolean deleteCustomer(int customerId) throws SQLException {
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "DELETE FROM customers WHERE Customer_ID = '" + customerId + "'";
        stmt.executeUpdate(query);

        JDBC.closeConnection();
        return true;

    }
}
