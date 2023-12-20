package BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Song {
    private StringProperty songName;
    private StringProperty artistName;
    private StringProperty genre;
    private int id;

    private String filePath;

    public Song(String songName, String artistName, String genre, int id, String filePath) {
        this.songName = new SimpleStringProperty(songName);
        this.artistName = new SimpleStringProperty(artistName);
        this.genre = new SimpleStringProperty(genre);
        this.id = id;
        this.filePath = filePath;
    }


    public String getFilePath()
    {
        return this.filePath;
    }
    public String getSongName() {
        return songName.get();
    }

    public void setSongName(String songName) {
        this.songName.set(songName);
    }

    public String getArtistName() {
        return artistName.get();
    }

    public void setArtistName(String artistName) {
        this.artistName.set(artistName);
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return songName.get() + " by " + artistName.get();
    }

    public String getSongLocation() {
        return null;
    }
}