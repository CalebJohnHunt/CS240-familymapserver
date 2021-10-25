package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import model.AuthToken;

import java.sql.Connection;

/**
 * Authorizes AuthTokens.
 */
public class AuthorizeToken {
    /**
     * Authorizes AuthToken.
     * @param authTokenID the ID of the AuthToken to check.
     * @param conn the connection to the database.
     * @return whether the AuthToken is in the database.
     */
    public static boolean authorize(String authTokenID, Connection conn) {
        AuthTokenDAO atDAO = new AuthTokenDAO(conn);
        try {
            AuthToken foundAuthToken = atDAO.find(authTokenID);
            return foundAuthToken != null;
        } catch (DataAccessException e) {
            return false;
        }
    }
}
