package BE;

public class Playlist {
    private String playlistName;
    private int id;
    private int numberOfSongs;
    private String durationOfplaylist;

    public Playlist(String playlistName, int id) {
        this.playlistName = playlistName;
        this.id = id;
    }
    public String getPlaylistName() {
        return playlistName;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public String getDurationOfplaylist() {
        return durationOfplaylist;
    }
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public void setDurationOfplaylist(String durationOfplaylist) {
        this.durationOfplaylist = durationOfplaylist;
    }

    public int getId(){
        return id;
    }







}
