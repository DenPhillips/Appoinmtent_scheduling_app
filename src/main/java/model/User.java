package model;

/**
 * class to create A User object
 */
public class User {
    private int userId;
    private String userName;
    private String password;


    /**
     * constructs the user class
     * @param userId
     * User ID
     * @param userName
     * User Name
     * @param password
     * Password
     */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;

    }

    /**
     * @return
     * returns the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId
     * sets userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return
     * returns the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     * sets the userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return
     * returns the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     * sets the password
     */
    public void setPassword(String password) {
        this.password = password;
    }



}

