package gui.Model;

import bll.util.SongManager;
import BE.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SongModel {
    private ObservableList<Song> songsToBeHeard;


    private SongManager songManager;


    public SongModel() throws Exception {
        songManager = new SongManager();
        songsToBeHeard = FXCollections.observableArrayList();
        songsToBeHeard.addAll(songManager.getAllSongs());
    }



    public ObservableList<Song> getObservableSongs() {
        return songsToBeHeard;
    }


}
