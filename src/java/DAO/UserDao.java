package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Business.User;
import Exceptions.DaoException;
import javax.sql.DataSource;

/**
 * This method to work with a user and contain all required methods for the
 * user. Class UserDao implements interface of name UserDaoInterface.
 */
public class UserDao extends Dao implements UserDaoInterface {

    /**
     * Add in constructor that takes in the source of database connections and
     * pass it up to the super class for storage.
     *
     * @param myDataSource The source of database.
     */
    public UserDao(DataSource myDataSource) {
        super(myDataSource);
    }

    /**
     * Add a blank constructor to allow for default usage
     */
    public UserDao() {
        super();
    }

    /**
     * This method will return all users from data base and store them in
     * ArrayList
     *
     * @return The ArrayList of users existing in the database.
     * @throws DaoException If message: "SQLException occurred in the
     * findAllUsers()" seen, error occurred by searching for all users stage. If
     * message: "SQLException occurred in the findAllUsers() final" seen,
     * database is not accessible.
     */
    @Override
    public ArrayList<User> findAllUsers() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<User>();

        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM USERS";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userId");
                String firstname = rs.getString("firstName");
                String lastname = rs.getString("lastName");
                java.sql.Date dob = rs.getDate("DOB");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                String email = rs.getString("email");
                boolean admin = rs.getBoolean("admin");

                User c = new User(userId, firstname, lastname, dob, phone, password, email, admin);
                users.add(c);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException occurred in the findAllUsers() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("SQLException occurred in the findAllUsers() final" + e.getMessage());
            }
        }
        return users;
    }

    /**
     * This method takes in email and password and will return all user details
     * if email and password exists in database.
     *
     * @param eml The user email to search for the user in the database.
     * @param pword The user password to search for the user in the database.
     * @return The user information if the user exist, null otherwise.
     * @throws DaoException If message: "SQLException occurred in the
     * findUserByEmailPassword()" or NullPointerException occurred in the
     * findUserByEmailPassword() seen, error occurred by searching for user
     * stage. If message: "SQLException occurred in the
     * findUserByEmailPassword() final" seen, database is not accessible.
     */
    @Override
    public User findUserByEmailPassword(String eml, String pword) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User c = null;
        try {
            con = this.getConnection();

            String query = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, eml);
            ps.setString(2, pword);

            rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("userId");
                String firstname = rs.getString("firstName");
                String lastname = rs.getString("lastName");
                java.sql.Date dob = rs.getDate("DOB");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                String email = rs.getString("email");
                boolean admin = rs.getBoolean("admin");

                c = new User(userId, firstname, lastname, dob, phone, password, email, admin);

            }
        } catch (SQLException e) {
            throw new DaoException("SQLException occured in the findUserByEmailPassword() " + e.getMessage());
        } catch (NullPointerException ex) {
            throw new DaoException("NullPointerException occurred in the findUserByEmailPassword() " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("SQLException occurred in the findUserByEmailPassword() final" + e.getMessage());
            }
        }
        return c;
    }

    /**
     * Takes in email and will return all user details if email exists in the
     * database.
     *
     * @param cEmail The user email to search for the user in the database.
     * @return The user information if user exist, null otherwise.
     * @throws DaoException If message: "SQLException occurred in the
     * findUserByEmail())" seen, error occurred by searching for user by email
     * address stage. If message: "SQLException occurred in the
     * findUserByEmail() final" seen, database is not accessible.
     */
    public User findUserByEmail(String cEmail) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User c = null;
        try {
            con = this.getConnection();

            String query = "SELECT * FROM USERS WHERE email = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, cEmail);

            rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("userId");
                String firstname = rs.getString("firstName");
                String lastname = rs.getString("lastName");
                java.sql.Date dob = rs.getDate("DOB");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                String email = rs.getString("email");
                boolean admin = rs.getBoolean("admin");

                c = new User(userId, firstname, lastname, dob, phone, password, email, admin);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException occurred in the findUserByEmail():" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("SQLException occurred in the findUserByEmail() final" + e.getMessage());
            }
        }
        return c;
    }

    // addUser method takes in all user details and stores them to the database
    /**
     * This method addUser method takes in user details and stores in the
     * database.
     *
     * @param u The user information to store in the database.
     * @return true if the user information was added successfully, false
     * otherwise.
     * @throws DaoException If message: "SQLException occurred in the addUser()"
     * seen, error occurred by adding user stage. If message: "SQLException
     * occurred in the addUser() final" seen, database is not accessible.
     */
    @Override
    public boolean addUser(User u) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        if (u != null) {
            if (findUserByEmail(u.getEmail()) == null) {
                try {
                    con = this.getConnection();

                    String query = "INSERT INTO users(firstName, lastName, DOB, phone, password, email, admin) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    ps = con.prepareStatement(query);

                    ps.setString(1, u.getFirstName());
                    ps.setString(2, u.getLastName());
                    ps.setDate(3, u.getDOB());
                    ps.setString(4, u.getPhone());
                    ps.setString(5, u.getPassword());
                    ps.setString(6, u.getEmail());
                    ps.setBoolean(7, u.isAdmin());

                    ps.execute();
                } catch (SQLException e) {
                    throw new DaoException("SQLException occurred in the addUser():" + e.getMessage());

                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                        if (con != null) {
                            freeConnection(con);
                        }
                    } catch (SQLException e) {
                        throw new DaoException("SQLException occurred in the addUser() final :" + e.getMessage());
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * This deleteUser method takes user object and if it is not null and
     * deletes in the database.
     *
     * @param u The user information to delete from the database.
     * @return true if the user information was deleted successfully, false
     * otherwise.
     * @throws DaoException If message: "SQLException occurred in the
     * deleteUser()" seen, error occurred by deleting user stage. If message:
     * "SQLException occurred in the deleteUser() final" seen, database is not
     * accessible.
     */
    @Override
    public boolean deleteUser(User u) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        if (u != null) {

            try {
                con = this.getConnection();

                String query = "Delete from users where userId = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, u.getUserId());

                ps.execute();
            } catch (SQLException e) {
                throw new DaoException("SQLException occurred in the deleteUser():" + e.getMessage());

            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    throw new DaoException("SQLException occurred in the deleteUser() final:" + e.getMessage());
                }
            }
            return true;

        } else {
            return false;
        }
    }

    /**
     * This method to update user information in the database.
     *
     * @param c The user information to be updated in the database.
     * @return true if the user information was updated successfully, false
     * otherwise.
     * @throws DaoException If message: "SQLException occurred in the
     * updateUser() method" seen, error occurred by updating user stage. If
     * message: "SQLException occurred in the updateUser() method final" seen,
     * database is not accessible.
     */
    @Override
    public boolean updateUser(User c) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean updated = false;

        if (c != null) {
            try {
                con = this.getConnection();

                String query = "UPDATE users SET firstName = ? , lastName = ?, password = ?, email = ?, admin = ? WHERE userId = ?";
                ps = con.prepareStatement(query);

                ps.setString(1, c.getFirstName());
                ps.setString(2, c.getLastName());
                ps.setString(3, c.getPassword());
                ps.setString(4, c.getEmail());
                ps.setBoolean(5, c.isAdmin());
                ps.setInt(6, c.getUserId());

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    updated = true;
                }

            } catch (SQLException e) {
                throw new DaoException("SQLException occurred in the updateUser() method:" + e.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    throw new DaoException("SQLException occurred in the updateUser() method final:" + e.getMessage());
                }
            }
        }
        return updated;
    }
}
