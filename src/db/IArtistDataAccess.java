package db;

import BE.Artist;
import bll.util.ArtistManager;

import java.sql.SQLException;
import java.util.List;

public interface IArtistDataAccess {
    List<Artist> getAllArtist() throws Exception;

    public Artist createArtist(Artist artist) throws Exception;

    public Artist updateArtist(Artist artist) throws Exception;

    public Artist findArtistByName(String name) throws SQLException;

}
