package DAO;

import model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

import static DAO.JDBC.connection;

/**
 *User data access object to interact with database
 */
public class UserDAO {

    /**
     * @return
     * all users
     * @throws Exception
     */
    public static ObservableList<User> getAllUsers() throws Exception{
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        JDBC.openConnection();
        Statement stmt = connection.createStatement();
        String query ="select * from users";
        ResultSet result= stmt.executeQuery(query);
        while(result.next()){
            int userid=result.getInt("User_ID");
            String userNameG=result.getString("User_Name");
            String password=result.getString("Password");
            User userResult= new User(userid, userNameG, password);
            allUsers.add(userResult);

        }
        JDBC.closeConnection();
        return allUsers;

    }

}
