package gui.Model;

import BE.Playlist;
import bll.util.SongManager;
import BE.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SongModel {
    private ObservableList<Song> songsToBeHeard;

    private static SongModel instance;  // Singleton instance

    private SongManager songManager;

    public static SongModel getInstance() throws Exception {
        if (instance == null)
            instance = new SongModel();
        return instance;
    }

    private SongModel() throws Exception {
        songManager = new SongManager();
        songsToBeHeard = FXCollections.observableArrayList();
        songsToBeHeard.addAll(songManager.getAllSongs());
    }



    public void SearchMethod(String query) throws Exception{
        List<Song> SearchResults = songManager.SearchSongs(query);
        songsToBeHeard.clear();
        songsToBeHeard.addAll(SearchResults);
    }


    public ObservableList<Song> getObservableSongs() {
        return songsToBeHeard;
    }

    public void updateSongs() throws Exception {
        songsToBeHeard.clear();
        songsToBeHeard.addAll(songManager.getAllSongs());
    }


}
