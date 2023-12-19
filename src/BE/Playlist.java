package BE;

public class Playlist {
    private int id;
    private String name;

    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Playlist() {
    }

    public Playlist(String name) {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaylistID(int newPlaylistId) {
    }
}
