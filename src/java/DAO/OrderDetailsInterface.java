package DAO;

import Business.Movie;
import Business.OrderDetails;
import Exceptions.DaoException;
import java.util.ArrayList;
import java.util.HashMap;

public interface OrderDetailsInterface {

    /**
     * This method to display all order details in the database.
     *
     * @return an ArrayList of order details in the database.
     * @throws DaoException if message: "SQLException occurred in the
     * findAllOrderDetails()" seen, error occurred by displaying all order
     * details stage. If message: "SQLException occurred in the
     * findAllOrderDetails() final" seen, database is not accessible.
     */
    public ArrayList<OrderDetails> findAllOrderDetails() throws DaoException;

    /**
     * This method to search for order details by order id.
     *
     * @param ordId The order id of order details to be searched.
     * @return order details information by order id.
     * @throws DaoException if message: "SQLException occurred in the
     * findOderDetailsById()" seen, error occurred by searching for order
     * details stage. If message: "SQLException occurred in the
     * findOderDetailsById() final" seen, database is not accessible.
     */
    public OrderDetails findOrderDetailsById(int ordId) throws DaoException;

    /**
     * This method adds order details(shopping card) to the database for a user.
     *
     * @param cart The shopping cart HashMap holds user added movies and movie
     * quantities.
     * @param id The order id of order to be added into order detail table.
     * @return true if the order details added to the database, false otherwise.
     * @throws DaoException if message: "Exception occurred in the ROLLBACK"
     * seen, error occurred by adding order details stage. If message:
     * "SQLException occurred in the addOrderDetails() final:" seen, database is
     * not accessible.
     */
    public boolean addOrderDetails(HashMap<Movie, Integer> cart, int id) throws DaoException;

    /**
     * This method deleteOrderDetails takes order details object and if it is
     * not null then deletes an order details in the database.
     *
     * @param ord The order details to be deleted.
     * @return true if the order details information deleted successfully, false
     * otherwise.
     * @throws DaoException if message: "SQLException occurred in the
     * deleteOrderDetails():" seen, error occurred by deleting order details
     * stage. If message: "SQLException occurred in the deleteOrderDetails()
     * final:" seen, database is not accessible.
     */
    public boolean deleteOrderDetails(OrderDetails ord) throws DaoException;

    /**
     * This method to update order details in the database.
     *
     * @param ord The order information to be updated in the database.
     * @return true if the order details information updated successfully, false
     * otherwise.
     * @throws DaoException if message: "SQLException occurred in the
     * updateOrderDetails() method:" seen, error occurred by updating order
     * details stage. If message: "SQLException occurred in the
     * updateOrderDetails() method final:" seen, database is not accessible.
     */
    public boolean updateOrderDetails(OrderDetails ord) throws DaoException;

}
