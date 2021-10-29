package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.AuthToken;
import model.User;
import service.utility.IDGenerator;

/**
 *
 */
public final class TestUtil {
    private TestUtil() {}

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
