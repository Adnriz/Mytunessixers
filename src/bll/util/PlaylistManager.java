package bll.util;


import BE.Playlist;
import db.DAO_DB_Playlists;
import db.IPlaylistDataAccess;
import db.PlaylistDB;

import java.io.IOException;
import java.util.List;

public class PlaylistManager {

 /*   public PlaylistManager() throws IOException {
        this.playlistDB = new PlaylistDB();
    }
    private PlaylistDB playlistDB;

    public List<Playlist> getAllPlaylist() throws Exception {
        return playlistDB.getAllPlaylist();
    }
*/
 private PlaylistSearcher playlistSearcher = new PlaylistSearcher();
    private IPlaylistDataAccess DAO_DB;

    public PlaylistManager() throws IOException {
        DAO_DB = new DAO_DB_Playlists();
    }

    public List<Playlist> getAllPlaylists() throws Exception {
        return DAO_DB.getAllPlaylists();
    }

    public List<Playlist> searchPlaylist(String query) throws Exception {
        List<Playlist> allPlaylists = getAllPlaylists();
        List<Playlist> searchResult = playlistSearcher.search(allPlaylists, query);
        return searchResult;
    }

    public Playlist createPlaylist(Playlist newPlaylist, int userID) throws Exception {
        return DAO_DB.createPlaylist(newPlaylist, userID);
    }

    public void deletePlaylist(Playlist deletedPlaylist, int userID) throws Exception {
        DAO_DB.deletePlaylist(deletedPlaylist, userID);
    }

    public Playlist updatePlaylist(Playlist selectedPlaylist, int userID) throws Exception {
        return DAO_DB.updatePlaylist(selectedPlaylist, userID);
    }

    public List<Playlist> getUserPlaylist(int userID) throws Exception {
        return DAO_DB.getUserPlaylist(userID);
    }

    public Playlist getAllPlaylist() {
        return null;
    }
}
