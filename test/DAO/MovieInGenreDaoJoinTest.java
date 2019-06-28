/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Genre;
import Business.Movie;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class MovieInGenreDaoJoinTest {
    
    MovieInGenreDaoJoin movGen = new MovieInGenreDaoJoin(new MyDataSource());
    
    /**
     * Test of findGenresByMovieId method, of class MovieInGenreDaoJoin.
     */
    @Test
    public void testFindGenresByMovieId() {
        System.out.println("findGenresByMovieId");
        int movieId = 1;
        ArrayList<Genre> expResult = movGen.findGenresByMovieId(movieId);
        ArrayList<Genre> result = movGen.findGenresByMovieId(movieId);
        assertEquals(expResult, result);
    }

    /**
     * Test of findMoviesByGenreId method, of class MovieInGenreDaoJoin.
     */
    @Test
    public void testFindMoviesByGenreId() {
        System.out.println("findMoviesByGenreId");
        int genreId = 1;
        ArrayList<Movie> expResult = movGen.findMoviesByGenreId(genreId);
        ArrayList<Movie> result = movGen.findMoviesByGenreId(genreId);
        assertEquals(expResult, result);
    }
    
}
