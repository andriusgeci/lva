
package Business;
import java.sql.Timestamp;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderTest {
    
    Order ord = new Order(1, 1, 1, new Timestamp(1990/10/10), 2.0);
    
    /**
     * Test of getOrderID method, of class Order.
     */
    @Test
    public void testGetOrderID() {
        System.out.println("getOrderID");
        int expResult = 1;
        int result = ord.getOrderID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderID method, of class Order.
     */
    @Test
    public void testSetOrderID() {
        System.out.println("setOrderID");
        ord.setOrderID(2);
        assertEquals(2, ord.getOrderID());
    }

    /**
     * Test of getUserID method, of class Order.
     */
    @Test
    public void testGetUserID() {
        System.out.println("getUserID");
        int expResult = 1;
        int result = ord.getUserID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserID method, of class Order.
     */
    @Test
    public void testSetUserID() {
        System.out.println("setUserID");
        ord.setUserID(2);
        assertEquals(2, ord.getUserID());
    }

    /**
     * Test of getAddressID method, of class Order.
     */
    @Test
    public void testGetAddressID() {
        System.out.println("getAddressID");
        int expResult = 1;
        int result = ord.getAddressID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddressID method, of class Order.
     */
    @Test
    public void testSetAddressID() {
        System.out.println("setAddressID");
        ord.setAddressID(2);
        assertEquals(2, ord.getAddressID());
    }

    /**
     * Test of getOrderDate method, of class Order.
     */
    @Test
    public void testGetOrderDate() {
        System.out.println("getOrderDate");
        Timestamp expResult = new Timestamp(1990/10/10);
        Timestamp result = ord.getOrderDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalOrderPrice method, of class Order.
     */
    @Test
    public void testGetTotalOrderPrice() {
        System.out.println("getTotalOrderPrice");
        double expResult = 2.0;
        double result = ord.getTotalOrderPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setOrderDate method, of class Order.
     */
    @Test
    public void testSetOrderDate() {
        System.out.println("setOrderDate");
        Timestamp ordDate = new Timestamp(1998/05/05);
        ord.setOrderDate(ordDate);
        assertEquals(ordDate, ord.getOrderDate());
    }

    /**
     * Test of setTotalOrderPrice method, of class Order.
     */
    @Test
    public void testSetTotalOrderPrice() {
        System.out.println("setTotalOrderPrice");
        ord.setTotalOrderPrice(2.0);
        assertEquals(2.0, ord.getTotalOrderPrice(), 0.0);
    }

    /**
     * Test of hashCode method, of class Order.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = 266510041;
        int result = ord.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Order.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = ord;
        boolean expResult = true;
        boolean result = ord.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Order.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String result = ord.toString();
        assertFalse(result.isEmpty());
    }
    
}
