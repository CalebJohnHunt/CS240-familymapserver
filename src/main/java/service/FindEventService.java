package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.EventDAO;
import dao.PersonDAO;
import handler.HttpUtil;
import model.AuthToken;
import model.Event;
import service.request.FindEventRequest;
import service.result.FindEventResult;

import java.util.Set;

/**
 * Service for /event/[eventID].
 */
public class FindEventService extends Service {

    /**
     * Calls the /event[eventID] API.
     * @param request the request to the API.
     * @return the response from the API.
     */
    public FindEventResult find(FindEventRequest request) throws DataAccessException {
        EventDAO eDAO = new EventDAO(db.getConnection());
        PersonDAO pDao = new PersonDAO(db.getConnection());
        AuthTokenDAO atDAO = new AuthTokenDAO(db.getConnection());

        try {
            AuthToken authToken = atDAO.find(request.getAuthTokenID());
            if (authToken == null) {
                return new FindEventResult("Error: Bad AuthToken.");
            }

            Event foundEvent = eDAO.find(request.getEventID());
            if (foundEvent == null) {
                return new FindEventResult("Error: Event not found.");
            }

            Set<Event> familyEvents = eDAO.findEventsForUserFamilySet(authToken.getAssociatedUsername());
            if (!familyEvents.contains(foundEvent)) {
                return new FindEventResult("Error: Related event not found.");
            }

            return new FindEventResult(foundEvent);

        } catch (DataAccessException e) {
            e.printStackTrace(); // TODO: Logger
            return new FindEventResult("Error: Server error.");
        } finally {
            db.closeConnection(false);
        }
    }
}
