package dal;

import BE.Song;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongDB {
    private SQLController sqlController;

    public SongDB() throws IOException {
        this.sqlController = new SQLController();
    }

    public List<Song> getAllSongs() throws Exception {

        ArrayList<Song> allSongs = new ArrayList<>();

        try (Connection conn = sqlController.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM dbo.Music;";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Movie object
                String name = rs.getString("title");
                String artist = rs.getString("artist");
                String genre = rs.getString("genre");
                String filePath = rs.getString("filepath");

                int id = rs.getInt("GenreID");

                Song song = new Song(name, artist, genre, id, filePath);
                allSongs.add(song);
            }
            return allSongs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not get the song from database", ex);
        }


    }
}
