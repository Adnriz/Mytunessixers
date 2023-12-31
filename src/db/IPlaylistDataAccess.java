package db;

import BE.Playlist;

import java.util.List;

public interface IPlaylistDataAccess {

    List<Playlist> getAllPlaylists() throws Exception;

    public Playlist createPlaylist(Playlist playlist, int userID) throws Exception;

    public Playlist updatePlaylist(Playlist playlist, int userID) throws Exception;

    public void deletePlaylist(Playlist playlist, int userID) throws Exception;

    List<Playlist> getUserPlaylist(int userID) throws Exception;
}
