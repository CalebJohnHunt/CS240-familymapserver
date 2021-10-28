package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.EventDAO;
import dao.PersonDAO;
import model.AuthToken;
import model.Event;
import model.Person;
import service.request.GetFamilyEventsRequest;
import service.result.GetFamilyEventsResult;
import service.result.GetFamilyResult;

import java.util.List;

/**
 * Service for /event
 */
public class GetFamilyEventsService extends Service {

    /**
     * Calls the /event API.
     * @param request the request to the API.
     * @return the response from the API.
     */
    public GetFamilyEventsResult getFamilyEvents(GetFamilyEventsRequest request) throws DataAccessException {
        EventDAO eDAO = new EventDAO(db.getConnection());
        AuthTokenDAO atDAO = new AuthTokenDAO(db.getConnection());
        try {
            AuthToken authToken = atDAO.find(request.getAuthTokenID());
            if (authToken == null) {
                return new GetFamilyEventsResult("Error: Bad authToken.");
            }

            List<Event> eventsList = eDAO.findEventsForUserFamilyList(authToken.getAssociatedUsername());
            Event[] eventsArray = eventsList.toArray(new Event[0]);

            return new GetFamilyEventsResult(eventsArray);
        } catch (DataAccessException e) {
            e.printStackTrace(); // TODO: Logger
            return new GetFamilyEventsResult("Error: Server error.");
        } finally {
            db.closeConnection(false);
        }
    }
}
