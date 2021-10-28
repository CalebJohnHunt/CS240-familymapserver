package service.request;

import model.AuthToken;

/**
 * Request to /event.
 */
public class GetFamilyEventsRequest {
    /**
     * The authToken ID for the user who made the request.
     */
    private String authTokenID;

    /**
     * Creates a GetFamilyEventsRequest.
     * @param authTokenID the authToken ID for the user who made the request.
     */
    public GetFamilyEventsRequest(String authTokenID) {
        this.authTokenID = authTokenID;
    }

    public String getAuthTokenID() {
        return authTokenID;
    }

    public void setAuthTokenID(String authTokenID) {
        this.authTokenID = authTokenID;
    }
}
