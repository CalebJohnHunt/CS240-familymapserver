package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.PersonDAO;
import model.AuthToken;
import model.Person;
import request.GetFamilyRequest;
import result.GetFamilyResult;

import java.util.List;

/**
 * Service for /person
 */
public class GetFamilyService extends Service {

    /**
     * Calls the /person API.
     * @param request the request for the API.
     * @return the response from the API.
     */
    public GetFamilyResult getFamily(GetFamilyRequest request) throws DataAccessException {
        PersonDAO pDAO = new PersonDAO(db.getConnection());
        AuthTokenDAO atDAO = new AuthTokenDAO(db.getConnection());
        try {
            AuthToken authToken = atDAO.find(request.getAuthTokenID());
            if (authToken == null) {
                return new GetFamilyResult("Error: Bad AuthToken.");
            }

            List<Person> familyList = pDAO.findAssociatedPersons(authToken.getAssociatedUsername());
            Person[] familyArray = familyList.toArray(new Person[0]);

            return new GetFamilyResult(familyArray);
        } catch (DataAccessException e) {
            e.printStackTrace(); // TODO: Logger
            return new GetFamilyResult("Error: Server error.");
        } finally {
            db.closeConnection(false);
        }
    }
}
