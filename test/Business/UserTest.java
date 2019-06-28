
package Business;
import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    User c = new User(123, "firstName", "lastName", Date.valueOf(LocalDate.now()),  "phone", "password", "email", true);
    User a = new User(123, "firstName", "lastName", Date.valueOf(LocalDate.now()),  "phone", "password", "email", true);

    /**
     * Test of getUserId method, of class User.
     */
    @Test
    public void testGetUserId() {
        int expResult = 123;
        int result = c.getUserId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserId method, of class User.
     */
    @Test
    public void testSetUserId() {
        c.setUserId(111);
        assertEquals(111, c.getUserId());
    }

    /**
     * Test of getFirstName method, of class User.
     */
    @Test
    public void testGetFirstName() {
        String expResult = "firstName";
        String result = c.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class User.
     */
    @Test
    public void testSetFirstName() {
        c.setFirstName("abc");
        assertEquals("abc", c.getFirstName());
    }

    /**
     * Test of getLastName method, of class User.
     */
    @Test
    public void testGetLastName() {
        String expResult = "lastName";
        String result = c.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastName method, of class User.
     */
    @Test
    public void testSetLastName() {
        c.setLastName("zzz");
        assertEquals("zzz", c.getLastName());
    }

    /**
     * Test of getDOB method, of class User.
     */
    @Test
    public void testGetDOB() {
        Date expResult = Date.valueOf(LocalDate.now());
        Date result = c.getDOB();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of getPhone method, of class User.
     */
    @Test
    public void testGetPhone() {
        String expResult = "phone";
        String result = c.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPhone method, of class User.
     */
    @Test
    public void testSetPhone() {
        c.setPhone("123");
        assertEquals("123", c.getPhone());
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        String expResult = "password";
        String result = c.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        c.setPassword("pass");
        assertEquals("pass", c.getPassword());
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        String expResult = "email";
        String result = c.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        c.setEmail("eee");
        assertEquals("eee", c.getEmail());
    }

    /**
     * Test of isAdmin method, of class User.
     */
    @Test
    public void testIsAdmin() {
        boolean expResult = true;
        boolean result = c.isAdmin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAdmin method, of class User.
     */
    @Test
    public void testSetAdmin() {
        c.setAdmin(false);
        assertEquals(false, c.isAdmin());
    }

    /**
     * Test of hashCode method, of class User.
     */
    @Test
    public void testHashCode() {
        int expResult = 96619715;
        int result = c.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals() {
        boolean expResult = true;
        boolean result = c.equals(a);
        assertEquals(expResult, result);
    }

}
