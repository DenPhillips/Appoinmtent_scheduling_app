package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.First_Level_Divisions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static DAO.JDBC.connection;

/**
 * First Level Division data access object to interact with database
 */
public class FLDDAO {
    /**
     * @param country_Id
     * Country ID
     * @return
     * all first level divisions where country id = county_Id
     * @throws SQLException
     */
    public static ObservableList<First_Level_Divisions> getFLDByCountryId(int country_Id) throws SQLException {
        ObservableList<First_Level_Divisions> allFLD = FXCollections.observableArrayList();
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM first_level_divisions WHERE Country_ID = '" + country_Id + "' ";
        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()){
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryId = rs.getInt("Country_ID");
            First_Level_Divisions FLD= new First_Level_Divisions(divisionId, division, createdBy,
                    lastUpdatedBy, countryId);
            allFLD.add(FLD);
        }
        JDBC.closeConnection();
        return allFLD;
    }

    /**
     * @param FLDID
     * first level division id
     * @return
     * First level division where division id = FLDID
     * @throws SQLException
     */
    public static First_Level_Divisions getFLDById(int FLDID) throws SQLException {
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM first_level_divisions WHERE Division_ID = '" + FLDID + "'";
        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()){
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryId = rs.getInt("Country_ID");
            First_Level_Divisions FLD= new First_Level_Divisions(divisionId, division, createdBy,
                    lastUpdatedBy, countryId);
            return FLD;
        }
        JDBC.closeConnection();
        return null;

    }

    /**
     * @param fldName
     * first level division name
     * @return
     * First Level Division where divison = fldName
     * @throws SQLException
     */
    public static First_Level_Divisions getFLDByName(String fldName) throws SQLException {
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM first_level_divisions WHERE Division = '" + fldName + "' ";
        ResultSet rs = stmt.executeQuery(query);
        First_Level_Divisions FLD = null;

        while(rs.next()){
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryId = rs.getInt("Country_ID");
            FLD= new First_Level_Divisions(divisionId, division,  createdBy,
                    lastUpdatedBy, countryId);
        }
        JDBC.closeConnection();
        return FLD;
    }
}
