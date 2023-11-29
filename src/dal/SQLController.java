package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLController {
    private Connection connection;

    public SQLController() {
        // JDBC URL, username, and password
        String url = "jdbc:mysql://10.176.111.34/6_And_The_Music";
        String user = "CSe2023a_e_36 ";
        String password = "CSe2023aE36#23";

        try {
            // Establish the connection
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implement methods to perform SQL operations (insert, select, update, delete)
    // Example methods could include adding music to a playlist, retrieving playlists, etc.

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Other methods for SQL operations...
}
