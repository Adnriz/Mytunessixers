package gui.Model;

import bll.util.SongManager;
import BE.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SongModel {
    private ObservableList<Song> songsToBeHeard;


    private SongManager songManager;

    public void SearchMethod(String query) throws Exception{
        List<Song> SearchResults = songManager.SearchSongs(query);
        songsToBeHeard.clear();
        songsToBeHeard.addAll(SearchResults);
    }

    public SongModel() throws Exception {
        songManager = new SongManager();
        songsToBeHeard = FXCollections.observableArrayList();
        songsToBeHeard.addAll(songManager.getAllSongs());
    }



    public ObservableList<Song> getObservableSongs() {
        return songsToBeHeard;
    }


}