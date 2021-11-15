package service;

import dao.*;
import model.Person;
import model.User;
import request.FillRequest;
import result.FillResult;
import service.utility.FamilyGenerator;

/**
 * Service for /fill/[username]/{generations}.
 */
public class FillService extends Service {
    /**
     * Calls the /fill/[username]/{generations}.
     * @param request the request to the API.
     * @return the response from the API.
     */
    public FillResult fill(FillRequest request) throws DataAccessException {
        PersonDAO pDAO = new PersonDAO(db.getConnection());
        EventDAO eDAO  = new EventDAO(db.getConnection());
        AuthTokenDAO atDAO = new AuthTokenDAO(db.getConnection());
        UserDAO uDAO = new UserDAO(db.getConnection());
        try {

            User user = uDAO.find(request.getUsername());
            if (user == null) {
                db.closeConnection(false);
                return new FillResult(false, "Error: Username not found.");
            }

            pDAO.deleteUserPersons(request.getUsername());

            eDAO.deleteUserEvents(request.getUsername());

            atDAO.deleteUserAuthTokens(request.getUsername());

            int[] itemsAdded = FamilyGenerator.run(new Person(user.getPersonID(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getGender(), null, null, null),
                    request.getGenerations(), db.getConnection());

            db.closeConnection(true);
            return new FillResult(true, "Successfully added " + itemsAdded[0]
                    + " persons and " + itemsAdded[1] + " events to the database");
        } catch (DataAccessException e) {
            e.printStackTrace(); // TODO: Logger
            db.closeConnection(false);
            return new FillResult(false, "Error: Server error.");
        }
    }
}
