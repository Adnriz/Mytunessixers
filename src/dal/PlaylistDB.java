package dal;

import BE.Playlist;
import BE.PlaylistSong;
import BE.Song;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                int id = rs.getInt("id");
                Playlist playlist = new Playlist(name, id);
                allPlaylist.add(playlist);
            }
            return allPlaylist;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not get the song from database", ex);
        }
    }

    public PlaylistSong addSongToPlaylist(PlaylistSong playlistSong) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sqlInsert = "INSERT INTO dbo.PlaylistSong (playlist_id, song_id) VALUES (?, ?);";

        try {
            conn = sqlController.getConnection();
            conn.setAutoCommit(false);

            // Prepare the SQL statement
            pstmt = conn.prepareStatement(sqlInsert);

            // Set parameters
            pstmt.setInt(1, playlistSong.getPlaylistId());
            pstmt.setInt(2, playlistSong.getSongId());

            // Execute the insert operation


            pstmt.executeUpdate();
            PlaylistSong playlistSongs = new PlaylistSong(playlistSong.getPlaylistId(), playlistSong.getSongId());


            // Commit the transaction
            conn.commit();
        } catch (SQLException e) {

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    e.addSuppressed(ex);
                }
            }
            throw new Exception("Could not add song to playlist", e);
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return playlistSong;
    }

    public List<Song> getSongsInPlaylist(int playlistId) throws Exception {
        List<Song> songs = new ArrayList<>();

        String sql = "SELECT s.* FROM dbo.Music s INNER JOIN PlaylistSong ps ON s.GenreID = ps.song_id WHERE ps.playlist_id = ?";

        try (Connection conn = sqlController.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playlistId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("title");
                    String artist = rs.getString("artist");
                    String genre = rs.getString("genre");
                    String filePath = rs.getString("filepath");

                    int id = rs.getInt("GenreID");

                    songs.add(new Song(name, artist, genre, id, filePath));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Could not retrieve songs from playlist", e);
        }

        return songs;
    }

    public void deleteSongFromPlaylist(PlaylistSong playlistSong) throws Exception {
        try {
            if (sqlController == null) {
                try {
                    sqlController = new SQLController();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            String sql = "DELETE FROM dbo.PlaylistSong WHERE playlist_id = ? AND song_id = ?;";
            try (Connection conn = sqlController.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setInt(1, playlistSong.getPlaylistId());
                stmt.setInt(2, playlistSong.getSongId());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Could not delete song", ex);
            }
        } finally {

        }
    }

    public void deletePlaylist(Playlist playlist) throws Exception {
        try {
            if (sqlController == null) {
                try {
                    sqlController = new SQLController();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            String sql = "DELETE FROM dbo.PlayList WHERE id = ?";
            try (Connection conn = sqlController.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setInt(1, playlist.getId());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Could not delete song", ex);
            }
        } finally {

        }
    }
}
