package Business;

import java.util.Comparator;

/*
    This method compares movies by title in descending order
 */
public class MovieTitleDscComparator implements Comparator<Movie> {

    /**
     * This method compares movies by title in descending order
     *
     * @param mv1 First Movie
     * @param mv2 Second Movie
     * @return return a negative integer, zero, or a positive integer, when
     * first movie is less than, equal, or greater than second movie.
     */
    @Override
    public int compare(Movie mv1, Movie mv2) {
        return (mv1.getTitle().compareTo(mv2.getTitle()) * (-1));
    }
}
