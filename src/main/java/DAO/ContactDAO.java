package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static DAO.JDBC.connection;

/**
 * Contact data access object to interact with database
 */
public class ContactDAO {
    /**
     * @return
     * all contacts
     * @throws SQLException
     */
    public static ObservableList<Contacts> getAllContacts() throws SQLException {
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM contacts";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            Contacts contact = new Contacts(contactId, contactName, email);
            allContacts.add(contact);
        }
        JDBC.closeConnection();
        return allContacts;
    }

    /**
     * @param contactId
     * Contact ID
     * @return
     * contact name of contact where contact id = contactId
     * @throws SQLException
     */
    public static String getContactName(int contactId) throws SQLException {
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM contacts WHERE Contact_ID = '" + contactId + "';";
        ResultSet rs = stmt.executeQuery(query);

        String contactName = "";
        while (rs.next()) {
            contactName = rs.getString("Contact_Name");

        }
        JDBC.closeConnection();
        return contactName;

    }
}
