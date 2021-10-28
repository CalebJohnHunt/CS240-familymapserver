package service;

import dao.*;
import model.Person;
import model.User;
import service.request.RegisterRequest;
import service.result.RegisterResult;
import service.utility.AuthTokenGenerator;
import service.utility.FamilyGenerator;
import service.utility.IDGenerator;

/**
 * Service for /user/register.
 */
public class RegisterService extends Service {
    /**
     * Registers a new user. Inserts a new user into the database, generates their personID and an AuthToken.
     * @param request the request to the API.
     * @return the response from the API.
     */
    public RegisterResult register(RegisterRequest request) throws DataAccessException {
        UserDAO uDao = new UserDAO(db.getConnection()); // starts transaction
        User newUser = new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstName(),
                request.getLastName(), request.getGender(), IDGenerator.generate());
        try {
            uDao.insert(newUser);
            String authTokenID = AuthTokenGenerator.generate(newUser.getUsername(), db.getConnection());

            // Creates fake data for and inserts the user's person
            Person person = new Person(newUser.getPersonID(), newUser.getUsername(), newUser.getFirstName(), newUser.getLastName(),
                    newUser.getGender(), null, null, null);
            FamilyGenerator.run(person, db.getConnection());

            db.closeConnection(true);
            return new RegisterResult(authTokenID, newUser.getUsername(), newUser.getPersonID());

        } catch (DataAccessException e) {
            db.closeConnection(false);
            return new RegisterResult("Couldn't register user.");
        }

    }
}
