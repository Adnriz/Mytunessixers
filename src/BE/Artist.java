package BE;

import java.util.Collection;

public class Artist {
    private String name;
    private int id;

    /**
     *
     * @param name = name from database
     * @param id = id from database
     */
    public Artist(String name, int id){
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Collection<Object> toLowerCase() {
        return null;
    }
}
