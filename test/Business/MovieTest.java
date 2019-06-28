/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import org.junit.Test;
import static org.junit.Assert.*;

public class MovieTest {
    
    Movie mov = new Movie(1, "title", "description", 1, "ageRating", 1, "imgUrl", 1.0, 1, 1.0 , 1, 1);
    


    /**
     * Test of getMovieId method, of class Movie.
     */
    @Test
    public void testGetMovieId() {
        System.out.println("getMovieId");
        int expResult = 1;
        int result = mov.getMovieId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTitle method, of class Movie.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        String expResult = "title";
        String result = mov.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getReleaseYear method, of class Movie.
     */
    @Test
    public void testGetReleaseYear() {
        System.out.println("getReleaseYear");
        int expResult = 1;
        int result = mov.getReleaseYear();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Movie.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "description";
        String result = mov.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMovieLegth method, of class Movie.
     */
    @Test
    public void testGetMovieLegth() {
        System.out.println("getMovieLegth");
        int expResult = 1;
        int result = mov.getMovieLegth();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getAgeRating method, of class Movie.
     */
    @Test
    public void testGetAgeRating() {
        System.out.println("getAgeRating");
        String expResult = "ageRating";
        String result = mov.getAgeRating();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImgUrl method, of class Movie.
     */
    @Test
    public void testGetImgUrl() {
        System.out.println("getImgUrl");
        String expResult = "imgUrl";
        String result = mov.getImgUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrice method, of class Movie.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        double expResult = 1.0;
        double result = mov.getPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getQuantityInStock method, of class Movie.
     */
    @Test
    public void testGetQuantityInStock() {
        System.out.println("getQuantityInStock");
        int expResult = 1;
        int result = mov.getQuantityInStock();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMovieId method, of class Movie.
     */
    @Test
    public void testSetMovieId() {
        System.out.println("setMovieId");
        mov.setMovieId(2);
        assertEquals(2, mov.getMovieId());
    }

    /**
     * Test of getRatingAvg method, of class Movie.
     */
    @Test
    public void testGetRatingAvg() {
        System.out.println("getRatingAvg");
        mov.setRatingAvg(2.0);
        assertEquals(2.0, mov.getRatingAvg(), 0.0);
    }

    /**
     * Test of getRatingNum method, of class Movie.
     */
    @Test
    public void testGetRatingNum() {
        System.out.println("getRatingNum");
        mov.setRatingNum(2);
        assertEquals(2, mov.getRatingNum());
    }

    /**
     * Test of getRatingTotal method, of class Movie.
     */
    @Test
    public void testGetRatingTotal() {
        System.out.println("getRatingTotal");
        mov.setRatingTotal(2);
        assertEquals(2, mov.getRatingTotal());  
    }

    /**
     * Test of setTitle method, of class Movie.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        mov.setTitle("setTitle");
        assertEquals("setTitle", mov.getTitle());
    }

    /**
     * Test of setDescription method, of class Movie.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        mov.setDescription("setDescription");
        assertEquals("setDescription", mov.getDescription());
    }

    /**
     * Test of setReleaseYear method, of class Movie.
     */
    @Test
    public void testSetReleaseYear() {
        System.out.println("setReleaseYear");
        mov.setReleaseYear(2);
        assertEquals(2, mov.getReleaseYear());
    }

    /**
     * Test of setMovieLegth method, of class Movie.
     */
    @Test
    public void testSetMovieLegth() {
        System.out.println("setMovieLegth");
        mov.setMovieLegth(2);
        assertEquals(2, mov.getMovieLegth());
    }

    /**
     * Test of setAgeRating method, of class Movie.
     */
    @Test
    public void testSetAgeRating() {
        System.out.println("setAgeRating");
        mov.setAgeRating("setAgeRating");
        assertEquals("setAgeRating", mov.getAgeRating());
    }

    /**
     * Test of setImgUrl method, of class Movie.
     */
    @Test
    public void testSetImgUrl() {
        System.out.println("setImgUrl");
        mov.setImgUrl("setImgUrl");
        assertEquals("setImgUrl", mov.getImgUrl());
    }

    /**
     * Test of setPrice method, of class Movie.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        mov.setPrice(2.0);
        assertEquals(2.0, mov.getPrice(), 0.0);
    }

    /**
     * Test of setQuantityInStock method, of class Movie.
     */
    @Test
    public void testSetQuantityInStock() {
        System.out.println("setQuantityInStock");
        mov.setQuantityInStock(2);
        assertEquals(2, mov.getQuantityInStock());
    }

    /**
     * Test of setRatingAvg method, of class Movie.
     */
    @Test
    public void testSetRatingAvg() {
        System.out.println("setRatingAvg");
        mov.setRatingAvg(2.0);
        assertEquals(2.0, mov.getRatingAvg(), 0.0);
    }

    /**
     * Test of setRatingNum method, of class Movie.
     */
    @Test
    public void testSetRatingNum() {
        System.out.println("setRatingNum");
        mov.setRatingNum(2);
        assertEquals(2, mov.getRatingNum());
    }

    /**
     * Test of setRatingTotal method, of class Movie.
     */
    @Test
    public void testSetRatingTotal() {
        System.out.println("setRatingTotal");
        mov.setRatingTotal(2);
        assertEquals(2, mov.getRatingTotal());
    }

    /**
     * Test of hashCode method, of class Movie.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = -210868963;
        int result = mov.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Movie.
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
     * Test of toString method, of class Movie.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String result = mov.toString();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of compareTo method, of class Movie.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Movie m = new Movie(1, "title", "description", 1, "ageRating", 1, "imgUrl", 1.0, 1, 1.0 , 1, 1);
        int expResult = 0;
        int result = mov.compareTo(m);
        assertEquals(expResult, result);
        
    }
    
}
