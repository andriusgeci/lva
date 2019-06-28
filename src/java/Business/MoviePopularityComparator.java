package Business;

import java.util.Comparator;


public class MoviePopularityComparator implements Comparator<Movie> {

    /**
     * This method compares movies by number of reviews for movie been left if
     * number of reviews left are equal, then method compares by rating of movie
     *
     * @param mv1 first movie
     * @param mv2 second movie
     * @return return a negative integer, zero, or a positive integer, when
     * first movie is less than, equal, or greater than second movie.
     */
    @Override
    public int compare(Movie mv1, Movie mv2) {
        int popularity1 = mv1.getRatingNum();
        int popularity2 = mv2.getRatingNum();
        double ratingAvg1 = mv1.getRatingAvg();
        double ratingAvg2 = mv2.getRatingAvg();
        if (popularity1 < popularity2) {
            return 1;
        } else if (popularity1 > popularity2) {
            return -1;
        } else if (ratingAvg1 < ratingAvg2) {
            return 1;
        } else if (ratingAvg1 > ratingAvg2) {
            return -1;
        } else {
            return 0;
        }
    }

}
