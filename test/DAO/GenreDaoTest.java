/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Genre;
import org.junit.Test;
import static org.junit.Assert.*;

public class GenreDaoTest {
    
  GenreDao gen = new GenreDao(new MyDataSource());
    
    /**
     * Test of displayAllGenres method, of class GenreDao.
     */
    @Test
    public void testDisplayAllGenres() {
        System.out.println("displayAllGenres");
        String expResult = "Action";
        String result = gen.displayAllGenres().get(0).getGenreName();
        assertEquals(expResult, result);
    }

    /**
     * Test of findGenreById method, of class GenreDao.
     */
    @Test
    public void testFindGenreById() {
        System.out.println("findGenreById");
        int genreId = 1;
        Genre expResult = new Genre(1,"Action");
        Genre result = gen.findGenreById(genreId);
        assertEquals(expResult, result);    
    }

    /**
     * Test of findGenreByGenreName method, of class GenreDao.
     */
    @Test
    public void testFindGenreByGenreName() {
        System.out.println("findGenreByGenreName");
        String genreName = "Action";
        Genre expResult = new Genre(1, "Action");
        Genre result = gen.findGenreByGenreName(genreName);
        assertEquals(expResult, result);
    }
    
}
