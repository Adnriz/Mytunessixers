package BE;

public class PlaylistSongs {
    private int songID;
    private int time;
    private int playlistID;
    private String fPath;
    private String formatedTime;
    private String title;
    private Artist artist;
    private Genre type;
    private Song song;

    public Song getSong() {
        return this.song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getfPath() {
        return this.fPath;
    }

    public void setfPath(String fPath) {
        this.fPath = fPath;
    }

    public void setFormatedTime(String formatedTime) {
        this.formatedTime = formatedTime;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getType() {
        return this.type;
    }

    public void setType(Genre type) {
        this.type = type;
    }

    public PlaylistSongs(int songID, int playlistID, String title, int time, Artist artist, Genre type, String formatedTime, String fPath) {
        this.playlistID = playlistID;
        this.songID = songID;
        this.time = time;
        this.formatedTime = this.getFormatedTime();
        this.title = title;
        this.artist = artist;
        this.type = type;
        this.fPath = fPath;
    }

    public PlaylistSongs() {
    }

    public PlaylistSongs(int playlistID, int songID) {
        this.playlistID = playlistID;
        this.songID = songID;
    }

    public int getPlaylistID() {
        return this.playlistID;
    }

    public void setPlaylistID(int id) {
        this.playlistID = id;
    }

    public int getSongID() {
        return this.songID;
    }

    public void setSongID(int id) {
        this.songID = id;
    }

    public String getFormatedTime() {
        return this.song != null ? this.song.getConvertedTime() : "no time to display";
    }
}
