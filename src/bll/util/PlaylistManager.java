package bll.util;


import BE.Playlist;
import dal.PlaylistDB;
import dal.SongDB;
import BE.Song;

import java.io.IOException;
import java.util.List;

public class PlaylistManager {

    public PlaylistManager() throws IOException {
        this.playlistDB = new PlaylistDB();
    }
    private PlaylistDB playlistDB;

    public List<Playlist> getAllPlaylist() throws Exception {
        return playlistDB.getAllPlaylist();
    }



}
