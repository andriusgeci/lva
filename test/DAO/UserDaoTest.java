package DAO;

import Business.User;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDaoTest {
    
    User test = new User("testFirstName", "testSurName", "Password1", "test@gmail.com");
    User a = new User("firstName", "lastName", "password", "email");
    User b = null;
    UserDao instance = new UserDao(new MyDataSource());
    
    

    /**
     * Test of displayAllMovies method, of class MovieDao.
     */
    @Test
    public void testFindAllUsersNotNull() throws Exception {
        List<User> result = instance.findAllUsers();
        assertNotNull(result);
    }

    /**
     * Test of findAllUsers method, of class UserDao.
     */
    @Test
    public void testFindAllUsers() throws Exception {
        List<User> result = instance.findAllUsers();
        assertEquals("Andrius", result.get(0).getFirstName());

    }

    /**
     * Test of findUserByEmailPassword method, of class UserDao.
     */
    @Test
    public void testFindUserByEmailPassword() throws Exception {
        String eml = "andrius@gmail.com";
        String pword = "$2a$10$wwOhI5ZK161IXVyH734eOelSkKEzeQmgcJnyutHs02fmfw/EOl6Bq";
        User expResult = instance.findAllUsers().get(0);
        User result = instance.findUserByEmailPassword(eml, pword);
        assertEquals(expResult, result);

    }

    /**
     * Test of testFindUserByEmailPasswordNotNull method, of class UserDao.
     */
    @Test
    public void testFindUserByEmailPasswordNotNull() throws Exception {
        String eml = "andrius@gmail.com";
        String pword = "$2a$10$wwOhI5ZK161IXVyH734eOelSkKEzeQmgcJnyutHs02fmfw/EOl6Bq";
        User result = instance.findUserByEmailPassword(eml, pword);
        assertNotNull(result);
    }

    /**
     * Test of testFindUserByEmailPasswordNull method, of class UserDao.
     */
    @Test
    public void testFindUserByEmailPasswordNull() throws Exception {
        String eml = "andrius@gmail.com";
        String pword = "2432612431302477774f6849355a4b3136314958567948373334654f656c536b4b457a65516d67634a6e79757448733032666d66772f454f6c364271";
        User result = instance.findUserByEmailPassword(eml, pword);
        assertNull(result);
    }

    /**
     * Test of findUserByEmail method, of class UserDao.
     */
    @Test
    public void testFindUserByEmail() throws Exception {
        String cEmail = "Andrius@gmail.com";
        User expResult = instance.findAllUsers().get(0);
        User result = instance.findUserByEmail(cEmail);
        assertEquals(expResult, result);
    }

    /**
     * Test of testFindUserByEmailNotNull method, of class UserDao.
     */
    @Test
    public void testFindUserByEmailNotNull() throws Exception {
        String cEmail = "Vitalijus@gmail.com";
        User result = instance.findUserByEmail(cEmail);
        assertNotNull(result);
    }

    /**
     * Test of testFindUserByEmailNull method, of class UserDao.
     */
    @Test
    public void testFindUserByEmailNull() throws Exception {
        String cEmail = "qqq";
        User result = instance.findUserByEmail(cEmail);
        assertNull(result);
    }

    /**
     * Test of testAddUserTrue method, of class UserDao.
     */
    @Test
    public void testAddUserTrue() throws Exception {
        boolean result = instance.addUser(a);
        assertTrue(result);
        User aa = instance.findUserByEmail("email");
        instance.deleteUser(aa);
    }

    /**
     * Test of testAddUserFalse method, of class UserDao.
     */
    @Test
    public void testAddUserFalse() throws Exception {
        boolean result = instance.addUser(b);
        assertFalse(result);
    }
}
