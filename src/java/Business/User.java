package Business;

import java.sql.Date;
import java.util.Objects;

public class User {

    //Declare Variables
    private int userId;
    private String firstName;
    private String lastName;
    private Date DOB;
    private String phone;
    private String password;
    private String email;
    private boolean admin;

    /**
     * Default Constructor
     */
    public User() {
    }

    /**
     * Parameterized constructor
     *
     * @param userId User ID
     * @param firstName User First Name
     * @param lastName User Last Name
     * @param DOB User DOB
     * @param phone User Phone
     * @param password User Password
     * @param email User Email
     * @param admin User Admin Rights
     */
    public User(int userId, String firstName, String lastName, Date DOB, String phone, String password, String email, boolean admin) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }

    /**
     * Parameterized constructor without User Phone, DOB, User ID
     *
     * @param firstName User First Name
     * @param lastName User Last Name
     * @param password User Password
     * @param email User Email
     * @param admin User Admin Rights
     */
    public User(String firstName, String lastName, String password, String email, boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }

    /**
     * Parameterized constructor without User Phone, DOB, Password
     *
     * @param userId User ID
     * @param firstName User First Name
     * @param lastName User Last Name
     * @param email User Email
     * @param admin User Admin Rights
     */
    public User(int userId, String firstName, String lastName, String email, boolean admin) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.admin = admin;
    }

    /**
     * Parameterized constructor for simple register form without USer ID
     *
     * @param firstName User First Name
     * @param lastName User Last Name
     * @param password User Password
     * @param email User Email
     */
    public User(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    /**
     * Parameterized constructor for simple register form
     *
     * @param userId User ID
     * @param firstName User First Name
     * @param lastName User Last Name
     * @param password User Password
     * @param email User Email
     */
    public User(int userId, String firstName, String lastName, String password, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    /**
     * Parameterized constructor for forgot password form
     *
     * @param userId User ID
     * @param firstName User First Name
     * @param lastName User Last Name
     * @param password User Password
     * @param email User Email
     * @param admin User Admin Rights
     */
    public User(int userId, String firstName, String lastName, String password, String email, boolean admin) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }

    /**
     * Parameterized constructor for order new user
     *
     * @param firstName User First Name
     * @param lastName User Last Name
     * @param phone User Phone
     * @param password User Password
     * @param email User Email
     */
    public User(String firstName, String lastName, String phone, String password, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
        this.email = email;
    }

    /**
     * Getter Method
     *
     * @return User ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter Method
     *
     * @param userId User ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getter Method
     *
     * @return First Name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter Method
     *
     * @param firstName First Name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter Method
     *
     * @return Last Name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter Method
     *
     * @param lastName Last Name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter Method
     *
     * @return User DOB
     */
    public Date getDOB() {
        return DOB;
    }

    /**
     * Setter Method
     *
     * @param DOB User DOB
     */
    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    /**
     * Getter Method
     *
     * @return Phone Number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter Method
     *
     * @param phone Phone Number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter Method
     *
     * @return User Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter Method
     *
     * @param password User Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter Method
     *
     * @return User Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter Method
     *
     * @param email User Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter Method
     *
     * @return User Admin Rights
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Setter Method
     *
     * @param admin User Admin Rights
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Calculate the hashcode of this object, based only on its email
     *
     * @return the hashcode for the User object
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.email);
        return hash;
    }

    /**
     *
     * @param obj other Object, usually a User, to compare with.
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
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.admin != other.admin) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.DOB, other.DOB)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return a string representation of User object
     */
    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", DOB=" + DOB + ", phone=" + phone + ", password=" + password + ", email=" + email + ", admin=" + admin + '}';
    }
}
