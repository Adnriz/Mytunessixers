package BE;

public class Genre {

        private String type;
        private int id;
        private int artistID;

        public Genre(String type, int id, int artistID) {
            this.type = type;
            this.id = id;
            this.artistID = artistID;
        }

        public String getType() {
            return this.type;
        }

        public String toString() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getArtistID() {
            return this.artistID;
        }

        public void setArtistID(int artistID) {
            this.artistID = artistID;
        }

        public String toLowerCase() {
            return this.toString();
        }
}
