/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Movie;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class MovieDaoTest {
    
    MovieDao mv = new MovieDao(new MyDataSource());
    
    /**
     * Test of displayAllMovies method, of class MovieDao.
     */
    @Test
    public void testDisplayAllMovies() {
        System.out.println("displayAllMovies");
        ArrayList<Movie> result = mv.displayAllMovies();
        assertNotNull(result);
    }

    /**
     * Test of findMovieByTitle method, of class MovieDao.
     */
    @Test
    public void testFindMovieByTitle() {
        System.out.println("findMovieByTitle");
        String movieTitle = "Deadpool";
        Movie expResult = mv.findMovieByTitle(movieTitle);
        Movie result = mv.findMovieByTitle(movieTitle);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchSimilarMoviesByTitle method, of class MovieDao.
     */
    @Test
    public void testSearchSimilarMoviesByTitle() {
        System.out.println("searchSimilarMoviesByTitle");
        String movieTitle = "Elysium";
        ArrayList<Movie> result = mv.searchSimilarMoviesByTitle(movieTitle);
        assertNotNull(result);
    }

    /**
     * Test of addMovie method, of class MovieDao.
     */
    @Test
    public void testAddMovie() throws Exception {
        System.out.println("addMovie");
        Movie mov = new Movie(999,"title","description",2016,"ageRating",60,"imgUrl",20.0,3,1,2,2);
        boolean expResult = true;
        boolean result = mv.addMovie(mov);
        assertEquals(expResult, result);
    }

//    /**//METHOD NOT IN USE CHECK MOVIEDAO
//     * Test of updateQuantityOfMoviesInStock method, of class MovieDao.
//     */
//    @Test
//    public void testUpdateQuantityOfMoviesInStock() throws Exception {
//        System.out.println("updateQuantityOfMoviesInStock");
//        Movie mov = new Movie(999,"title","description",2016,"ageRating",60,"imgUrl",20.0,10,1,2,2);
//        boolean expResult = true;
//        boolean result = mv.updateQuantityOfMoviesInStock(mov);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of deleteMovie method, of class MovieDao.
     */
    @Test
    public void testDeleteMovie() throws Exception {
        System.out.println("deleteMovie");
        Movie mov = new Movie(999,"title","description",2016,"ageRating",60,"imgUrl",20.0,3,1,2,2);
        boolean expResult = true;
        boolean result = mv.deleteMovie(mov);
        assertEquals(expResult, result);
    }

    /**
     * Test of findMoviesByPriceBetweenMinAndMaxPrice method, of class MovieDao.
     */
    @Test
    public void testFindMoviesByPriceBetweenMinAndMaxPrice() {
        System.out.println("findMoviesByPriceBetweenMinAndMaxPrice");
        int minPrice = 10;
        int maxPrice = 20;
        ArrayList<Movie> result = mv.findMoviesByPriceBetweenMinAndMaxPrice(minPrice, maxPrice);
        assertNotNull(result);
    }

    /**
     * Test of findMoviePriceMin method, of class MovieDao.
     */
    @Test
    public void testFindMoviePriceMin() {
        System.out.println("findMoviePriceMin");
        double expResult = 1;
        double result = mv.findMoviePriceMin();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of findMoviePriceMax method, of class MovieDao.
     */
    @Test
    public void testFindMoviePriceMax() {
        System.out.println("findMoviePriceMax");
        double expResult = 82.0;
        double result = mv.findMoviePriceMax();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of updateMovie method, of class MovieDao.
     */
    @Test
    public void testUpdateMovie() throws Exception {
        System.out.println("updateMovie");
        Movie mov = mv.findMovieByTitle("Elysium");
        mov.setAgeRating("R");
        assertTrue(mv.updateMovie(mov));
    }
    
}
