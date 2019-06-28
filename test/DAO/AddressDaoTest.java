/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Address;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddressDaoTest {
    
    AddressDao adr = new AddressDao(new MyDataSource());
    public AddressDaoTest() {
    }
      
    //test if method returns something and its not a null
    @Test
    public void testFindAddressByIdNotNull() throws Exception
    {
        System.out.println("findAddressByIdNotNull");
        int adrId = 1;
        Address result = adr.findAddressById(adrId);
        assertNotNull(result);
        
    }        
    /**
     * Test of findAddressById method, of class AddressDao.
     */
    @Test
    public void testFindAddressById() throws Exception {
        System.out.println("findAddressById");
        int adrId = 1;
        Address result = adr.findAddressById(adrId);
        assertEquals("Drogheda", result.getCity());
       
    }
    
     //test if method returns something and its not a null        
    @Test
    public void testFindAddressByUserIdNotNull()
    {
        System.out.println("findAddressByUserIdNotNull");
        int userId = 4;
        ArrayList<Address> result = adr.findAddressByUserId(userId);
        assertNotNull(result);
        
    }        

    /**
     * Test of findAddressByUserId method, of class AddressDao.
     */
    @Test
    public void testFindAddressByUserId() 
    {
        System.out.println("findAddressByUserId");
        int userId = 4;
        String expResult = "Drogheda";
        ArrayList<Address> result = adr.findAddressByUserId(userId);
        assertEquals(expResult, result.get(0).getCity());
       
    }

    /**
     * Test of addAddress method, of class AddressDao.
     */
    @Test
    public void testAddAddress() throws Exception {
        System.out.println("addAddress");
        Address adrTest = new Address(999,4,"address1","address2","city","county","postalCode","country",true);
        boolean expResult = true;
        boolean result = adr.addAddress(adrTest);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteAddress method, of class AddressDao.
     */
    @Test
    public void testDeleteAddress() throws Exception {
        System.out.println("deleteAddress");
        Address adrTest = new Address(999,4,"address1","address2","city","county","postalCode","country",true);
        boolean expResult = true;
        boolean result = adr.deleteAddress(adrTest);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateAddress method, of class AddressDao.
     */
    @Test
    public void testUpdateAddress() throws Exception {
        System.out.println("updateAddress");
        Address adrTest = new Address(1, 4, "20 the downs", "Highlands", "Drogheda", "Meath", "TEST", "Lithuania",true);
        boolean expResult = true;
        boolean result = adr.updateAddress(adrTest);
        assertEquals(expResult, result);
    }
    
}
