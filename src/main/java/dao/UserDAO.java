package dao;

import model.User;

import java.sql.Connection;

/**
 * Accesses data about users from the database.
 */
public class UserDAO {
    /**
     * The connection to the database.
     */
    private final Connection conn;

    /**
     * Creates a user DAO.
     * @param conn the connection to the database.
     */
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds a user to the database.
     * @param user the user to add to the database.
     * @throws DataAccessException
     */
    public void insert(User user) throws DataAccessException {}

    /**
     * Fills in fake information about a user back to 4 generations.
     * @param username the username of the user
     */
    public void populateData(String username) {}

    /**
     * Fills in fake information about a user to a specified number of generations.
     * @param username the username of the user.
     * @param generations the number of generations to go back.
     */
    public void populateData(String username, int generations) {}

    /**
     * Validates that the username and password go together and are in the database.
     * @param username the username of the user.
     * @param password the password of the user.
     * @return whether the user's username and password match those in the database.
     * @throws DataAccessException
     */
    public boolean validate(String username, String password) throws DataAccessException { return false; }

    /**
     * Finds a specified user in the database.
     * @param username the username of the user.
     * @return the user matching the username
     * @throws DataAccessException
     */
    public User find(String username) throws DataAccessException { return null; }

    /**
     * Removes a specified user from the database.
     * @param username the username of the user to remove
     * @throws DataAccessException
     */
    public void delete(String username) throws DataAccessException {}

    /**
     * Removes all users from the database.
     * @throws DataAccessException
     */
    public void clearTable() throws DataAccessException {}

}
