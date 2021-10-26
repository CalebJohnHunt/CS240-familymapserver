package service;

import dao.DataAccessException;
import dao.PersonDAO;
import model.Person;
import service.request.FindPersonRequest;
import service.result.FindPersonResult;
import service.utility.AuthorizeToken;

/**
 * Service for /person/[personID].
 */
public class FindPersonService extends Service {

    /**
     * Calls the /person/[personID] API.
     * @param request the request to the API.
     * @return the response from the API.
     */
    public FindPersonResult find(FindPersonRequest request) throws DataAccessException {
        PersonDAO pDao = new PersonDAO(db.getConnection());
        try {
            if (!AuthorizeToken.authorize(request.getAuthTokenID(), db.getConnection())) {
                db.closeConnection(false);
                return new FindPersonResult("Error: Bad Auth Token.");
            }
            Person foundPerson = pDao.find(request.getPersonID());
            if (foundPerson == null) {
                return new FindPersonResult("Error: Person not found.");
            }
            db.closeConnection(false); // Don't commit since we didn't do anything.
            return new FindPersonResult(foundPerson.getPersonID(), foundPerson.getAssociatedUsername(), foundPerson.getFirstName(),
                    foundPerson.getLastName(), foundPerson.getGender(), foundPerson.getFatherID(), foundPerson.getMotherID(), foundPerson.getSpouseID());
        } catch (DataAccessException e) {
            db.closeConnection(false);
            return new FindPersonResult("Error: Server error.");
        }
    }
}
