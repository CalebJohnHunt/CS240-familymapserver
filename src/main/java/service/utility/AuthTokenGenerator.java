package service.utility;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import model.AuthToken;

import java.sql.Connection;

/**
 * Helper to generate AuthTokens
 */
public class AuthTokenGenerator {
    /**
     * Generates and inserts an AuthToken for a username. It's dumb; we don't check for duplicate tokens or logins.
     * @param username username of the user to generate a token for.
     * @param conn connection to the database
     * @return ID of the AuthToken for the username.
     */
    public static String generate(String username, Connection conn) throws DataAccessException {
        AuthTokenDAO atDAO = new AuthTokenDAO(conn);
        String tokenID = IDGenerator.generate();
        atDAO.insert(new AuthToken(tokenID, username));
        return tokenID;
    }
}
