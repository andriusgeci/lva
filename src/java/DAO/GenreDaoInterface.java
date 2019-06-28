package DAO;

import Business.Genre;
import Exceptions.DaoException;
import java.util.ArrayList;

public interface GenreDaoInterface {

    /**
     * This method to display all genres from genres table.
     *
     * @return all genres from genres table in database.
     */
    public ArrayList<Genre> displayAllGenres();

    /**
     * This method to find and return genre by genre id form genre table.
     *
     * @param genreId genre id to find genre with presented id.
     * @return genre if genre with presented genre id exists in database.
     */
    public Genre findGenreById(int genreId);

    /**
     * This method to find and return genre by genre name from genre table.
     *
     * @param genreName genre name to find genre name.
     * @return genre name.
     */
    public Genre findGenreByGenreName(String genreName);
}
