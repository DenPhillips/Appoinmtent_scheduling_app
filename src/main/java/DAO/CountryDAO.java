package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static DAO.JDBC.connection;

/**
 * Country data access object to interact with database
 */
public class CountryDAO {
    /**
     * @return
     * all countries
     * @throws SQLException
     */
    public static ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM countries";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            Country country = new Country(countryId, countryName,createdBy,
                    lastUpdatedBy);
            allCountries.add(country);
        }
        JDBC.closeConnection();
        return allCountries;
    }


    /**
     * @param countryId
     * @return
     * all countries that the country id = countryId
     * @throws SQLException
     */
    public static Country getCountry(int countryId) throws SQLException {
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM countries WHERE Country_ID = '" + countryId + "'";
        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()){
            countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            Country country= new Country(countryId, countryName,createdBy,
                    lastUpdatedBy);
            return country;
        }
        JDBC.closeConnection();
        return null;
    }
    }

