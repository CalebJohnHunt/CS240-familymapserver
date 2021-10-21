package service;

import dao.*;
import model.AuthToken;
import model.Person;
import model.User;
import service.request.RegisterRequest;
import service.result.RegisterResult;
import utility.IDGenerator;

// TODO: Fill the user with fake data (i.e. give them parents and events and stuff) - Make helper class

/**
 * Service for /user/register.
 */
public class RegisterService {
    private final Database db = new Database();

    /**
     * Registers a new user. Inserts a new user into the database, generates their personID and an AuthToken.
     * @param request the request to the API.
     * @return the response from the API.
     */
    public RegisterResult register(RegisterRequest request) throws DataAccessException {
        UserDAO uDao = new UserDAO(db.getConnection()); // starts transaction
        User newUser = new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstName(),
                request.getLastName(), request.getGender(), IDGenerator.generateID());
        try {
            uDao.insert(newUser);
            // Make authToken for the user
            String authTokenID = insertAuthTokenForNewUser(newUser.getUsername());
            insertPersonForNewUser(newUser);
            db.closeConnection(true);
            return new RegisterResult(authTokenID, newUser.getUsername(), newUser.getPersonID());

        } catch (DataAccessException e) {
            db.closeConnection(false);
            return new RegisterResult("Couldn't register user.");
        }

    }

    /**
     * Create and insert an AuthToken for the user getting registered.
     * @param username the username of the user getting registered
     * @return the new authToken's ID
     */
    private String insertAuthTokenForNewUser(String username) throws DataAccessException {
        AuthTokenDAO atDAO = new AuthTokenDAO(db.getConnection());
        String authTokenID = IDGenerator.generateID();
        atDAO.insert(new AuthToken(authTokenID, username));
        return authTokenID;
    }

    private void insertPersonForNewUser(User newUser) throws DataAccessException {
        PersonDAO pDAO = new PersonDAO(db.getConnection());
        // TODO: Probably don't want null for father, mother, spouse. But that's the same thing as above: generate data
        pDAO.insert(new Person(newUser.getPersonID(), newUser.getUsername(), newUser.getFirstName(), newUser.getLastName(),
                newUser.getGender(), null, null, null));
    }
}
