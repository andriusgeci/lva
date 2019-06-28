package Exceptions;

import java.sql.SQLException;

public class DaoException extends SQLException {

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }
}
