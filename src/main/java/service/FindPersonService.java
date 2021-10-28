package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.PersonDAO;
import model.AuthToken;
import model.Person;
import service.request.FindPersonRequest;
import service.result.FindPersonResult;

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
        System.out.println("FindPerson");
        PersonDAO pDao = new PersonDAO(db.getConnection());
        AuthTokenDAO atDAO = new AuthTokenDAO(db.getConnection());
        try {
            AuthToken authToken = atDAO.find(request.getAuthTokenID());
            if (authToken == null) {
                return new FindPersonResult("Error: Bad Auth Token.");
            }

            Person foundPerson = pDao.find(request.getPersonID());
            if (foundPerson == null) {
                return new FindPersonResult("Error: Person not found.");
            }

            if (!foundPerson.getAssociatedUsername().equals(authToken.getAssociatedUsername())) {
                return new FindPersonResult("Error: Related person not found.");
            }

            return new FindPersonResult(foundPerson.getPersonID(), foundPerson.getAssociatedUsername(), foundPerson.getFirstName(),
                    foundPerson.getLastName(), foundPerson.getGender(), foundPerson.getFatherID(), foundPerson.getMotherID(), foundPerson.getSpouseID());
        } catch (DataAccessException e) {
            return new FindPersonResult("Error: Server error.");
        } finally {
            // We never change the database, so there's no need to ever commit!
            db.closeConnection(false);
        }
    }
}
