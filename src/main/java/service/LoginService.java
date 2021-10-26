package service;

import dao.DataAccessException;
import dao.UserDAO;
import model.User;
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
        String personID = uDao.validate(request.getUsername(),request.getPassword());
        try {
            if (personID != null) {
                String authTokenID = AuthTokenGenerator.generate(request.getUsername(), db.getConnection());
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
