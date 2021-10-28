package service;

import dao.DataAccessException;
import dao.PersonDAO;
import dao.UserDAO;
import service.request.LoginRequest;
import service.result.LoginResult;
import service.utility.AuthTokenGenerator;

/**
 * Service for /user/login.
 */
public class LoginService extends Service {
    /**
     * Calls the /user/login API.
     * @param request the request to the API.
     * @return the response from the API.
     */
    public LoginResult login(LoginRequest request) throws DataAccessException {
        UserDAO uDao = new UserDAO(db.getConnection());
        boolean successfulLogin = uDao.validate(request.getUsername(),request.getPassword());
        try {
            if (successfulLogin) {
                String authTokenID = AuthTokenGenerator.generate(request.getUsername(), db.getConnection());
                String personID = new PersonDAO(db.getConnection()).getPersonIDFromUsername(request.getUsername());
                db.closeConnection(true);
                return new LoginResult(authTokenID, request.getUsername(), personID);
            }
            db.closeConnection(false);
            return new LoginResult("Error: Username or password could not be found.");
        }
        catch (DataAccessException e){
                db.closeConnection(false);
                return new LoginResult("Error: Server error");
            }
    }
}
