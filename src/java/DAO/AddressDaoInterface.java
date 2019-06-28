package DAO;

import Business.Address;
import Exceptions.DaoException;

public interface AddressDaoInterface {

    /**
     * findAddressById interface takes address id and finds address by taken id
     * if address exist. If address not exist method will return null
     *
     * @param adrId address id to find address with presented id
     * @return address details if address with presented id exists in database
     * @throws DaoException if message "SQLException occurred in the
     * findAddressByID():" seen, address with presented id doesn't exist. If
     * message "SQLException occurred in the findAddressByID() final" seen,
     * database is not accessible
     */
    public Address findAddressById(int adrId) throws DaoException;

    /**
     * addAddress interface adds address to the database for a user
     *
     * @param adr takes in all address details and stores them to the database
     * @return true/false depend on address details stored or not
     * @throws DaoException if message "SQLException occurred in the
     * addAddress():" seen, error occurred by inserting address stage. If
     * message "SQLException occurred in the addAddress() final :" seen,
     * database is not accessible
     */
    public boolean addAddress(Address adr) throws DaoException;

    /**
     * deleteAddress interface takes in address object and if it is not null
     * then deletes it by id from database.
     *
     * @param adr takes in address object to be deleted from the database.
     * @return true if the address information was deleted successfully, false
     * otherwise.
     * @throws DaoException if message "SQLException occurred in the
     * deleteAddress():" seen, error occurred by deleting address stage. If
     * message "SQLException occurred in the deleteAddress() final:" seen,
     * database is not accessible.
     */
    public boolean deleteAddress(Address adr) throws DaoException;

    /**
     * updateAddress interface takes in address object and updates an address.
     *
     * @param adr takes in address object to update an address.
     * @return true if the address information was updated successfully, false
     * otherwise.
     * @throws DaoException if message "SQLException occurred in the
     * updateAddress() method:" seen, error occurred by updating address stage.
     * If message "SQLException occurred in the updateAddress() method final:"
     * seen, database is not accessible.
     */
    public boolean updateAddress(Address adr) throws DaoException;

}
