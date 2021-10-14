package dao;

import model.AuthToken;

import java.sql.Connection;

/**
 * Accesses data about AuthTokens from the database.
 */
public class AuthTokenDAO {
    /**
     * The connection to the database.
     */
    private final Connection conn;

    /**
     * Creates a DAO with a connection to the database
     * @param conn the connection to the database.
     */
    public AuthTokenDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts a new AuthToken into the database.
     * @param authToken AuthToken to insert.
     * @throws DataAccessException
     */
    public void insert(AuthToken authToken) throws DataAccessException {}

    /**
     * Finds and returns an AuthToken from the database from an AuthToken ID
     * @param authTokenID ID of AuthToken to find.
     * @return the authToken with the matching ID or null if no such authToken exists
     * @throws DataAccessException
     */
    public AuthToken find(String authTokenID) throws DataAccessException { return null; }

    /**
     * Removes an AuthToken from the database from an AuthToken ID.
     * @param authTokenID ID of AuthToken to remove.
     * @throws DataAccessException
     */
    public void delete(String authTokenID) throws DataAccessException {}

    /**
     * Removes all AuthTokens from the database.
     * @throws DataAccessException
     */
    public void clearTable() throws DataAccessException {}

}
