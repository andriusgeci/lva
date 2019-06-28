package Business;

import java.util.Objects;

public class Genre {

    private int genreId;
    private String genreName;

    /**
     * default constructor
     */
    public Genre() {
    }

    /**
     * parameterised constructor
     *
     * @param genreId new takes genre ID
     * @param genreName new takes genre name
     */
    public Genre(int genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    /**
     * getter methods
     *
     * @return genreId
     */
    public int getGenreId() {
        return genreId;
    }

    /**
     * getter methods
     *
     * @return genreName
     */
    public String getGenreName() {
        return genreName;
    }

    /**
     * setter methods
     *
     * @param genreId new genre ID
     */
    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    /**
     * setter methods
     *
     * @param genreName
     */
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    /**
     * Calculate the hashcode of this object, based only on its genreID and
     * genreName
     *
     * @return the hashcode for the Genre object
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.genreId;
        hash = 67 * hash + Objects.hashCode(this.genreName);
        return hash;
    }

    /**
     *
     * @param obj other Object, usually a Genre, to compare with.
     * @return true if the objects are of identical
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Genre other = (Genre) obj;
        if (this.genreId != other.genreId) {
            return false;
        }
        if (!Objects.equals(this.genreName, other.genreName)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return a string representation of Genre object
     */
    @Override
    public String toString() {
        return "Genre{" + "genreId=" + genreId + ", genreName=" + genreName + '}';
    }

}
