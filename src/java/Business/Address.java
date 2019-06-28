package Business;

import java.util.Objects;

public class Address {

    // Declare Variables
    private int addressID;
    private int userID;
    private String address1;
    private String address2;
    private String city;
    private String county;
    private String postalCode;
    private String country;
    private boolean primaryAddress;

    /**
     * Parameterized Constructor
     *
     * @param addressID takes address id
     * @param userID takes user id
     * @param address1 takes address line 1
     * @param address2 takes address line 1
     * @param city takes city
     * @param county takes county
     * @param postalCode takes postal code
     * @param country takes country
     * @param primaryAddress true or false after all variables passed then
     * creates new User
     */
    public Address(int addressID, int userID, String address1, String address2, String city, String county, String postalCode, String country, boolean primaryAddress) {
        this.addressID = addressID;
        this.userID = userID;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.county = county;
        this.postalCode = postalCode;
        this.country = country;
        this.primaryAddress = primaryAddress;
    }

    /**
     * Parameterized Constructor
     *
     * @param userID takes user id
     * @param address1 takes address line 1
     * @param address2 takes address line 1
     * @param city takes city
     * @param county takes county
     * @param postalCode takes postal code
     * @param country takes country
     * @param primaryAddress true or false after all variables passed then
     * creates new User
     */
    public Address(int userID, String address1, String address2, String city, String county, String postalCode, String country, boolean primaryAddress) {
        this.userID = userID;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.county = county;
        this.postalCode = postalCode;
        this.country = country;
        this.primaryAddress = primaryAddress;
    }

    /**
     * Default Constructor
     */
    public Address() {
        this.addressID = 123;
        this.userID = 321;
        this.address1 = "address1";
        this.address2 = "address2";
        this.city = "city";
        this.county = "county";
        this.postalCode = "postal code";
        this.country = "country";
        this.primaryAddress = true;
    }

    //Getter and Setter Methods
    /**
     *
     * @return address ID
     */
    public int getAddressID() {
        return addressID;
    }

    /**
     * Sets new address
     *
     * @param addressID new address ID
     */
    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    /**
     *
     * @return User ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets new User
     *
     * @param userID new User ID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     *
     * @return address line 1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     *
     * @param address1 sets address line 1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     *
     * @return address line 2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     *
     * @param address2 new address line 2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return County
     */
    public String getCounty() {
        return county;
    }

    /**
     *
     * @param county new county
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     *
     * @return Postal Code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode new Postal Code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return Country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country new Country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return return primary address
     */
    public boolean isPrimaryAddress() {
        return primaryAddress;
    }

    /**
     *
     * @param primaryAddress true if primary else false primary address
     */
    public void setPrimaryAddress(boolean primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    /**
     * Calculate the hashcode of this object, based only on its addressID,
     * userID, address1, address2, city, county, postalCode, country,
     * primaryAddress.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.addressID;
        hash = 79 * hash + this.userID;
        hash = 79 * hash + Objects.hashCode(this.address1);
        hash = 79 * hash + Objects.hashCode(this.address2);
        hash = 79 * hash + Objects.hashCode(this.city);
        hash = 79 * hash + Objects.hashCode(this.county);
        hash = 79 * hash + Objects.hashCode(this.postalCode);
        hash = 79 * hash + Objects.hashCode(this.country);
        hash = 79 * hash + (this.primaryAddress ? 1 : 0);
        return hash;
    }

    /**
     *
     * @param obj other Object, usually a Genre, to compare with.
     * @return true if the objects are of identical
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
        final Address other = (Address) obj;
        if (this.addressID != other.addressID) {
            return false;
        }
        if (this.userID != other.userID) {
            return false;
        }
        if (this.primaryAddress != other.primaryAddress) {
            return false;
        }
        if (!Objects.equals(this.address1, other.address1)) {
            return false;
        }
        if (!Objects.equals(this.address2, other.address2)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.county, other.county)) {
            return false;
        }
        if (!Objects.equals(this.postalCode, other.postalCode)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return a string representation of Address object
     */
    @Override
    public String toString() {
        return "Address{" + "addressID=" + addressID + ", userID=" + userID + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", county=" + county + ", postalCode=" + postalCode + ", country=" + country + ", primaryAddress=" + primaryAddress + '}';
    }
}
