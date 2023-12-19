package gui.Model;

import BE.*;
import bll.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SongModel {
    private ObservableList<Song> listOfSongs = FXCollections.observableArrayList();
    private ObservableList<Artist> searchedArtist;
    private ObservableList<Playlist> listOfPlaylists;
    private ObservableList<PlaylistSongs> listSongsFromPlaylist;
    private ObservableList<Song> listOfSongInPlaylist = FXCollections.observableArrayList();
    private Song selectedSong;
    private Playlist selectedPlaylist;
    private SongManager songManager = new SongManager();
    private ArtistManager artistManager;
    private GenreManager genreManager = new GenreManager();
    private PlaylistManager playlistManager;
    private PlaylistSongsManager playlistSongsManager;
    private UsersManager usersManager = new UsersManager();

   private final ObservableList<Song> songsToBeHeard;


    {
        songManager = new SongManager();
    }


    public SongModel() throws Exception {
        songsToBeHeard = FXCollections.observableArrayList();
        songsToBeHeard.addAll(songManager.getAllSongs());
    }



    public ObservableList<Song> getObservableSongs() {
        return songsToBeHeard;
    }

    public void searchSong(String query) throws Exception {
        List<Song> searchResults = SongManager.Search(query);
        songsToBeHeard.clear();
        assert searchResults != null;
        songsToBeHeard.addAll(searchResults);
    }
}
