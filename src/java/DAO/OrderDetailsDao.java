package DAO;

import Business.Movie;
import Business.OrderDetails;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class OrderDetailsDao contains methods to work with order details table.
 * Class OrderDetailsDao implements interface of name OrderDetailsInterface.
 */
public class OrderDetailsDao extends Dao implements OrderDetailsInterface {

    /**
     * This method to display all order details in the database.
     *
     * @return an ArrayList of order details in the database.
     * @throws DaoException if message: "SQLException occurred in the
     * findAllOrderDetails()" seen, error occurred by displaying all order
     * details stage. If message: "SQLException occurred in the
     * findAllOrderDetails() final" seen, database is not accessible.
     */
    @Override
    public ArrayList<OrderDetails> findAllOrderDetails() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<OrderDetails> orDetails = new ArrayList<OrderDetails>();

        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM ORDER_DETAILS";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("order_id");
                int movieID = rs.getInt("movie_id");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                OrderDetails od = new OrderDetails(orderID, movieID, quantity, price);
                orDetails.add(od);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException occurred in the findAllOrderDetails() " + e.getMessage());
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
                throw new DaoException("SQLException occurred in the findAllOrderDetails() final" + e.getMessage());
            }
        }
        return orDetails;
    }

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
    @Override
    public OrderDetails findOrderDetailsById(int ordId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderDetails od = null;
        try {
            con = this.getConnection();

            String query = "SELECT * FROM ORDER_DETAILS WHERE order_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, ordId);

            rs = ps.executeQuery();
            if (rs.next()) {
                int orderID = rs.getInt("order_id");
                int movieID = rs.getInt("movie_id");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                od = new OrderDetails(orderID, movieID, quantity, price);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException occurred in the findOderDetailsById():" + e.getMessage());
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
                throw new DaoException("SQLException occurred in the findOderDetailsById() final" + e.getMessage());
            }
        }
        return od;
    }

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
    @Override
    public boolean addOrderDetails(HashMap<Movie, Integer> cart, int id) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        if (cart != null) {
            if (findOrderDetailsById(id) == null) {
                try {
                    con = this.getConnection();
                    MovieDao m = new MovieDao();
                    con.setAutoCommit(false);

                    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                    //Storing OrderDetails
                    for (Movie key : cart.keySet()) {
                        String query = "INSERT INTO order_details (order_id, movie_id, quantity, price) VALUES (?,?,?,?)";
                        ps = con.prepareStatement(query);
                        ps.setInt(1, id);
                        ps.setInt(2, key.getMovieId());
                        ps.setInt(3, cart.get(key));
                        ps.setDouble(4, key.getPrice() * cart.get(key));

                        ps.execute();
                    }
                    //Commiting transaction
                    con.commit();
                } catch (SQLException e) {
                    try {
                        //start rollbacck
                        con.rollback();
                    } catch (SQLException ex1) {
                        System.out.println("Exception occurred in the ROLLBACK " + ex1.getMessage());
                    }
                    throw new DaoException("addOrderDetails(): " + e.getMessage());
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                        if (con != null) {
                            freeConnection(con);
                        }
                    } catch (SQLException e) {
                        throw new DaoException("SQLException occurred in the addOrderDetails() final: " + e.getMessage());
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
    @Override
    public boolean deleteOrderDetails(OrderDetails ord) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        if (ord != null) {

            try {
                con = this.getConnection();

                String query = "Delete from order_details where order_id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, ord.getOrderID());

                ps.execute();
            } catch (SQLException e) {
                throw new DaoException("SQLException occurred in the deleteOrderDetails():" + e.getMessage());

            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    throw new DaoException("SQLException occurred in the deleteOrderDetails() final:" + e.getMessage());
                }
            }
            return true;
        } else {
            return false;
        }
    }

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
    @Override
    public boolean updateOrderDetails(OrderDetails ord) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean updated = false;

        if (ord != null) {
            try {
                con = this.getConnection();

                String query = "UPDATE order_details SET `order_id`=?,`movie_id`=?,`quantity`=?,`price`=? WHERE order_id = ? ";
                ps = con.prepareStatement(query);

                ps.setInt(1, ord.getOrderID());
                ps.setInt(2, ord.getMovieID());
                ps.setInt(3, ord.getQuantity());
                ps.setDouble(4, ord.getPrice());
                ps.setInt(5, ord.getOrderID());

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    updated = true;
                }

            } catch (SQLException e) {
                throw new DaoException("SQLException occurred in the updateOrderDetails() method:" + e.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    throw new DaoException("SQLException occurred in the updateOrderDetails() method final:" + e.getMessage());
                }
            }
        }
        return updated;
    }

    /**
     * This method to search order details by user id.
     *
     * @param orderId The order id to search order details in the database.
     * @return an ArrayList of order details of order by order id.
     * @throws DaoException if message: "Exception occurred in the
     * findOrderDetailsByOrderId() method" seen, error occurred in searching
     * order details stage. If message: "Exception occurred in the finally
     * section of the findOrderDetailsByOrderId() method" seen, database is not
     * accessible.
     */
    public ArrayList<OrderDetails> findOrderDetailsByOrderId(int orderId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = getConnection();

            String query = "SELECT * FROM order_details WHERE order_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                /*
                    declare and iniciate variables for order_details object
                 */
                int ordId = rs.getInt("order_id");
                int movieId = rs.getInt("movie_id");
                int quantity = rs.getInt("quantity");
                double moviePrice = rs.getDouble("price");

                //create current order object and fill with details for the current order_details
                OrderDetails ordDtl = new OrderDetails(ordId, movieId, quantity, moviePrice);
                // Store the current order object (now filled with information) in the arraylist
                orderDetails.add(ordDtl);
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the findOrderDetailsByUserId() method");
            e.getMessage();
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
                System.out.println("Exception occurred in the finally section of the findOrderDetailsByUserId() method");
                e.getMessage();
            }
        }
        return orderDetails;
    }

}
