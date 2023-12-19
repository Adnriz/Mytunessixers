package bll.util;


import BE.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SongSearcher {

    public List<Song> search(List<Song> searchBase, String query) {
        List<Song> searchResult = new ArrayList<>();

        for (Song song : searchBase) {
            if(compareToSongTitle(query, song))
            {
                searchResult.add(song);
            }
        }
        return searchResult;
    }

    private boolean compareToSongTitle(String query, Song song) {
        return song.getTitle().toLowerCase().contains(query.toLowerCase());
    }

    private boolean comparetoSongArtist(String query, Song song){
        return song.getArtist().toLowerCase().contains(query.toLowerCase());
    }

    private boolean comparetoSongGenre(String query, Song song){
        return song.getGenre().toLowerCase().contains(query.toLowerCase());
    }


}

