package service;

import dao.DataAccessException;
import service.request.FindPersonRequest;
import service.result.FindPersonResult;

/**
 * Service for /person/[personID].
 */
public class FindPersonService {

    /**
     * Calls the /person/[personID] API.
     * @param request the request to the API.
     * @return the response from the API.
     */
    public FindPersonResult find(FindPersonRequest request) throws DataAccessException {
        return null;
    }
}
