package bll.util;

import BE.Users;
import db.DAO_DB_Users;

import java.io.IOException;
import java.util.List;

public class UsersManager {
    private DAO_DB_Users DAO_DB = new DAO_DB_Users();

    public UsersManager() throws IOException {
    }

    public List<Users> getAllUsers() throws Exception {
        return this.DAO_DB.getAllUsers();
    }

    public Users createUser(Users newUser) throws Exception {
        return this.DAO_DB.createUser(newUser);
    }

    public Users updateUser(Users user) throws Exception {
        return this.DAO_DB.updateUser(user);
    }

    public Users validateUser(String userName, String password) throws Exception {
        return this.DAO_DB.validateUser(userName, password);
    }
}
