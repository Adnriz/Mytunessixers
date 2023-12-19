package bll.util;

import BE.PlaylistSongs;
import BE.Song;
import db.DAO_DB_PlaylistSongs;
import db.IPlaylistSongsDataAccess;

import java.io.IOException;
import java.util.List;

public class PlaylistSongsManager {
    private static IPlaylistSongsDataAccess DAO_DB;

    public PlaylistSongsManager() throws IOException {
        DAO_DB = new DAO_DB_PlaylistSongs();
    }

    public PlaylistSongs addSongToPlaylist(PlaylistSongs newPlaylistSong) throws Exception {
        return DAO_DB.addSongToPlaylist(newPlaylistSong);
    }

    public static PlaylistSongs removeSongFromPlaylist(PlaylistSongs removedPlaylistSong) throws Exception {
        return DAO_DB.removeSongFromPlaylist(removedPlaylistSong);
    }

    public List<Song> fetchSongsForPlaylist(int playlistId) throws Exception {
        // Assuming playlistId is the ID of the playlist for which you want to fetch songs
        return DAO_DB.getPlaylistSong(playlistId);
    }
}
