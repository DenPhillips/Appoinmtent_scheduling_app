package DAO;

import helper.Helper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.*;
import java.time.*;

import static DAO.JDBC.connection;

/**
 * Appointment data access object to interact with database
 */
public class AppointmentDAO {
    /**
     * @return
     * all appointments
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAllAppointments() throws SQLException {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM appointments";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime dbStart = rs.getObject("Start", LocalDateTime.class);
            ZonedDateTime sZoned = dbStart.atZone(ZoneId.of("UTC"));
            String start = sZoned.withZoneSameInstant(ZoneId.systemDefault()).format(Helper.formatter);
            LocalDateTime dbEnd = rs.getObject("End", LocalDateTime.class);
            ZonedDateTime eZoned = dbEnd.atZone(ZoneId.of("UTC"));
            String end = eZoned.withZoneSameInstant(ZoneId.systemDefault()).format(Helper.formatter);
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            Appointments appointment = new Appointments(appointmentId, title, description,
                    location, type, start, end, createdBy, lastUpdatedBy, customerId, userId, contactId);
            allAppointments.add(appointment);
        }
        JDBC.closeConnection();
        return allAppointments;
    }

    /**
     * @param customer_Id
     * Customer ID
     * @return
     * all appointments where customer id = customer_Id
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAllCustomerAppointment(int customer_Id) throws SQLException {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM appointments WHERE Customer_ID = '" + customer_Id + "'";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime dbStart = rs.getObject("Start", LocalDateTime.class);
            ZonedDateTime sZoned = dbStart.atZone(ZoneId.of("UTC"));
            String start = sZoned.withZoneSameInstant(ZoneId.systemDefault()).format(Helper.formatter);
            LocalDateTime dbEnd = rs.getObject("End", LocalDateTime.class);
            ZonedDateTime eZoned = dbEnd.atZone(ZoneId.of("UTC"));
            String end = eZoned.withZoneSameInstant(ZoneId.systemDefault()).format(Helper.formatter);
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            Appointments appointment = new Appointments(appointmentId, title, description,
                    location, type, start, end, createdBy, lastUpdatedBy, customerId, userId, contactId);
            allAppointments.add(appointment);
        }
        JDBC.closeConnection();
        return allAppointments;
    }

    /**
     * @param contact_Id
     * Contact ID
     * @return
     * all appointments where contact id = contact_Id
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAllContactAppointment(int contact_Id) throws SQLException {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM appointments WHERE Contact_ID = '" + contact_Id + "'";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime dbStart = rs.getObject("Start", LocalDateTime.class);
            ZonedDateTime sZoned = dbStart.atZone(ZoneId.of("UTC"));
            String start = sZoned.withZoneSameInstant(ZoneId.systemDefault()).format(Helper.formatter);
            LocalDateTime dbEnd = rs.getObject("End", LocalDateTime.class);
            ZonedDateTime eZoned = dbEnd.atZone(ZoneId.of("UTC"));
            String end = eZoned.withZoneSameInstant(ZoneId.systemDefault()).format(Helper.formatter);
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            Appointments appointment = new Appointments(appointmentId, title, description,
                    location, type, start, end, createdBy, lastUpdatedBy, customerId, userId, contactId);
            allAppointments.add(appointment);
        }
        JDBC.closeConnection();
        return allAppointments;
    }

    /**
     * add appointment to the appointments table
     * @param title
     * Appointment Title
     * @param description
     * Appointment Description
     * @param location
     * Appointment Location
     * @param type
     * Appointment Tyoe
     * @param start
     * Appointment Start
     * @param end
     * Appointment End
     * @param createdBy
     * Created By
     * @param lastUpdateBy
     * Last Updated By
     * @param customerId
     * Customer ID
     * @param userId
     * User ID
     * @param contactId
     * Contact ID
     * @throws SQLException
     */
    public static void addAppointment(String title, String description,
                                      String location, String type,
                                      Timestamp start, Timestamp end, String createdBy,
                                      String lastUpdateBy, int customerId, int userId, int contactId) throws SQLException {
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "INSERT INTO appointments " + "(Title, Description, Location, Type, Start, End, "
                + "Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID ) " +
                "VALUES ('" + title + "','" + description + "','" + location + "','" +
                type + "','" + start + "','" + end + "', UTC_TIMESTAMP, '" + createdBy + "', UTC_TIMESTAMP, '"
                + lastUpdateBy + "','" + customerId + "','" + userId + "','" + contactId + "');";
        stmt.executeUpdate(query);

        JDBC.closeConnection();
    }

    /**
     * @return
     * all appointments for current week
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAllWeekAppointments() throws SQLException {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM appointments WHERE YEARWEEK(Start, 0) = YEARWEEK(NOW(),0); ";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime dbStart = rs.getObject("Start", LocalDateTime.class);
            ZonedDateTime sZoned = dbStart.atZone(ZoneId.of("UTC"));
            String start = sZoned.withZoneSameInstant(ZoneId.systemDefault()).format(Helper.formatter);
            LocalDateTime dbEnd = rs.getObject("End", LocalDateTime.class);
            ZonedDateTime eZoned = dbEnd.atZone(ZoneId.of("UTC"));
            String end = eZoned.withZoneSameInstant(ZoneId.systemDefault()).format(Helper.formatter);
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            Appointments appointment = new Appointments(appointmentId, title, description,
                    location, type, start, end, createdBy, lastUpdatedBy, customerId, userId, contactId);
            allAppointments.add(appointment);
        }
        JDBC.closeConnection();
        return allAppointments;
    }

    /**
     * @return
     * all appointments for the current month
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAllMonthAppointments() throws SQLException {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(NOW()); ";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime dbStart = rs.getObject("Start", LocalDateTime.class);
            ZonedDateTime sZoned = dbStart.atZone(ZoneId.of("UTC"));
            String start = sZoned.withZoneSameInstant(ZoneId.systemDefault()).format(Helper.formatter);
            LocalDateTime dbEnd = rs.getObject("End", LocalDateTime.class);
            ZonedDateTime eZoned = dbEnd.atZone(ZoneId.of("UTC"));
            String end = eZoned.withZoneSameInstant(ZoneId.systemDefault()).format(Helper.formatter);
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            Appointments appointment = new Appointments(appointmentId, title, description,
                    location, type, start, end, createdBy, lastUpdatedBy, customerId, userId, contactId);
            allAppointments.add(appointment);
        }
        JDBC.closeConnection();
        return allAppointments;
    }

    /**
     * @param appointmentId
     * @return
     * boolean if appointment is deleted form appointments table
     * @throws SQLException
     */
    public static boolean deleteAppointment(int appointmentId) throws SQLException {
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "DELETE FROM appointments WHERE Appointment_ID = '" + appointmentId + "'";
        stmt.executeUpdate(query);

        JDBC.closeConnection();
        return true;

    }

    /**
     * update appointment is appointments table id Appointment ID = appointmentId
     * @param title
     * Appointment Title
     * @param description
     * Appointment Description
     * @param location
     * Appointment Location
     * @param type
     * Appointment Type
     * @param start
     * Appointment Start
     * @param end
     * Appointment End
     * @param lastUpdateBy
     * Last Updated By
     * @param customerId
     * Customer ID
     * @param userId
     * User ID
     * @param contactId
     * Contact ID
     * @param appointmentId
     * Appointment ID
     * @throws SQLException
     */
    public static void updateAppointment(String title, String description,
                                         String location, String type,
                                         Timestamp start, Timestamp end,
                                         String lastUpdateBy, int customerId, int userId, int contactId,int appointmentId) throws SQLException {
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "UPDATE appointments SET Title = '" + title + "', Description = '" +
                description + "', Location = '" + location + "', Type = '" + type + "', Start = '" + start +
                "', End = '" + end + "', Last_Update = UTC_TIMESTAMP, Last_Updated_By ='" + lastUpdateBy + "', " +
                "Customer_ID = '" + customerId + "', User_ID = '" + userId + "', Contact_ID = '" + contactId + "' WHERE Appointment_ID = '" + appointmentId + "'";
        stmt.executeUpdate(query);

        JDBC.closeConnection();
    }

    /**
     * @param user_Id
     * User ID
     * @return
     * get all appointments where User ID = user_Id
     * @throws SQLException
     */
    public static Appointments getAllUserAppointment(int user_Id) throws SQLException {
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM appointments WHERE User_ID = '" + user_Id + "' AND TIMEDIFF(Start, UTC_TIMESTAMP) <= TIME('00:15:00') AND TIMEDIFF(Start, UTC_TIMESTAMP) >= TIME('-00:15:00')";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime dbStart = rs.getObject("Start", LocalDateTime.class);
            ZonedDateTime sZoned = dbStart.atZone(ZoneId.of("UTC"));
            String start = sZoned.withZoneSameInstant(ZoneId.systemDefault()).format(Helper.formatter);
            LocalDateTime dbEnd = rs.getObject("End", LocalDateTime.class);
            ZonedDateTime eZoned = dbEnd.atZone(ZoneId.of("UTC"));
            String end = eZoned.withZoneSameInstant(ZoneId.systemDefault()).format(Helper.formatter);
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            Appointments appointment = new Appointments(appointmentId, title, description,
                    location, type, start, end, createdBy, lastUpdatedBy, customerId, userId, contactId);
            return appointment;
        }
        JDBC.closeConnection();
        return null;
    }

    /**
     * @param month
     * Month
     * @param type
     * Appointment Type
     * @return
     * counts the number of appointments of month and type
     * @throws SQLException
     */
    public static String CustomerReport(int month, String type) throws SQLException {
        String count = "";
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT COUNT(*) As count FROM appointments WHERE MONTH(Start) = '" + month + "' AND Type = '" + type + "';";
        stmt.executeQuery(query);
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            count = rs.getString("count");
        }

        JDBC.closeConnection();
        return count;

    }



}

