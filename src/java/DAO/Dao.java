package DAO;

import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Class Dao connects to the database
 *
 */
public class Dao {

    private DataSource datasource;

    /**
     * Add in constructor that takes in the source of database connections and
     * store it.
     *
     * @param myDataSource The source of database.
     */
    public Dao(DataSource myDataSource) {
        this.datasource = myDataSource;
    }

    /**
     * Constructor for use by pooled connections
     */
    public Dao() {

        String DATASOURCE_CONTEXT = "jdbc/LVA_Movies";
        try {
            Context initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/" + DATASOURCE_CONTEXT);
            if (ds != null) {
                datasource = ds;
            } else {
                System.out.println(("Failed to lookup datasource."));
            }
        } catch (NamingException ex) {
            System.out.println("Cannot get connection: " + ex);
        }
    }

    /**
     * Pooled version of getConnection
     *
     * @return connection if connection successful.
     * @throws DaoException abnormal termination
     */
    public Connection getConnection() throws DaoException {
        Connection conn = null;
        try {
            if (datasource != null) {
                conn = datasource.getConnection();
            } else {
                System.out.println(("Failed to lookup datasource."));
            }
        } catch (SQLException ex2) {
            System.out.println("Connection failed " + ex2.getMessage());
            System.exit(2); // 
        }
        return conn;
    }

    /**
     * This method freeConnection to terminate connection.
     *
     * @param con Connection to be terminated.
     * @throws DaoException This exception occur if connection not terminated.
     */
    public void freeConnection(Connection con) throws DaoException {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }
    }
}
