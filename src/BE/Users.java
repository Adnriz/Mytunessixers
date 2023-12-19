package BE;

public class Users {
    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;

    public Users(int userID, String userName, String userEmail, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public Users() {
    }

    public int getUserID() {
        return this.userID;
    }

    private void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return this.userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    private void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    private void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
