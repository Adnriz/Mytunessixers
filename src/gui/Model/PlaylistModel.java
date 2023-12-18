package gui.Model;

import BE.Playlist;
import BE.Song;
import bll.util.PlaylistManager;
import bll.util.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistModel {
    private ObservableList<Playlist> playlistToBeHeard;


    private PlaylistManager playlistManager;


    public PlaylistModel() throws Exception {
        playlistManager = new PlaylistManager();
        playlistToBeHeard = FXCollections.observableArrayList();
        playlistToBeHeard.addAll(playlistManager.getAllPlaylist());
    }



    public ObservableList<Playlist> getObservablePlaylists() {
        return playlistToBeHeard;
    }


}
