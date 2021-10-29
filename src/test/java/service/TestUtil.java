package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.AuthToken;
import model.User;
import service.utility.IDGenerator;

/**
 * Utility class for the unit tests.
 */
public final class TestUtil {
    private TestUtil() {}

    /**
     * Registers a user by only inserting the user, generating an AuthToken, and inserting the token.
     * @param user the user to register.
     * @return the AuthToken going along with the newly registered user.
     */
    public static AuthToken registerUser(User user) throws DataAccessException {
        Database db = new Database();
        UserDAO uDAO = new UserDAO(db.getConnection());
        AuthTokenDAO atDAO = new AuthTokenDAO(db.getConnection());
        uDAO.insert(user);
        AuthToken authToken = new AuthToken(IDGenerator.generate(), user.getUsername());
        atDAO.insert(authToken);
        db.closeConnection(true);
        return authToken;
    }
}
