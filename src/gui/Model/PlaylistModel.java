package gui.Model;

import BE.Playlist;
import BE.PlaylistSong;
import BE.Song;
import bll.util.PlaylistManager;
import bll.util.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistModel {
    private ObservableList<Playlist> playlistToBeHeard;
    private ObservableList<Song> songsInPlaylist;
    private Playlist playlist;


    private PlaylistManager playlistManager;


    public PlaylistModel() throws Exception {
        playlistManager = new PlaylistManager();
        playlistToBeHeard = FXCollections.observableArrayList();
        playlistToBeHeard.addAll(playlistManager.getAllPlaylist());

        songsInPlaylist = FXCollections.observableArrayList();
    }

    public ObservableList<Playlist> getObservablePlaylists() {
        return playlistToBeHeard;
    }

    public PlaylistSong addSongToPlaylist(PlaylistSong playlistSong) throws  Exception {
        return playlistManager.addSongToPlaylist(playlistSong);
    }

    public void updateSongsInPlaylist(Playlist playlist) throws Exception {
        songsInPlaylist.clear();
        songsInPlaylist.addAll(playlistManager.getSongsInPlaylist(playlist));
    }

    public void updatePlaylist() throws Exception {
        playlistToBeHeard.clear();
        playlistToBeHeard.addAll(playlistManager.getAllPlaylist());
    }

    public ObservableList<Song> getObservableSongsInPlaylist() {
        return songsInPlaylist;
    }
    public void removeFromPlaylist(PlaylistSong playlistSong) throws Exception {
        playlistManager.removeFromPlaylist(playlistSong);
    }

    public Playlist getPlaylist(){
        return playlist;
    }

    public void setPlaylist(Playlist playlist){
        this.playlist = playlist;
    }

    public void deletePlaylist(Playlist playlist) throws Exception {
        playlistManager.deletePlaylist(playlist);
    }
}
