/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Order;
import java.sql.Timestamp;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderDaoTest {
    
    OrderDao ord = new OrderDao(new MyDataSource());
    
    /**
     * Test of displayAllOrders method, of class OrderDao.
     */
    @Test
    public void testDisplayAllOrders() {
        System.out.println("displayAllOrders");
       assertNotNull(ord.displayAllOrders());
    }

    /**
     * Test of findOrderByOrderId method, of class OrderDao.
     */
    @Test
    public void testFindOrderByOrderId() {
        System.out.println("findOrderByOrderId");
        int orderId = 1;
        assertNotNull(ord.findOrderByOrderId(orderId));
    }

    /**
     * Test of findOrderByUserId method, of class OrderDao.
     */
    @Test
    public void testFindOrderByUserId() {
        System.out.println("findOrderByUserId");
        int userId = 4;
        assertNotNull(ord.findOrderByUserId(userId));
    }

    /**
     * Test of findOrderByAddressId method, of class OrderDao.
     */
    @Test
    public void testFindOrderByAddressId() {
        System.out.println("findOrderByAddressId");
        int addressId = 1;
        assertNotNull(ord.findOrderByAddressId(addressId));
    }

    /**
     * Test of findOrderByDateOrderPlaced method, of class OrderDao.
     */
    @Test
    public void testFindOrderByDateOrderPlaced() {
        System.out.println("findOrderByDateOrderPlaced");
        Timestamp dateOrderPlaced = new Timestamp(1990/10/10);
        assertNotNull(ord.findOrderByDateOrderPlaced(dateOrderPlaced));    
    }

    /**
     * Test of addOrder method, of class OrderDao.
     */
    @Test
    public void testAddOrder() throws Exception {
        System.out.println("addOrder");
        Order ordTest = new Order(1,4,1,new Timestamp(1990/10/10),20.0);
        assertTrue(ord.addOrder(ordTest));
    }

    /**
     * Test of updateUserAddressId method, of class OrderDao.
     */
    @Test
    public void testUpdateUserAddressId() throws Exception {
        System.out.println("updateUserAddressId");
        int addressId = 0;
        int orderId = 0;
        Order ord = null;
        OrderDao instance = new OrderDao();
        boolean expResult = false;
        boolean result = instance.updateUserAddressId(addressId, orderId, ord);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteOrderByOrderId method, of class OrderDao.
     */
    @Test
    public void testDeleteOrderByOrderId() throws Exception {
        System.out.println("deleteOrderByOrderId");
        int orderId = 0;
        OrderDao instance = new OrderDao();
        boolean expResult = false;
        boolean result = instance.deleteOrderByOrderId(orderId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteOrderByUserId method, of class OrderDao.
     */
    @Test
    public void testDeleteOrderByUserId() throws Exception {
        System.out.println("deleteOrderByUserId");
        int userId = 0;
        OrderDao instance = new OrderDao();
        boolean expResult = false;
        boolean result = instance.deleteOrderByUserId(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
