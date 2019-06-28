package Business;

import java.util.Comparator;

public class MovieRatingComparator implements Comparator<Movie> {

    /**
     * This method compares movies by user rating for movie been left if ratings
     * of movies are equal, then method compares by number of reviews was left
     *
     * @param mv1 First Movie
     * @param mv2 Second Movie
     * @return return a negative integer, zero, or a positive integer, when
     * first movie is less than, equal, or greater than second movie.
     */
    @Override
    public int compare(Movie mv1, Movie mv2) {
        double ratingAvg1 = mv1.getRatingAvg();
        double ratingAvg2 = mv2.getRatingAvg();
        int popularity1 = mv1.getRatingNum();
        int popularity2 = mv2.getRatingNum();

        if (ratingAvg1 < ratingAvg2) {
            return 1;
        } else if (ratingAvg1 > ratingAvg2) {
            return -1;
        } else if (popularity1 < popularity2) {
            return 1;
        } else if (popularity1 > popularity2) {
            return -1;
        } else {
            return 0;
        }
    }
}
