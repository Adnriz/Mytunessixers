package db;

import BE.PlaylistSongs;
import BE.Song;

import java.util.List;

public interface IPlaylistSongsDataAccess {
    List<Song> getPlaylistSong(int playlistId) throws Exception;

    PlaylistSongs addSongToPlaylist(PlaylistSongs playlistSongs) throws Exception;

    PlaylistSongs removeSongFromPlaylistById(int songId, int playlistId) throws Exception;

    List<Song> fetchSongsForPlaylist(int playlistId) throws Exception;

    PlaylistSongs removeSongFromPlaylistByIndex(int songIndex, int playlistId) throws Exception;

    PlaylistSongs removeSongFromPlaylist(PlaylistSongs removedPlaylistSong) throws Exception;
}
