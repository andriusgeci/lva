package Business;

import java.sql.Timestamp;
import java.util.Objects;

public class Order {

    //Declare Variables
    private int orderID;
    private int userID;
    private int addressID;
    private Timestamp orderDate;
    private double totalOrderPrice;

    /**
     * Parameterized Constructor
     *
     * @param orderID New Order ID
     * @param userID New User ID
     * @param addressID New Address ID
     * @param orderDate New Order Date
     * @param totalOrderPrice New Total Order Price
     */
    public Order(int orderID, int userID, int addressID, Timestamp orderDate, double totalOrderPrice) {
        this.orderID = orderID;
        this.userID = userID;
        this.addressID = addressID;
        this.orderDate = orderDate;
        this.totalOrderPrice = totalOrderPrice;
    }

    /**
     * parameterized constructor without order_i; order_id column in SQL table
     * has an auto increment parameter
     *
     * @param userID New Order ID
     * @param addressID New Address ID
     * @param totalOrderPrice New Total Order Price
     */
    public Order(int userID, int addressID, double totalOrderPrice) {
        this.userID = userID;
        this.addressID = addressID;
        this.totalOrderPrice = totalOrderPrice;
    }

    /**
     * Default Constructor
     */
    public Order() {
    }

    /**
     * Getter Method
     *
     * @return Order ID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Setter Method
     *
     * @param orderID Order ID
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Getter Method
     *
     * @return User ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Setter Method
     *
     * @param userID User ID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getter Method
     *
     * @return Address ID
     */
    public int getAddressID() {
        return addressID;
    }

    /**
     * Setter Method
     *
     * @param addressID Address ID
     */
    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    /**
     * Getter Method
     *
     * @return Order Date
     */
    public Timestamp getOrderDate() {
        return orderDate;
    }

    /**
     * Getter Method
     *
     * @return Order Total Price
     */
    public double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    /**
     * Setter Method
     *
     * @param orderDate Order Date
     */
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Setter Method
     *
     * @param totalOrderPrice Total Order Price
     */
    public void setTotalOrderPrice(double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    /**
     * Calculate the hashcode of this object, based only on its orderID, userID,
     * addressID
     *
     * @return the hashcode for the Order object
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.orderID;
        hash = 97 * hash + this.userID;
        hash = 97 * hash + this.addressID;
        hash = 97 * hash + Objects.hashCode(this.orderDate);
        return hash;
    }

    /**
     *
     * @param obj other Object, usually a Order, to compare with.
     * @return true if the objects are identical
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderID != other.orderID) {
            return false;
        }
        if (this.userID != other.userID) {
            return false;
        }
        if (this.addressID != other.addressID) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return a string representation of Order object
     */
    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", userID=" + userID + ", addressID=" + addressID + ", orderDate=" + orderDate + ", totalOrderPrice=" + totalOrderPrice + '}';
    }

}
