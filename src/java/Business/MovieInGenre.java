package Business;

public class MovieInGenre {

    private int movieId;
    private int genreId;

    /**
     * Default Constructor
     */
    public MovieInGenre() {
    }

    /**
     * Parameterized constructor
     *
     * @param movieId New Movie ID
     * @param genreId New Movie Genre
     */
    public MovieInGenre(int movieId, int genreId) {
        this.movieId = movieId;
        this.genreId = genreId;
    }

    /**
     * Getter Method
     *
     * @return Movie ID
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * Getter Method
     *
     * @return Genre ID
     */
    public int getGenreId() {
        return genreId;
    }

    /**
     * Setter Method
     *
     * @param movieId Movie ID
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    /**
     * Setter Method
     *
     * @param genreId Genre ID
     */
    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    /**
     * Calculate the hashcode of this object, based only on its movieId and
     * genreId
     *
     * @return the hashcode for the MovieInGenre object
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.movieId;
        hash = 59 * hash + this.genreId;
        return hash;
    }

    /**
     *
     * @param obj other Object, usually a MovieInGenre, to compare with
     * @return true if the objects are identical
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
        final MovieInGenre other = (MovieInGenre) obj;
        if (this.movieId != other.movieId) {
            return false;
        }
        if (this.genreId != other.genreId) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return a string representation of Movie In Genre object
     */
    @Override
    public String toString() {
        return "MovieInGenre{" + "movieId=" + movieId + ", genreId=" + genreId + '}';
    }

}
