package bll.util;


import db.DAO_DB_Songs;
import db.ISongDataAccess;
import BE.Song;

import java.io.IOException;
import java.util.List;

public class SongManager {
    private SongSearcher songSearcher = new SongSearcher();
    private ISongDataAccess DAO_DB;

    public SongManager() throws IOException {
        DAO_DB = (ISongDataAccess) new DAO_DB_Songs();
    }

    public static List<Song> Search(String query) {
        return null;
    }

    public List<Song> getAllSongs() throws Exception {
        return DAO_DB.getAllSongs();
    }

    public List<Song> searchSong(String query) throws Exception {
        List<Song> allSongs = getAllSongs();
        List<Song> searchResult = songSearcher.search(allSongs, query);
        return searchResult;
    }

    public Song createSong(Song newSong) throws Exception {
        return DAO_DB.createSong(newSong);
    }

    public Song deleteSong(Song deletedSong) throws Exception {
        return DAO_DB.deleteSong(deletedSong);
    }

    public Song updateSong(Song selectedSong) throws Exception {
        return DAO_DB.updateSong(selectedSong);
    }
    /*
public SongManager() throws IOException {
    this.songDB = new SongDB();
}
    private SongDB songDB;
    public static List<Song> Search(String query) {
        return null;
    }
    public List<Song> getAllSongs() throws Exception {
            return songDB.getAllSongs();
        }
*/

}
