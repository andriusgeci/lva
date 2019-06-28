/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import org.junit.Test;
import static org.junit.Assert.*;

public class GenreTest {
    Genre gen = new Genre(1, "genreName");
    
    /**
     * Test of getGenreId method, of class Genre.
     */
    @Test
    public void testGetGenreId() {
        System.out.println("getGenreId");
        int expResult = 1;
        int result = gen.getGenreId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGenreName method, of class Genre.
     */
    @Test
    public void testGetGenreName() {
        System.out.println("getGenreName");
        String expResult = "genreName";
        String result = gen.getGenreName();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setGenreId method, of class Genre.
     */
    @Test
    public void testSetGenreId() {
        System.out.println("setGenreId");
        gen.setGenreId(2);
        assertEquals(2, gen.getGenreId());
    }

    /**
     * Test of setGenreName method, of class Genre.
     */
    @Test
    public void testSetGenreName() {
        System.out.println("setGenreName");
        gen.setGenreName("setGenreName");
        assertEquals("setGenreName", gen.getGenreName());
    }

    /**
     * Test of hashCode method, of class Genre.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = 646576560;
        int result = gen.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Genre.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        boolean expResult = false;
        boolean result = gen.equals(obj);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of toString method, of class Genre.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String result = gen.toString();
        assertFalse(result.isEmpty());
    }
    
}
