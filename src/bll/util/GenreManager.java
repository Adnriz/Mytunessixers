package bll.util;

import BE.Genre;
import db.DAO_DB_Genre;
import db.IGenreDataAccess;

import java.io.IOException;
import java.util.List;

public class GenreManager {
    private GenreSearcher genreSearcher = new GenreSearcher();
    private IGenreDataAccess DAO_DB;

    public GenreManager() throws IOException {
        DAO_DB = new DAO_DB_Genre();
    }

    public List<Genre> getAllGenre() throws Exception {
        return DAO_DB.getAllGenre();
    }
}
