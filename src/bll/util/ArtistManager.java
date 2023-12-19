package bll.util;

import BE.Artist;
import db.DAO_DB_Artist;
import db.IArtistDataAccess;

import java.io.IOException;
import java.util.List;

public class ArtistManager {

    private ArtistSearcher artistSearcher = new ArtistSearcher();
    private IArtistDataAccess DAO_DB;

    public ArtistManager() throws IOException {
        DAO_DB = new DAO_DB_Artist();
    }

    public List<BE.Artist> getAllArtist() throws Exception {
        return DAO_DB.getAllArtist();
    }

    public List<BE.Artist> searchArtist(String query) throws Exception {
        List<BE.Artist> allArtist = getAllArtist();
        List<BE.Artist> searchResult = artistSearcher.search(allArtist, query);
        return searchResult;
    }

    public Artist createArtist(Artist newArtist) throws Exception {
        return (Artist) DAO_DB.createArtist(newArtist);
    }

    public Artist updateArtist(Artist selectedSong) throws Exception {
        return (Artist) DAO_DB.updateArtist(selectedSong);
    }

    public Artist findArtistByName(String name) throws Exception {
        DAO_DB.findArtistByName(name);
        return null;
    }

    public class Artist extends BE.Artist {
        public Artist(String name, int id) {
            super(name, id);
        }
    }
}
