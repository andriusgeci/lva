/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReviewTest {

    Review rev = new Review(1, 1, 1, "reviewText", 1.0, new Date(1990 / 10 / 10));

    /**
     * Test of getReviewID method, of class Review.
     */
    @Test
    public void testGetReviewID() {
        System.out.println("getReviewID");
        int expResult = 1;
        int result = rev.getReviewID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setReviewID method, of class Review.
     */
    @Test
    public void testSetReviewID() {
        System.out.println("setReviewID");
        rev.setReviewID(2);
        assertEquals(2, rev.getReviewID());
    }

    /**
     * Test of getMovieID method, of class Review.
     */
    @Test
    public void testGetMovieID() {
        System.out.println("getMovieID");
        int expResult = 1;
        int result = rev.getMovieID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMovieID method, of class Review.
     */
    @Test
    public void testSetMovieID() {
        System.out.println("setMovieID");
        rev.setMovieID(2);
        assertEquals(2, rev.getMovieID());
    }

    /**
     * Test of getUserID method, of class Review.
     */
    @Test
    public void testGetUserID() {
        System.out.println("getUserID");
        int expResult = 1;
        int result = rev.getUserID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserID method, of class Review.
     */
    @Test
    public void testSetUserID() {
        System.out.println("setUserID");
        rev.setUserID(2);
        assertEquals(2, rev.getUserID());
    }

    /**
     * Test of getReviewText method, of class Review.
     */
    @Test
    public void testGetReviewText() {
        System.out.println("getReviewText");
        String expResult = "reviewText";
        String result = rev.getReviewText();
        assertEquals(expResult, result);
    }

    /**
     * Test of setReviewText method, of class Review.
     */
    @Test
    public void testSetReviewText() {
        System.out.println("setReviewText");
        rev.setReviewText("setReviewText");
        assertEquals("setReviewText", rev.getReviewText());
    }

    /**
     * Test of getReviewRating method, of class Review.
     */
    @Test
    public void testGetReviewRating() {
        System.out.println("getReviewRating");
        double expResult = 1.0;
        double result = rev.getReviewRating();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setReviewRating method, of class Review.
     */
    @Test
    public void testSetReviewRating() {
        System.out.println("setReviewRating");
        rev.setReviewRating(2.0);
        assertEquals(2.0, rev.getReviewRating(), 0.0);
    }

    /**
     * Test of getReviewDate method, of class Review.
     */
    @Test
    public void testGetReviewDate() {
        System.out.println("getReviewDate");
        Date expResult = new Date(1990 / 10 / 10);
        Date result = rev.getReviewDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setReviewDate method, of class Review.
     */
    @Test
    public void testSetReviewDate() {
        System.out.println("setReviewDate");
        Date date = new Date(1998 / 05 / 05);
        rev.setReviewDate(date);
        assertEquals(date, rev.getReviewDate());
    }

    /**
     * Test of hashCode method, of class Review.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        System.out.println(rev.hashCode());
        int expResult = 2747526;
        int result = rev.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Review.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        boolean expResult = false;
        boolean result = rev.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Review.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String result = rev.toString();
        assertFalse(result.isEmpty());
        
    }

}
