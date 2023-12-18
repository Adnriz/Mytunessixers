package bll.util;


import dal.SongDB;
import gui.Song;

import java.io.IOException;
import java.util.List;

public class SongManager {
    private SongDB songDB;

        public List<Song> getAllSongs() throws Exception {
            return songDB.getAllSongs();
        }


}
