/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import org.junit.Test;
import static org.junit.Assert.*;


public class MovieInGenreTest {
    MovieInGenre mov = new MovieInGenre(1, 1);
    
    /**
     * Test of getMovieId method, of class MovieInGenre.
     */
    @Test
    public void testGetMovieId() {
        System.out.println("getMovieId");
        int expResult = 1;
        int result = mov.getMovieId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGenreId method, of class MovieInGenre.
     */
    @Test
    public void testGetGenreId() {
        System.out.println("getGenreId");
        int expResult = 1;
        int result = mov.getGenreId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMovieId method, of class MovieInGenre.
     */
    @Test
    public void testSetMovieId() {
        System.out.println("setMovieId");
        mov.setMovieId(2);
        assertEquals(2, mov.getMovieId());
    }

    /**
     * Test of setGenreId method, of class MovieInGenre.
     */
    @Test
    public void testSetGenreId() {
        System.out.println("setGenreId");
        mov.setGenreId(2);
        assertEquals(2, mov.getGenreId());
    }

    /**
     * Test of hashCode method, of class MovieInGenre.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = 10503;
        int result = mov.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class MovieInGenre.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        boolean expResult = false;
        boolean result = mov.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class MovieInGenre.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String result = mov.toString();
        assertFalse(result.isEmpty()); 
    }
    
}
