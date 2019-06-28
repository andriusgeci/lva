package DAO;

import Business.Genre;
import Business.Movie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * Class MovieInGenreDaoJoin created to work with movie_in_genre table and
 * contain following methods: findGenresByMovieId method join two tables:
 * movie_in_genre and genre, and searches for movie genres by movie id.
 * findMoviesByGenreIdv method join two tables: movie_in_genre & movies, and
 * searches for the movie by genre id; This class methods is used in shop
 * searches. Class MovieInGenreDaoJoin implements interface of name
 * MovieInGenreDaoJoinInterface.
 */
public class MovieInGenreDaoJoin extends Dao implements MovieInGenreDaoJoinInterface {

    /**
     * Add in constructor that takes in the source of database connections and
     * pass it up to the super class for storage
     *
     * @param myDataSource
     */
    public MovieInGenreDaoJoin(DataSource myDataSource) {
        super(myDataSource);
    }

    /**
     * Add a blank constructor to allow for default usage
     */
    public MovieInGenreDaoJoin() {
        super();
    }

    /**
     * This method join two tables: movie_in_genre & genre and searches for
     * genre by movie id.
     *
     * @param movieId The movie id to search for genre.
     * @return an ArrayList of genres by movie id.
     */
    @Override
    public ArrayList<Genre> findGenresByMovieId(int movieId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Genre> genres = new ArrayList<Genre>();

        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = getConnection();

            String query = "Select * from movie_in_genre, genre WHERE movie_in_genre.movie_id = ? AND genre.genre_id = movie_in_genre.genre_id";
            ps = con.prepareStatement(query);
            ps.setInt(1, movieId);
            rs = ps.executeQuery();

            while (rs.next()) {
                // Make a genre object by movie_id for the current genre and fill with details for the current genre
                Genre g = new Genre(rs.getInt("genre_id"), rs.getString("name"));
                // Store the current genre object (now filled with information) in the arraylist
                genres.add(g);
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the findGenresByMovieId() method");
            e.getMessage();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the finally section of the findGenresByMovieId() method");
                e.getMessage();
            }
        }

        return genres;
    }

    /**
     * This method join two tables: movie_in_genre & movies and searches for the
     * movie by the genre_id.
     *
     * @param genreId The genre id to search for the movie.
     * @return an ArrayList of movies by genre id.
     */
    @Override
    public ArrayList<Movie> findMoviesByGenreId(int genreId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList<Movie>();

        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = getConnection();

            String query = "Select * from movies, movie_in_genre WHERE movie_in_genre.genre_id = ? AND movies.movie_id = movie_in_genre.movie_id";
            ps = con.prepareStatement(query);
            ps.setInt(1, genreId);
            rs = ps.executeQuery();

            while (rs.next()) {
                //  declare and iniciate variables for movie object
                //object by movie_id for the current genre and fill with details for the current genre
                int movieId = rs.getInt("movie_id");
                String movieTitle = rs.getString("title");
                String movieDescription = rs.getString("description");
                int movieReleaseYear = rs.getInt("release_Year");
                String movieAgeRating = rs.getString("age_rating");
                int movieLength = rs.getInt("movie_length");
                String movieImgUrl = rs.getString("img_url");
                double moviePrice = rs.getDouble("price");
                int movieQuantityInStock = rs.getInt("quantity_in_stock");
                double movieRatingAvg = rs.getDouble("avg_rating");
                int movieRatingNum = rs.getInt("num_ratings");
                int movieRatingTotal = rs.getInt("rating_total");

                //create current movie object and fill with details for the current movie
                Movie m = new Movie(movieId, movieTitle, movieDescription, movieReleaseYear, movieAgeRating, movieLength, movieImgUrl, moviePrice, movieQuantityInStock, movieRatingAvg, movieRatingNum, movieRatingTotal);
                // Store the current movie object (now filled with information) in the arraylist
                movies.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the findMoviesByGenreId() method");
            e.getMessage();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the findMoviesByGenreId() method");
                e.getMessage();
            }
        }
        return movies;
    }
}
