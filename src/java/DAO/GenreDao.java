package DAO;

import Business.Genre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * Class GenreDao contains method to display all genres. And findGenreById
 * method to find genre by genre id. And findGenreByGenreName method to find
 * genre by genre name. Class GenreDao implements GenreDaoInterface interface.
 */
public class GenreDao extends Dao implements GenreDaoInterface {

    /**
     * Add in constructor that takes in the source of database connections and
     * pass it up to the super class for storage
     *
     * @param myDataSource
     */
    public GenreDao(DataSource myDataSource) {
        super(myDataSource);
    }

    /**
     * Add a blank constructor to allow for default usage
     */
    public GenreDao() {
        super();
    }

    /**
     * This method to display all genres from genres table.
     *
     * @return all genres from genres table.
     */
    @Override
    public ArrayList<Genre> displayAllGenres() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Genre> genres = new ArrayList();
        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = getConnection();

            String query = "Select * from genre";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                /*
                    Make a genre object for the current genre;
                    Fill genre object with details for the current genre;
                 */
                Genre g = new Genre(rs.getInt("genre_id"), rs.getString("name"));

                // Store the current genre object (now filled with information) in the arraylist
                genres.add(g);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the displayAllGenres() method");
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
                System.out.println("Exception occured in the finally section of the displayAllGenres() method");
                System.out.println(e.getMessage());
            }
        }
        return genres;
    }

    /**
     * This method to find and return genre by genre id form genre table.
     *
     * @param genreId genre id to find genre with presented id.
     * @return genre if genre with presented genre id exists in database.
     */
    @Override
    public Genre findGenreById(int genreId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Genre g = null;
        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM genre WHERE  genre_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, genreId);

            rs = ps.executeQuery();
            if (rs.next()) {

                // Make a genre object for the current genre
                g = new Genre();

                // Fill genre object with details for the current genre
                g.setGenreId(rs.getInt("genre_id"));
                g.setGenreName(rs.getString("name"));

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the findGenreById() method ");
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
                System.out.println("Exception occured in the finally section of the findGenreById() method ");
                System.out.println(e.getMessage());
            }
        }
        return g;
    }

    /**
     * This method to find and return genre by genre name from genre table.
     *
     * @param genreName genre name to find genre name.
     * @return genre name.
     */
    @Override
    public Genre findGenreByGenreName(String genreName) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Genre g = null;
        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM genre WHERE  name = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, genreName);

            rs = ps.executeQuery();
            if (rs.next()) {

                // Make a genre object for the current genre
                g = new Genre();

                // Fill genre object with details for the current genre
                g.setGenreId(rs.getInt("genre_id"));
                g.setGenreName(rs.getString("name"));

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the findGenreByGenreName() method ");
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
                System.out.println("Exception occured in the finally section of the findGenreByGenreName() method ");
                System.out.println(e.getMessage());
            }
        }
        return g;
    }

}
