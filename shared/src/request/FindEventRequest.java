package request;

/**
 * Request to /event/[eventID].
 */
public class FindEventRequest {
    /**
     * The unique ID of the event to find.
     */
    private String eventID;
    /**
     * The authToken ID for the user who made the request.
     */
    private String authTokenID;

    /**
     * Creates a FindEventRequest.
     * @param eventID the unique ID of the event to find.
     * @param authTokenID the authToken ID for the user who made the request.
     */
    public FindEventRequest(String eventID, String authTokenID) {
        this.eventID = eventID;
        this.authTokenID = authTokenID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getAuthTokenID() {
        return authTokenID;
    }

    public void setAuthTokenID(String authToken) {
        this.authTokenID = authToken;
    }
}
