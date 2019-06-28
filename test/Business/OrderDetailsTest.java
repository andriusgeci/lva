/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderDetailsTest {
    
    OrderDetails ord = new OrderDetails(1, 1, 1, 1.0);
    
    /**
     * Test of getOrderID method, of class OrderDetails.
     */
    @Test
    public void testGetOrderID() {
        System.out.println("getOrderID");
        int expResult = 1;
        int result = ord.getOrderID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderID method, of class OrderDetails.
     */
    @Test
    public void testSetOrderID() {
        System.out.println("setOrderID");
        ord.setOrderID(2);
        assertEquals(2, ord.getOrderID());
    }

    /**
     * Test of getMovieID method, of class OrderDetails.
     */
    @Test
    public void testGetMovieID() {
        System.out.println("getMovieID");
        int expResult = 1;
        int result = ord.getMovieID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMovieID method, of class OrderDetails.
     */
    @Test
    public void testSetMovieID() {
        System.out.println("setMovieID");
        ord.setMovieID(2);
        assertEquals(2, ord.getMovieID());
    }

    /**
     * Test of getQuantity method, of class OrderDetails.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        int expResult = 1;
        int result = ord.getQuantity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setQuantity method, of class OrderDetails.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        ord.setQuantity(2);
        assertEquals(2, ord.getQuantity());
    }

    /**
     * Test of getPrice method, of class OrderDetails.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        double expResult = 1.0;
        double result = ord.getPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPrice method, of class OrderDetails.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        ord.setPrice(2.0);
        assertEquals(2.0, ord.getPrice(), 0.0);
    }

    /**
     * Test of hashCode method, of class OrderDetails.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = 1200114676;
        int result = ord.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class OrderDetails.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        boolean expResult = false;
        boolean result = ord.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class OrderDetails.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String result = ord.toString();
        assertFalse(result.isEmpty());
    }
    
}
