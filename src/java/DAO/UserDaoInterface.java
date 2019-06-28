package DAO;

import Business.User;
import Exceptions.DaoException;

import java.util.ArrayList;

public interface UserDaoInterface {

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
    public ArrayList<User> findAllUsers() throws DaoException;

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
    public User findUserByEmailPassword(String eml, String pword) throws DaoException;

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
    public User findUserByEmail(String cEmail) throws DaoException;

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
    public boolean addUser(User u) throws DaoException;

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
    public boolean deleteUser(User u) throws DaoException;

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
    public boolean updateUser(User c) throws DaoException;
}
