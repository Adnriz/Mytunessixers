package bll.util;


import dal.SongDB;
import gui.Song;

import java.io.IOException;
import java.util.List;

public class SongManager {

    public SongManager() throws IOException {
        this.songDB = new SongDB();
    }
    private SongDB songDB;

        public List<Song> getAllSongs() throws Exception {
            return songDB.getAllSongs();
        }


}
