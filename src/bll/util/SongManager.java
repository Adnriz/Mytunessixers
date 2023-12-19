package bll.util;


import dal.SongDB;
import BE.Song;

import java.io.IOException;
import java.util.List;

public class SongManager {

    private SongSearcher songSearcher = new SongSearcher();

    public List<Song> SearchSongs(String query) throws Exception{
        List<Song> AllSongs = getAllSongs();
        List<Song> SearchResult = songSearcher.Search(AllSongs, query);
        return SearchResult;
    }

    public SongManager() throws IOException {
        this.songDB = new SongDB();
    }
    private SongDB songDB;

    public List<Song> getAllSongs() throws Exception {
        return songDB.getAllSongs();
    }


}