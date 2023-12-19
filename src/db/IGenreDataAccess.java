package db;

import BE.Genre;

import java.util.List;

public interface IGenreDataAccess {
    List<Genre> getAllGenre() throws Exception;
}
