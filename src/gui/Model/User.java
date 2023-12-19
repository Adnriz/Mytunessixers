// User.java
package gui.Model;

import gui.Model.SongControllor;

public class User {

    private SongControllor songControllor;
    private String username;

    // Constructor, getters, setters.

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;

    }


}
