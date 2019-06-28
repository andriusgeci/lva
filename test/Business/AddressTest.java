package Business;

import org.junit.Test;
import static org.junit.Assert.*;

public class AddressTest {
    
    Address adr1 = new Address(1, 1,"address1", "address2", "city", "county", "postalCode", "country",true);

    /**
     * Test of getAddressID method, of class Address.
     */
    @Test
    public void testGetAddressID() {
        System.out.println("getAddressID");
        int expResult = 1;
        int result = adr1.getAddressID();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setAddressID method, of class Address.
     */
    @Test
    public void testSetAddressID() {
        System.out.println("setAddressID");
        adr1.setAddressID(2);
        assertEquals(2, adr1.getAddressID());
        
    }

    /**
     * Test of getUserID method, of class Address.
     */
    @Test
    public void testGetUserID() {
        System.out.println("getUserID");
        int expResult = 1;
        int result = adr1.getUserID();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setUserID method, of class Address.
     */
    @Test
    public void testSetUserID() {
        System.out.println("setUserID");
        adr1.setUserID(2);
        assertEquals(2, adr1.getUserID());
    }

    /**
     * Test of getAddress1 method, of class Address.
     */
    @Test
    public void testGetAddress1() {
        System.out.println("getAddress1");
        String expResult = "address1";
        String result = adr1.getAddress1();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress1 method, of class Address.
     */
    @Test
    public void testSetAddress1() {
        System.out.println("setAddress1");
        adr1.setAddress1("setAddress1");
        assertEquals("setAddress1",adr1.getAddress1());
        
    }

    /**
     * Test of getAddress2 method, of class Address.
     */
    @Test
    public void testGetAddress2() {
        System.out.println("getAddress2");
        String expResult = "address2";
        String result = adr1.getAddress2();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress2 method, of class Address.
     */
    @Test
    public void testSetAddress2() {
        System.out.println("setAddress2");
        adr1.setAddress2("setAddress2");
        assertEquals("setAddress2",adr1.getAddress2());
    }

    /**
     * Test of getCity method, of class Address.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        String expResult = "city";
        String result = adr1.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCity method, of class Address.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        adr1.setCity("setCity");
        assertEquals("setCity", adr1.getCity());
    }

    /**
     * Test of getCounty method, of class Address.
     */
    @Test
    public void testGetCounty() {
        System.out.println("getCounty");
        String expResult = "county";
        String result = adr1.getCounty();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCounty method, of class Address.
     */
    @Test
    public void testSetCounty() {
        System.out.println("setCounty");
        adr1.setCounty("setCounty");
        assertEquals("setCounty", adr1.getCounty());
    }

    /**
     * Test of getPostalCode method, of class Address.
     */
    @Test
    public void testGetPostalCode() {
        System.out.println("getPostalCode");
        String expResult = "postalCode";
        String result = adr1.getPostalCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPostalCode method, of class Address.
     */
    @Test
    public void testSetPostalCode() {
        System.out.println("setPostalCode");
        adr1.setPostalCode("setPostalCode");
        assertEquals("setPostalCode", adr1.getPostalCode());
    }

    /**
     * Test of getCountry method, of class Address.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        String expResult = "country";
        String result = adr1.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCountry method, of class Address.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        adr1.setCountry("setCountry");
        assertEquals("setCountry", adr1.getCountry());
    }

    /**
     * Test of hashCode method, of class Address.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = -1187645952;
        int result = adr1.hashCode();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of equals method, of class Address.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        boolean expResult = false;
        boolean result = adr1.equals(obj);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of toString method, of class Address.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String result = adr1.toString();
        assertFalse(result.isEmpty());
       
    }

}
