package bll.util;

import BE.Artist;

import java.util.ArrayList;
import java.util.List;

public class ArtistSearcher {
    public List<Artist> search(List<Artist> searchBase, String query) {
        List<Artist> searchResult = new ArrayList<>();

        for (Artist artist : searchBase) {
            if(compareToSongArtist(query, artist))
            {
                searchResult.add(artist);
            }
        }
        return searchResult;
    }

    private boolean compareToSongArtist(String query, Artist artist){
        return artist.getName().toLowerCase().contains(query.toLowerCase());
    }

}
