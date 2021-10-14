package service.request;

import model.AuthToken;

/**
 * Request to /event/[eventID].
 */
public class FindEventRequest {
    /**
     * The unique ID of the event to find.
     */
    private String eventID;
    /**
     * The authToken of the user who made the request.
     */
    private AuthToken authToken;

    /**
     * Creates a FindEventRequest.
     * @param eventID the unique ID of the event to find.
     * @param authToken the authToken of the user who made the request.
     */
    public FindEventRequest(String eventID, AuthToken authToken) {
        this.eventID = eventID;
        this.authToken = authToken;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}
