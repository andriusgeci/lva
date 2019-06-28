package DAO;

import Business.Movie;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * Class MovieDao created to work with the movies and contain following methods:
 * displayAllMovies to display all movies existing in movies table in the
 * database, findMovieByTitle to find exact movie by movie title,
 * searchSimilarMoviesByTitle to search for all similar movies by entered title,
 * addMovie to add new(not already existing) movie into movie table in database,
 * deleteMovie to delete movie from movie table in database,
 * findMoviesByPriceBetweenMinAndMaxPrice to search for a movies by price in
 * price range between max and min price, findMoviePriceMin to find least
 * expensive movie price, findMoviePriceMax to find most expensive movie price,
 * displayMoviesByDate to find all movies released on particular year. Class
 * MovieDao implements interface of name MovieDaoInterface.
 */
public class MovieDao extends Dao implements MovieDaoInterface {

    /**
     * Add in constructor that takes in the source of database connections and
     * pass it up to the super class for storage
     *
     * @param myDataSource
     */
    public MovieDao(DataSource myDataSource) {
        super(myDataSource);
    }

    /**
     * Add a blank constructor to allow for default usage
     */
    public MovieDao() {
        super();
    }

    /**
     * This method displayAllMovies to display all movies existing in movies
     * table in the database.
     *
     * @return an ArrayList of movies existing in movies table in the database.
     */
    @Override
    public ArrayList<Movie> displayAllMovies() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList();

        try {
            con = getConnection();

            String query = "Select * from movies";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                // Make a movie object for the current movie
                Movie mv = new Movie();

                // Fill movie object with details for the current movie
                mv.setMovieId(rs.getInt("movie_id"));
                mv.setTitle(rs.getString("title"));
                mv.setDescription(rs.getString("description"));
                mv.setReleaseYear(rs.getInt("release_Year"));
                mv.setMovieLegth(rs.getInt("movie_length"));
                mv.setAgeRating(rs.getString("age_rating"));
                mv.setImgUrl(rs.getString("img_url"));
                mv.setPrice(rs.getDouble("price"));
                mv.setQuantityInStock(rs.getInt("quantity_in_stock"));
                mv.setRatingAvg(rs.getDouble("avg_rating"));
                mv.setRatingNum(rs.getInt("num_ratings"));
                mv.setRatingTotal(rs.getLong("rating_total"));
                // Store the current movie object (now filled with information) in the arraylist
                movies.add(mv);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the displayAllMovies() method");
            System.out.println(e.getMessage());
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
                System.out.println("Exception occured in the finally section of the displayAllMovies() method");
                System.out.println(e.getMessage());
            }
        }
        return movies;
    }

    /**
     * This method to search for particular movie by particular title. Method
     * takes in movie title. If movie not exist method will return null.
     *
     * @param movieTitle movie title to search for the movie.
     * @return particular movie details if movie with particular title exist.
     */
    @Override
    public Movie findMovieByTitle(String movieTitle) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie mv = null;

        try {
            con = getConnection();

            String query = "Select * from movies where title = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, movieTitle);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Make a movie object for the current movie
                mv = new Movie();

                mv.setMovieId(rs.getInt("movie_id"));
                mv.setTitle(rs.getString("title"));
                mv.setDescription(rs.getString("description"));
                mv.setReleaseYear(rs.getInt("release_Year"));
                mv.setAgeRating(rs.getString("age_rating"));
                mv.setMovieLegth(rs.getInt("movie_length"));
                mv.setImgUrl(rs.getString("img_url"));
                mv.setPrice(rs.getDouble("price"));
                mv.setQuantityInStock(rs.getInt("quantity_in_stock"));
                mv.setRatingAvg(rs.getDouble("avg_rating"));
                mv.setRatingNum(rs.getInt("num_ratings"));
                mv.setRatingTotal(rs.getLong("rating_total"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the findMovieByTitle() method ");
            System.out.println(e.getMessage());
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
                System.out.println("Exception occured in the finally section of the findMovieByTitle() method ");
                System.out.println(e.getMessage());
            }
        }
        return mv;
    }

    /**
     * This method searchSimilarMoviesByTitle to search for similar movie by
     * title. Method takes in movie title. Method will return all movies, which
     * title contain a part of entered title.
     *
     * @param movieTitle movie title to find similar movies.
     * @return an ArrayList of movies if movies with similar title exists in the
     * database.
     */
    @Override
    public ArrayList<Movie> searchSimilarMoviesByTitle(String movieTitle) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList();

        try {
            con = getConnection();

            String query = "Select * from movies where title like ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + movieTitle + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                // Make a movie object for the current movie
                Movie mv = new Movie();

                // Fill movie object with details for the current movie
                mv.setMovieId(rs.getInt("movieId"));
                mv.setTitle(rs.getString("title"));
                mv.setDescription(rs.getString("description"));
                mv.setReleaseYear(rs.getInt("releaseYear"));
                mv.setMovieLegth(rs.getInt("movieLength"));
                mv.setAgeRating(rs.getString("ageRating"));
                mv.setImgUrl(rs.getString("imgUrl"));
                mv.setPrice(rs.getDouble("price"));
                mv.setQuantityInStock(rs.getInt("quantityInStock"));
                mv.setRatingAvg(rs.getDouble("avg_rating"));
                mv.setRatingNum(rs.getInt("num_ratings"));
                mv.setRatingTotal(rs.getLong("rating_total"));
                // Store the current movie object (now filled with information) in the arraylist
                movies.add(mv);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the searchSimilarMoviesByTitle() method ");
            System.out.println(e.getMessage());
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
                System.out.println("Exception occured in the finally section of the searchSimilarMoviesByTitle() method ");
                System.out.println(e.getMessage());
            }
        }
        return movies;
    }

    // addMovie method takes in all movie details and stores them to the database
    /**
     * This method addMovie to add movie into movie table in the database.
     * Method takes in all movie details.
     *
     * @param mv The movie to be added to the database.
     * @return true if movie detail was added successfully, false otherwise.
     * @throws DaoException if message: "Exception occurred in the ROLLBACK"
     * seen, error occurred by adding movie stage. If message: "Exception
     * occurred in the finally section of the addMovie() method " seen, database
     * is not accessible.
     */
    public boolean addMovie(Movie mv) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        if (mv != null) {
            if (findMovieByTitle(mv.getTitle()) == null) {
                try {
                    con = this.getConnection();
                    con.setAutoCommit(false);
                    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

                    String query = "INSERT INTO movies(title, description, release_Year,  age_Rating, movie_Length, img_Url, price, quantity_In_Stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    ps = con.prepareStatement(query);
                    ps.setString(1, mv.getTitle());
                    ps.setString(2, mv.getDescription());
                    ps.setInt(3, mv.getReleaseYear());
                    ps.setString(4, mv.getAgeRating());
                    ps.setInt(5, mv.getMovieLegth());
                    ps.setString(6, mv.getImgUrl());
                    ps.setDouble(7, mv.getPrice());
                    ps.setInt(8, mv.getQuantityInStock());

                    ps.execute();
                    con.commit();
                } catch (SQLException ex) {
                    try {
                        //start rollbacck
                        con.rollback();
                    } catch (SQLException ex1) {
                        System.out.println("Exception occured in the ROLLBACK " + ex1.getMessage());
                    }
                    throw new DaoException("transaction(): " + ex.getMessage());
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                        if (con != null) {
                            freeConnection(con);
                        }
                    } catch (SQLException e) {
                        System.out.println("Exception occurred in the finally section of the addMovie() method ");
                        System.out.println(e.getMessage());
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Deletes given movie from the database.
     *
     * @param mv The movie to be deleted.
     * @return true if the movie information was deleted successfully, false
     * otherwise.
     * @throws DaoException if message: "Exception occurred in the deleteMovie()
     * method" seen, error occurred by deleting movie information stage. If
     * message: "Exception occurred in the finally section of the deleteMovie()
     * method " seen, database is not accessible.
     */
    public boolean deleteMovie(Movie mv) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        if (mv != null) {

            try {
                con = this.getConnection();
                String query = "Delete from movies where movie_id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, mv.getMovieId());

                ps.execute();
            } catch (SQLException e) {
                System.out.println("Exception occurred in the deleteMovie() method ");
                System.out.println(e.getMessage());

            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    System.out.println("Exception occurred in the finally section of the deleteMovie() method ");
                    System.out.println(e.getMessage());
                }
            }
            return true;

        } else {
            return false;
        }
    }

    /**
     * This method finds all movies by price, between min and max prices.
     *
     * @param minPrice The minimum price for search.
     * @param maxPrice The maximum price for search.
     * @return an ArrayList of movies in range between min and max prices
     * entered by the user.
     */
    public ArrayList<Movie> findMoviesByPriceBetweenMinAndMaxPrice(int minPrice, int maxPrice) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList();

        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = getConnection();

            String query = "SELECT * from movies WHERE price BETWEEN ? AND ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, minPrice);
            ps.setInt(2, maxPrice);
            rs = ps.executeQuery();

            while (rs.next()) {
                //  declare and iniciate variables for movie object

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
                long movieRatingTotal = rs.getLong("rating_total");

                //create current movie object and fill with details for the current movie
                Movie m = new Movie(movieId, movieTitle, movieDescription, movieReleaseYear, movieAgeRating, movieLength, movieImgUrl, moviePrice, movieQuantityInStock, movieRatingAvg, movieRatingNum, movieRatingTotal);
                // Store the current movie object (now filled with information) in the arraylist
                movies.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the findMoviesByPriceBetweenMinAndMaxPrice() method ");
            System.out.println(e.getMessage());
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
                System.out.println("Exception occurred in the finally section of the findMoviesByPriceBetweenMinAndMaxPrice() method ");
                System.out.println(e.getMessage());
            }
        }
        return movies;
    }

    /**
     * This method will find minimum price of the movie in the database.
     *
     * @return an integer of minimum price value rounded to a floor.
     */
    @Override
    public double findMoviePriceMin() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double p = 0;
        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT MIN(price) FROM movies";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            if (rs.next()) {

                p = rs.getDouble("MIN(price)");
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the findMoviePriceMin() method ");
            System.out.println(e.getMessage());

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
                System.out.println("Exception occurred in the finally section of the findMoviePriceMin() method ");
                System.out.println(e.getMessage());
            }
        }
        return Math.floor(p);
    }

    /**
     * This method will find maximum price of the movie in the database.
     *
     * @return an integer of maximum price value rounded to a ceil.
     */
    @Override
    public double findMoviePriceMax() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double p = 0;
        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT MAX(price) FROM movies";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            if (rs.next()) {

                p = rs.getDouble("MAX(price)");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the findMoviePriceMax() method ");
            System.out.println(e.getMessage());

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
                System.out.println("Exception occured in the finally section of the findMoviePriceMax() method ");
                System.out.println(e.getMessage());
            }
        }
        return Math.ceil(p);
    }

    /*
        This method will update movie in the shop 
        Method returns boolean to show movie object updated or not
     */
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
    @Override
    public boolean updateMovie(Movie mv) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean updated = false;

        try {
            con = getConnection();

            String query = "UPDATE movies SET title = ?, description = ?, release_Year = ?, age_rating = ?, movie_length = ?, img_url = ?,price = ?, quantity_in_stock = ?, avg_rating = ?, num_ratings = ?, rating_total = ? WHERE movie_id = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, mv.getTitle());
            ps.setString(2, mv.getDescription());
            ps.setInt(3, mv.getReleaseYear());
            ps.setString(4, mv.getAgeRating());
            ps.setInt(5, mv.getMovieLegth());
            ps.setString(6, mv.getImgUrl());
            ps.setDouble(7, mv.getPrice());
            ps.setInt(8, mv.getQuantityInStock());
            ps.setDouble(9, mv.getRatingAvg());
            ps.setInt(10, mv.getRatingNum());
            ps.setLong(11, mv.getRatingTotal());
            ps.setInt(12, mv.getMovieId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                updated = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the updateMovie() method");
            throw new DaoException(e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the finally section of the updateMovie() method");
                throw new DaoException(e.getMessage());
            }
        }
        return updated;
    }

    /**
     * Displays all movies of particular year from database.
     *
     * @param release_year The year to search for a movie/movies.
     * @return an ArrayList of movies by the entered year.
     */
    public ArrayList<Movie> displayMoviesByDate(int release_year) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList();

        try {
            con = getConnection();

            String query = "Select * from movies where release_Year =?";
            ps = con.prepareStatement(query);
            ps.setInt(1, release_year);
            rs = ps.executeQuery();

            while (rs.next()) {
                // Make a movie object for the current movie
                Movie mv = new Movie();

                // Fill movie object with details for the current movie
                mv.setMovieId(rs.getInt("movie_id"));
                mv.setTitle(rs.getString("title"));
                mv.setDescription(rs.getString("description"));
                mv.setReleaseYear(rs.getInt("release_Year"));
                mv.setMovieLegth(rs.getInt("movie_length"));
                mv.setAgeRating(rs.getString("age_rating"));
                mv.setImgUrl(rs.getString("img_url"));
                mv.setPrice(rs.getDouble("price"));
                mv.setQuantityInStock(rs.getInt("quantity_in_stock"));
                mv.setRatingAvg(rs.getDouble("avg_rating"));
                mv.setRatingNum(rs.getInt("num_ratings"));
                mv.setRatingTotal(rs.getLong("rating_total"));
                // Store the current movie object (now filled with information) in the arraylist
                movies.add(mv);
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the displayAllMovies() method");
            System.out.println(e.getMessage());
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
                System.out.println("Exception occurred in the finally section of the displayAllMovies() method");
                System.out.println(e.getMessage());
            }
        }
        return movies;
    }

    /**
     *
     * @param movieId new movie id
     *
     * @return returns movie object by id
     */
    public Movie findMovieById(int movieId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie mv = null;

        try {
            con = getConnection();

            String query = "Select * from movies where movie_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, movieId);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Make a movie object for the current movie
                mv = new Movie();

                mv.setMovieId(rs.getInt("movie_id"));
                mv.setTitle(rs.getString("title"));
                mv.setDescription(rs.getString("description"));
                mv.setReleaseYear(rs.getInt("release_Year"));
                mv.setAgeRating(rs.getString("age_rating"));
                mv.setMovieLegth(rs.getInt("movie_length"));
                mv.setImgUrl(rs.getString("img_url"));
                mv.setPrice(rs.getDouble("price"));
                mv.setQuantityInStock(rs.getInt("quantity_in_stock"));
                mv.setRatingAvg(rs.getDouble("avg_rating"));
                mv.setRatingNum(rs.getInt("num_ratings"));
                mv.setRatingTotal(rs.getLong("rating_total"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the findMovieByTitle() method ");
            System.out.println(e.getMessage());
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
                System.out.println("Exception occured in the finally section of the findMovieByTitle() method ");
                System.out.println(e.getMessage());
            }
        }
        return mv;
    }
}
