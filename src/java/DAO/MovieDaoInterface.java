package DAO;

import Business.Movie;
import Exceptions.DaoException;
import java.util.ArrayList;

public interface MovieDaoInterface {

    /**
     * This method displayAllMovies to display all movies existing in movies
     * table in the database.
     *
     * @return an ArrayList of movies existing in movies table in the database.
     */
    public ArrayList<Movie> displayAllMovies();

    /**
     * This method to search for particular movie by particular title. Method
     * takes in movie title. If movie not exist method will return null.
     *
     * @param movieTitle movie title to search for the movie.
     * @return particular movie details if movie with particular title exist.
     */
    public Movie findMovieByTitle(String movieTitle);

    /**
     * This method searchSimilarMoviesByTitle to search for similar movie by
     * title. Method takes in movie title. Method will return all movies, which
     * title contain a part of entered title.
     *
     * @param movieTitle movie title to find similar movies.
     * @return an ArrayList of movies if movies with similar title exists in the
     * database.
     */
    public ArrayList<Movie> searchSimilarMoviesByTitle(String movieTitle);

    /**
     * This method will find minimum price of the movie in the database.
     *
     * @return an integer of minimum price value rounded to a floor.
     */
    public double findMoviePriceMin();

    /**
     * This method will find maximum price of the movie in the database.
     *
     * @return an integer of maximum price value rounded to a ceil.
     */
    public double findMoviePriceMax();

    /**
     * This method will update movie in the database.
     *
     * @param mv The movie to be updated in the database.
     * @return true if the movie information was updated successfully, false
     * otherwise.
     * @throws DaoException if message: "Exception occurred in the updateMovie()
     * method" seen, error occurred by updating movie information stage. If
     * message: "Exception occurred in the finally section of the updateMovie()
     * method" seen, database is not accessible.
     */
    public boolean updateMovie(Movie mv) throws DaoException;

    /**
     * Displays all movies of particular year from database.
     *
     * @param release_year The year to search for a movie/movies.
     * @return an ArrayList of movies by the entered year.
     */
    public ArrayList<Movie> displayMoviesByDate(int release_year);

}
