package Business;

public class OrderDetails {

    //Declare Variables
    private int orderID;
    private int movieID;
    private int quantity;
    private double price;

    /**
     * Parameterized Constructor
     *
     * @param orderID
     * @param movieID
     * @param quantity
     * @param price
     */
    public OrderDetails(int orderID, int movieID, int quantity, double price) {
        this.orderID = orderID;
        this.movieID = movieID;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Default Constructor
     */
    public OrderDetails() {
        this.orderID = 123;
        this.movieID = 321;
        this.quantity = 100;
        this.price = 20.0;
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
     * @return Movie ID
     */
    public int getMovieID() {
        return movieID;
    }

    /**
     * Setter Method
     *
     * @param movieID Movie ID
     */
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    /**
     * Getter Method
     *
     * @return Movie Quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter Method
     *
     * @param quantity Movie Quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter Method
     *
     * @return Movie Price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter Method
     *
     * @param price Movie Price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Calculate the hashcode of this object, based only on its orderID,
     * movieID, quantity, price
     *
     * @return the hashcode for the Order Details object
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.orderID;
        hash = 71 * hash + this.movieID;
        hash = 71 * hash + this.quantity;
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        return hash;
    }

    /**
     *
     * @param obj other Object, usually a Order Details, to compare with.
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
        final OrderDetails other = (OrderDetails) obj;
        if (this.orderID != other.orderID) {
            return false;
        }
        if (this.movieID != other.movieID) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "OrderDetails{" + "orderID=" + orderID + ", movieID=" + movieID + ", quantity=" + quantity + ", price=" + price + '}';
    }
}
