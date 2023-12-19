package bll.util;


import BE.Playlist;
import BE.PlaylistSong;
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

    public PlaylistSong addSongToPlaylist(PlaylistSong playlistSong) throws Exception {
        return playlistDB.addSongToPlaylist(playlistSong);
    }

    public List<Song> getSongsInPlaylist(Playlist playlist) throws Exception {
        return playlistDB.getSongsInPlaylist(playlist.getId());
    }
    public void removeFromPlaylist(PlaylistSong playlistSong) throws Exception {
        playlistDB.deleteSongFromPlaylist(playlistSong);
    }

    public void deletePlaylist(Playlist playlist) throws Exception{
        playlistDB.deletePlaylist(playlist);
    }
}
