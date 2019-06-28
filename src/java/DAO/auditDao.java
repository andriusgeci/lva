/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Audit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Exceptions.DaoException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class to deal with user activity(logs). Class auditDao implements
 * interface of name auditDaoInterface.
 */
public class auditDao extends Dao implements auditDaoInterface {

    /**
     * This method to get user activity(logs) information.
     *
     * @return an ArrayList of descriptions(logs).
     * @throws DaoException If message: "SQLException occurred in the
     * getDescriptions()" seen, error occurred by searching for user
     * descriptions stage. If message: "SQLException occurred in the
     * getDescriptions() final" seen, database is not accessible.
     */
    @Override
    public ArrayList<Audit> getDescriptions() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Audit> descriptions = new ArrayList<Audit>();

        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM audit";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) {
                int auditID = rs.getInt("audit_id");
                String descrip = rs.getString("description");

                Audit a = new Audit(auditID, descrip);
                descriptions.add(a);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException occurred in the getDescriptions() " + e.getMessage());
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
                throw new DaoException("SQLException occurred in the getDescriptions() final" + e.getMessage());
            }
        }
        return descriptions;

    }

    /**
     * This method to change bad language.
     *
     * @param text To be changed in the database
     * @return The changed text.
     * @throws Exceptions.DaoException
     */
    @Override
    public String censoredText(String text) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String censoredText = null;
        try {
            con = this.getConnection();

            String query = "{call splitter(?, ?, ?)}";
            cs = con.prepareCall(query);
            cs.setString(1, text);
            cs.setString(2, " ");
            cs.setString(3, "@changedText");
            cs.executeQuery();

            censoredText = cs.getString("changedText");

        } catch (SQLException e) {

            throw new DaoException("SQLException occured in the censoredText():" + e.getMessage());
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
                throw new DaoException("SQLException occured in the censoredText() final" + e.getMessage());
            }
        }
        return censoredText;
    }

}
