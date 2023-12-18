package dal;

import BE.Playlist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlaylistDB {
    private SQLController sqlController;

    public PlaylistDB() throws IOException {
        this.sqlController = new SQLController();
    }

    public ArrayList<Playlist> getAllPlaylist() throws Exception {

        ArrayList<Playlist> allPlaylist = new ArrayList<>();

        try (Connection conn = sqlController.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM dbo.PlayList;";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Movie object
                String name = rs.getString("name");

                Playlist playlist = new Playlist(name);
                allPlaylist.add(playlist);
            }
            return allPlaylist;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not get the song from database", ex);
        }


    }
}
