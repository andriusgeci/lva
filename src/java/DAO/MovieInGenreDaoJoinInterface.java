package DAO;

import Business.Genre;
import Business.Movie;
import Exceptions.DaoException;
import java.util.ArrayList;

public interface MovieInGenreDaoJoinInterface {

    /**
     * This method join two tables: movie_in_genre & genre and searches for
     * genre by movie id.
     *
     * @param movieId The movie id to search for genre.
     * @return an ArrayList of genres by movie id.
     */
    public ArrayList<Genre> findGenresByMovieId(int movieId);

    /**
     * This method join two tables: movie_in_genre & movies and searches for the
     * movie by the genre_id.
     *
     * @param genreId The genre id to search for the movie.
     * @return an ArrayList of movies by genre id.
     */
    public ArrayList<Movie> findMoviesByGenreId(int genreId);
}
