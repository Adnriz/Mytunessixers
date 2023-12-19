package bll.util;

import BE.Song;

import java.util.ArrayList;
import java.util.List;

public class SongSearcher {
    public List<Song> Search(List<Song> SearchList, String query){
        List<Song> SearchResult = new ArrayList<>();

        for (Song song: SearchList){
            if(compareToSongTitle(query, song)){
                SearchResult.add(song);
            }
        }
        return SearchResult;
    }
    private boolean compareToSongTitle(String query, Song song){
        return song.getSongName().toLowerCase().contains(query.toLowerCase());
    }
}
