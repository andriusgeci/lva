package DAO;

import Business.Order;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * Class OrderDao created to work with user/customer orders. Class methods used
 * to maintain an orders(add, delete, update, search...). Class OrderDao
 * implements interface of name OrderDaoInterface.
 */
public class OrderDao extends Dao implements OrderDaoInterface {

    /**
     * Add in constructor that takes in the source of database connections and
     * pass it up to the super class for storage
     *
     * @param myDataSource
     */
    public OrderDao(DataSource myDataSource) {
        super(myDataSource);
    }

    /**
     * Add a blank constructor to allow for default usage
     */
    public OrderDao() {
        super();
    }

    /**
     * This method to display all orders from orders table.
     *
     * @return the ArrayList of orders existing in the database.
     */
    @Override
    public ArrayList<Order> displayAllOrders() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Order> orders = new ArrayList();

        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = getConnection();

            String query = "Select * from orders";
            ps = con.prepareStatement(query);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();

            while (rs.next()) {
                /*
                    declare and iniciate variables for order object
                 */
                int orderId = rs.getInt("order_id");
                int userId = rs.getInt("user_id");
                int addressId = rs.getInt("address_id");
                Timestamp orderDate = rs.getTimestamp("order_date");
                double moviePrice = rs.getDouble("total_order_price");

                //create current order object and fill with details for the current order
                Order ord = new Order(orderId, userId, addressId, orderDate, moviePrice);
                // Store the current order object (now filled with information) in the arraylist
                orders.add(ord);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the displayAllOrders() method");
            System.out.println(e.getMessage());
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
                System.out.println("Exception occured in the finally section of the displayAllOrders() method");
                System.out.println(e.getMessage());
            }
        }
        return orders;
    }

    /**
     * This method: findOrderByOrderId() to find order by order id.
     *
     * @param orderId The order id to search for the order.
     * @return order information by the order id.
     */
    @Override
    public Order findOrderByOrderId(int orderId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order ord = null;
        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM orders WHERE  order_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, orderId);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            if (rs.next()) {

                // Make a Order object for the current order
                ord = new Order();

                // Fill Order object with details for the current Order
                ord.setOrderID(rs.getInt("order_id"));
                ord.setUserID(rs.getInt("user_id"));
                ord.setAddressID(rs.getInt("address_id"));
                ord.setOrderDate(rs.getTimestamp("order_date"));
                ord.setTotalOrderPrice(rs.getDouble("total_order_price"));

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the findOrderByOrderId() method ");
            System.out.println(e.getMessage());

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
                System.out.println("Exception occured in the finally section of the findOrderByOrderId() method ");
                System.out.println(e.getMessage());
            }
        }
        return ord;
    }

    /**
     * This method: findOrderByUserId() to find order using user id.
     *
     * @param userId The user id to search for the user order.
     * @return order information by the user id.
     */
    @Override
    public Order findOrderByUserId(int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order ord = null;
        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT * from orders where user_id = ? ORDER BY order_id DESC LIMIT 1";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            if (rs.next()) {

                // Make a Order object for the current order
                ord = new Order();

                // Fill Order object with details for the current Order
                ord.setOrderID(rs.getInt("order_id"));
                ord.setUserID(rs.getInt("user_id"));
                ord.setAddressID(rs.getInt("address_id"));
                ord.setOrderDate(rs.getTimestamp("order_date"));
                ord.setTotalOrderPrice(rs.getDouble("total_order_price"));

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the findOrderByUserId() method ");
            System.out.println(e.getMessage());

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
                System.out.println("Exception occured in the finally section of the findOrderByUserId() method ");
                System.out.println(e.getMessage());
            }
        }
        return ord;
    }

    /*
        This method: findOrderByAddressId() to find an order using address id;
     */
    /**
     * This method: findOrderByAddressId() to find an order using address id.
     *
     * @param addressId The address id to find order.
     * @return order information by address id.
     */
    @Override
    public Order findOrderByAddressId(int addressId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order ord = null;
        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM orders WHERE  address_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, addressId);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            if (rs.next()) {

                // Make a Order object for the current order
                ord = new Order();

                // Fill Order object with details for the current Order
                ord.setOrderID(rs.getInt("order_id"));
                ord.setUserID(rs.getInt("user_id"));
                ord.setAddressID(rs.getInt("address_id"));
                ord.setOrderDate(rs.getTimestamp("order_date"));
                ord.setTotalOrderPrice(rs.getDouble("total_order_price"));

            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the findOrderByAddressId() method ");
            System.out.println(e.getMessage());

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
                System.out.println("Exception occurred in the finally section of the findOrderByAddressId() method ");
                System.out.println(e.getMessage());
            }
        }
        return ord;
    }

    /**
     * This method: findOrderByDateOrderPlaced() to find order using date on
     * which order been placed;
     *
     * @param dateOrderPlaced The date to find order been placed.
     * @return order information on date when order been placed.
     */
    @Override
    public Order findOrderByDateOrderPlaced(Timestamp dateOrderPlaced) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order ord = null;
        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM orders WHERE  order_date = ?";
            ps = con.prepareStatement(query);
            ps.setTimestamp(1, dateOrderPlaced);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            if (rs.next()) {

                // Make a Order object for the current order
                ord = new Order();

                // Fill Order object with details for the current Order
                ord.setOrderID(rs.getInt("order_id"));
                ord.setUserID(rs.getInt("user_id"));
                ord.setAddressID(rs.getInt("address_id"));
                ord.setOrderDate(rs.getTimestamp("order_date"));
                ord.setTotalOrderPrice(rs.getDouble("total_order_price"));

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the findOrderByDateOrderPlaced() method ");
            System.out.println(e.getMessage());

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
                System.out.println("Exception occured in the finally section of the findOrderByDateOrderPlaced() method ");
                System.out.println(e.getMessage());
            }
        }
        return ord;
    }

    /*
        This method: addOrder() to add an order
     */
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
    @Override
    public boolean addOrder(Order ord) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        if (ord != null) {
            if (findOrderByOrderId(ord.getOrderID()) == null) {
                try {
                    //Get connection object using the methods in the super class (Dao.java)...
                    con = this.getConnection();
                    con.setAutoCommit(false);

                    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

                    String query = "INSERT INTO orders(user_id, address_id, order_date, total_order_price) VALUES (?, ?, ?, ?)";
                    ps = con.prepareStatement(query);

                    ps.setInt(1, ord.getUserID());
                    ps.setInt(2, ord.getAddressID());
                    ps.setTimestamp(3, ord.getOrderDate());
                    ps.setDouble(4, ord.getTotalOrderPrice());
                    ps.execute();
                    //Commiting transaction
                    con.commit();
                } catch (SQLException e) {
                    try {
                        //start rollbacck
                        con.rollback();
                    } catch (SQLException ex1) {
                        System.out.println("Exception occurred in the ROLLBACK " + ex1.getMessage());
                    }
                    throw new DaoException("transaction(): " + e.getMessage());
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                        if (con != null) {
                            freeConnection(con);
                        }
                    } catch (SQLException e) {
                        System.out.println("Exception occurred in the finally section of the addOrder() method ");
                        throw new DaoException(e.getMessage());
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
    @Override
    public boolean updateUserAddressId(int addressId, int orderId, Order ord) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean updated = false;
        //this statement checking if address id and order id relly already exists
        if ((findOrderByAddressId(ord.getAddressID()) != null) && (findOrderByOrderId(ord.getOrderID()) != null)) {
            try {
                //Get connection object using the methods in the super class (Dao.java)...
                con = getConnection();

                String query = "UPDATE orders SET address_id = ? where order_id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, addressId);
                ps.setInt(2, orderId);
                //Using a PreparedStatement to execute update
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    updated = true;
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the updateUserAddressId() method");
                throw new DaoException(e.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    System.out.println("Exception occured in the finally section of the updateUserAddressId() method");
                    throw new DaoException(e.getMessage());
                }
            }
            return updated;
        } else {
            return false;
        }
    }

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
    @Override
    public boolean deleteOrderByOrderId(int orderId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean deleted = false;

        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = getConnection();

            String query = "DELETE from orders where order_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, orderId);
            //Using a PreparedStatement to execute update
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the deleteOrderByOrderId() method");
            throw new DaoException(e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the finally section of the deleteOrderByOrderId() method");
                throw new DaoException(e.getMessage());
            }
        }
        return deleted;
    }

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
    @Override
    public boolean deleteOrderByUserId(int userId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean deleted = false;

        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = getConnection();

            String query = "DELETE from orders where user_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            //Using a PreparedStatement to execute update
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the deleteOrderByUserId() method");
            throw new DaoException(e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the finally section of the deleteOrderByUserId() method");
                throw new DaoException(e.getMessage());
            }
        }
        return deleted;
    }

    /**
     * This method takes in userId as integer and returns ArrayList of orders if
     * users have them in database
     *
     * @param userId
     * @return ArrayList of orders
     */
    public ArrayList<Order> findOrdersByUserId(int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Order> orders = new ArrayList();

        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = getConnection();

            String query = "Select * from orders where user_id =?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();

            while (rs.next()) {
                /*
                    declare and iniciate variables for order object
                 */
                int orderId = rs.getInt("order_id");
                int uId = rs.getInt("user_id");
                int addressId = rs.getInt("address_id");
                Timestamp orderDate = rs.getTimestamp("order_date");
                double moviePrice = rs.getDouble("total_order_price");

                //create current order object and fill with details for the current order
                Order ord = new Order(orderId, uId, addressId, orderDate, moviePrice);
                // Store the current order object (now filled with information) in the arraylist
                orders.add(ord);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the findOrdersByUserId() method");
            System.out.println(e.getMessage());
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
                System.out.println("Exception occured in the finally section of the findOrdersByUserId() method");
                System.out.println(e.getMessage());
            }
        }

        return orders;
    }
}
