package BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Song {
    private int id;
    private int time;
    private String fPath;
    private String formatedTime;
    private String title;
    private Artist artist;
    private Genre type;

    public Song(int id, String title, int time, Artist artist, Genre type, String formatedTime, String fPath) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.formatedTime = this.getConvertedTime();
        this.artist = artist;
        this.type = type;
        this.fPath = fPath;
    }

    public Song(String name, String artist, String genre) {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return this.time;
    }

    public String getConvertedTime() {
        int hours = this.time / 3600;
        int minutes = this.time % 3600 / 60;
        int seconds = this.time % 60;
        this.formatedTime = hours > 0 ? String.format("%d:%02d:%02d", hours, minutes, seconds) : String.format("%d:%02d", minutes, seconds);
        return this.formatedTime;
    }

    public String getFormatedTime() {
        return this.formatedTime;
    }

    public void setTime(int time) {
        this.time = time;
        this.formatedTime = this.getConvertedTime();
    }


    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return this.type;
    }

    public void setGenre(Genre type) {
        this.type = type;
    }

    public String getFPath() {
        return this.fPath;
    }

    public void setfPath(String fPath) {
        this.fPath = fPath;
    }

    public String getTimeStamp() {
        int minutes = this.time / 60;
        int seconds = this.time % 60;
        String textSeconds;
        if (seconds <= 9) {
            textSeconds = "0" + seconds;
        } else {
            textSeconds = "" + seconds;
        }

        return "" + minutes + ":" + textSeconds;
    }

    public String toString() {
        return this.title;
    }
}