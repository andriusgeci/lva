package DAO;

import Business.Address;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * Class AddressDao contains create, read, update, delete methods to organize an
 * addresses. And findAddressById method to find address by address id,
 * findAddressByUserId method to find address by user id Class AddressDao
 * implements interface of name AddressDaoInterface.
 */
public class AddressDao extends Dao implements AddressDaoInterface {

    /**
     * Add in constructor that takes in the source of database connections and
     * pass it up to the super class for storage
     *
     * @param myDataSource
     */
    public AddressDao(DataSource myDataSource) {
        super(myDataSource);
    }

    /**
     * Add a blank constructor to allow for default usage
     */
    public AddressDao() {
        super();
    }

    /**
     * findAddressById method takes address id and finds address by taken id if
     * address exist. If address not exist method will return null.
     *
     * @param adrId address id to find address with presented id
     * @return address details if address with presented id exists in database.
     * @throws DaoException if message "SQLException occurred in the
     * findAddressByID():" seen, address with presented id doesn't exist. If
     * message "SQLException occurred in the findAddressByID() final" seen,
     * database is not accessible
     */
    @Override
    public Address findAddressById(int adrId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Address adr = null;
        try {
            con = this.getConnection();
            String query = "SELECT * FROM ADDRESS WHERE address_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, adrId);

            rs = ps.executeQuery();
            if (rs.next()) {
                int addressID = rs.getInt("address_id");
                int userID = rs.getInt("user_id");
                String address1 = rs.getString("address_line_1");
                String address2 = rs.getString("address_line_2");
                String city = rs.getString("city");
                String county = rs.getString("county");
                String postalCode = rs.getString("postal_code");
                String country = rs.getString("country");
                boolean primaryAddress = rs.getBoolean("primary_address");

                adr = new Address(addressID, userID, address1, address2, city, county, postalCode, country, primaryAddress);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException occurred in the findAddressByID():" + e.getMessage());
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
                throw new DaoException("SQLException occurred in the findAddressByID() final" + e.getMessage());
            }
        }
        return adr;
    }

    /**
     * findAddressByUserId method takes in user id and finds address by taken
     * user id
     *
     * @param userId takes in user id to find address of the user
     * @return all user addresses full details if user id exists in a database
     */
    public ArrayList<Address> findAddressByUserId(int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Address> address = new ArrayList<Address>();

        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = getConnection();
            String query = "SELECT * FROM ADDRESS a join users u on (a.user_id = u.userId) WHERE user_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int addressID = rs.getInt("address_id");
                int userID = rs.getInt("user_id");
                String address1 = rs.getString("address_line_1");
                String address2 = rs.getString("address_line_2");
                String city = rs.getString("city");
                String county = rs.getString("county");
                String postalCode = rs.getString("postal_code");
                String country = rs.getString("country");
                boolean primaryAddress = rs.getBoolean("primary_address");
                Address adr = new Address(addressID, userID, address1, address2, city, county, postalCode, country, primaryAddress);
                address.add(adr);
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the findAddressByUserId() method");
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
                System.out.println("Exception occurred in the finally section of the findAddressByUserId() method");
                e.getMessage();
            }
        }

        return address;
    }

    /**
     * addAddress method adds address to the database for a user
     *
     * @param adr takes in all address details and stores them to the database
     * @return true/false depend on address details stored or not
     * @throws DaoException if message "SQLException occurred in the
     * addAddress():" seen, error occurred by inserting address stage. If
     * message "SQLException occurred in the addAddress() final :" seen,
     * database is not accessible
     */
    @Override
    public boolean addAddress(Address adr) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        if (adr != null) {
            if (findAddressById(adr.getAddressID()) == null) {
                try {
                    con = this.getConnection();

                    String query = "INSERT INTO ADDRESS(`address_id`, `user_id`, `address_line_1`, `address_line_2`, `city`, `county`, `postal_code`, `country`,`primary_address`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    ps = con.prepareStatement(query);

                    ps.setInt(1, adr.getAddressID());
                    ps.setInt(2, adr.getUserID());
                    ps.setString(3, adr.getAddress1());
                    ps.setString(4, adr.getAddress2());
                    ps.setString(5, adr.getCity());
                    ps.setString(6, adr.getCounty());
                    ps.setString(7, adr.getPostalCode());
                    ps.setString(8, adr.getCountry());
                    ps.setBoolean(9, adr.isPrimaryAddress());

                    ps.execute();
                } catch (SQLException e) {
                    throw new DaoException("SQLException occurred in the addAddress():" + e.getMessage());

                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                        if (con != null) {
                            freeConnection(con);
                        }
                    } catch (SQLException e) {
                        throw new DaoException("SQLException occurred in the addAddress() final :" + e.getMessage());
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
     * deleteAddress method takes in address object and if it is not null then
     * deletes it by id from database.
     *
     * @param adr takes in address object to be deleted from the database.
     * @return true if the address information was deleted successfully, false
     * otherwise.
     * @throws DaoException if message "SQLException occurred in the
     * deleteAddress():" seen, error occurred by deleting address stage. If
     * message "SQLException occurred in the deleteAddress() final:" seen,
     * database is not accessible.
     */
    @Override
    public boolean deleteAddress(Address adr) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        if (adr != null) {

            try {
                con = this.getConnection();

                String query = "DELETE from ADDRESS where address_id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, adr.getAddressID());

                ps.execute();
            } catch (SQLException e) {
                throw new DaoException("SQLException occurred in the deleteAddress():" + e.getMessage());

            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    throw new DaoException("SQLException occurred in the deleteAddress() final:" + e.getMessage());
                }
            }
            return true;

        } else {
            return false;
        }
    }

    /**
     * updateAddress method takes in address object and updates an address.
     *
     * @param adr takes in address object to update an address.
     * @return true if the address information was updated successfully, false
     * otherwise.
     * @throws DaoException if message "SQLException occurred in the
     * updateAddress() method:" seen, error occurred by updating address stage.
     * If message "SQLException occurred in the updateAddress() method final:"
     * seen, database is not accessible.
     */
    @Override
    public boolean updateAddress(Address adr) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean updated = false;

        if (adr != null) {
            try {
                //get connection
                con = this.getConnection();
                //set auto commit to false for transaction
                con.setAutoCommit(false);
                //put query into prepared statement to protect our database from sql injection attacks
                String query = "UPDATE ADDRESS SET `address_line_1`=?, `address_line_2`=?,`city`=?, `county`=?, `postal_code`=? ,`country`=?, `primary_address`=? WHERE address_id = ?";
                ps = con.prepareStatement(query);
                //get all details from Address object
                ps.setString(1, adr.getAddress1());
                ps.setString(2, adr.getAddress2());
                ps.setString(3, adr.getCity());
                ps.setString(4, adr.getCounty());
                ps.setString(5, adr.getPostalCode());
                ps.setString(6, adr.getCountry());
                ps.setBoolean(7, adr.isPrimaryAddress());
                ps.setInt(8, adr.getAddressID());
                ps.executeUpdate();

                if (adr.isPrimaryAddress() == true) {
                    //when i change my pramary address i must set any other address to secondary because i can only have 1 primary address             
                    String query2 = "UPDATE address SET primary_address = CASE WHEN address_id=? THEN 1 ELSE 0 END WHERE user_id =?";
                    ps = con.prepareStatement(query2);
                    ps.setInt(1, adr.getAddressID());
                    ps.setInt(2, adr.getUserID());
                    ps.executeUpdate();
                }
                //commit transaction  
                con.commit();
                updated = true;
            } catch (SQLException e) {
                if (con != null) {
                    try {
                        //if something happens within transaction it will roll back
                        con.rollback();
                    } catch (SQLException ex) {
                        System.out.println("Exception occured in the ROLLBACK " + ex.getMessage());
                    }
                }
                throw new DaoException("SQLException occurred in the updateAddress() method:" + e.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    throw new DaoException("SQLException occurred in the updateAddress() method final:" + e.getMessage());
                }
            }
        }
        return updated;
    }

}
