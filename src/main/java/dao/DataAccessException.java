package dao;

/**
 * Unexpected failure to access data from the database
 */
public class DataAccessException extends Exception {
    /**
     * Creates a DataAccessException
     * @param message the cause of the exception.
     */
    DataAccessException(String message)
    {
        super(message);
    }

    DataAccessException()
    {
        super();
    }
}
