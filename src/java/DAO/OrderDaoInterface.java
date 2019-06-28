/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Order;
import Exceptions.DaoException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface OrderDaoInterface {

    /**
     * This method to display all orders from orders table.
     *
     * @return the ArrayList of orders existing in the database.
     */
    public ArrayList<Order> displayAllOrders();

    /**
     * This method: findOrderByOrderId() to find order by order id.
     *
     * @param orderId The order id to search for the order.
     * @return order information by the order id.
     */
    public Order findOrderByOrderId(int orderId);

    /**
     * This method: findOrderByUserId() to find order using user id.
     *
     * @param userId The user id to search for the user order.
     * @return order information by the user id.
     */
    public Order findOrderByUserId(int userId);

    /**
     * This method: findOrderByAddressId() to find an order using address id.
     *
     * @param addressId The address id to find order.
     * @return order information by address id.
     */
    public Order findOrderByAddressId(int addressId);

    /**
     * This method: findOrderByDateOrderPlaced() to find order using date on
     * which order been placed;
     *
     * @param dateOrderPlaced The date to find order been placed.
     * @return order information on date when order been placed.
     */
    public Order findOrderByDateOrderPlaced(Timestamp dateOrderPlaced);

    /**
     * This method: addOrder() to add an order in the database.
     *
     * @param ord The order information to be added in the database.
     * @return true if order information was added successfully, false
     * otherwise.
     * @throws DaoException if message "Exception occurred in the ROLLBACK"
     * seen, error occurred by adding order stage. If message "Exception
     * occurred in the finally section of the addOrder() method " seen, database
     * is not accessible.
     */
    public boolean addOrder(Order ord) throws DaoException;

    /**
     * This method: updateUserAddressId() to update user address id by order id.
     *
     * @param addressId The address id to be updated in orders table in the
     * database.
     * @param orderId The order id to know which address id should be updated.
     * @param ord Order information to check that order already exist.
     * @return true if address id updated successfully, false otherwise.
     * @throws DaoException if message "Exception occurred in the
     * updateUserAddressId() method" seen, error occurred by updating address id
     * stage. If message "Exception occurred in the finally section of the
     * updateUserAddressId() method " seen, database is not accessible.
     */
    public boolean updateUserAddressId(int addressId, int orderId, Order ord) throws DaoException;

    /**
     * This method: deleteOrderByOrderId() to delete an order by order id in the
     * database.
     *
     * @param orderId The order id of the order to be deleted in the database.
     * @return true if the order was deleted successfully, false otherwise.
     * @throws DaoException if message "Exception occurred in the
     * deleteOrderByOrderId() method" seen, error occurred by deleting order
     * stage. If message "Exception occurred in the finally section of the
     * deleteOrderByOrderId() method" seen, database is not accessible.
     */
    public boolean deleteOrderByOrderId(int orderId) throws DaoException;

    /**
     * This method: deleteOrderByUserId() to delete an order by order id.
     *
     * @param userId The user id to delete order in the database.
     * @return true if the order information was deleted successfully, false
     * otherwise.
     * @throws DaoException if message "Exception occurred in the
     * deleteOrderByUserId() method" seen, error occurred by deleting order
     * stage. If message "Exception occurred in the finally section of the
     * deleteOrderByUserId() method" seen, database is not accessible.
     */
    public boolean deleteOrderByUserId(int userId) throws DaoException;

}
