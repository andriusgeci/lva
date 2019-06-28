package DAO;

import Business.Audit;
import Exceptions.DaoException;
import java.util.ArrayList;

public interface auditDaoInterface {

    /**
     * This method to get user activity(logs) information.
     *
     * @return an ArrayList of descriptions(logs).
     * @throws DaoException If message: "SQLException occurred in the
     * getDescriptions()" seen, error occurred by searching for user
     * descriptions stage. If message: "SQLException occurred in the
     * getDescriptions() final" seen, database is not accessible.
     */
    public ArrayList<Audit> getDescriptions() throws DaoException;

    /**
     * This method to change bad language.
     *
     * @param text To be changed in the database
     * @return The changed text.
     * @throws Exceptions.DaoException
     */
    public String censoredText(String text) throws DaoException;

}
