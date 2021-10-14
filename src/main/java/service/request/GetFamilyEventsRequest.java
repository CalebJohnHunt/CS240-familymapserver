package service.request;

import model.AuthToken;

/**
 * Request to /event.
 */
public class GetFamilyEventsRequest {
    /**
     * The authToken of the user who made the request.
     */
    private AuthToken authToken;

    /**
     * Creates a GetFamilyEventsRequest.
     * @param authToken the authToken of the user who made the request.
     */
    public GetFamilyEventsRequest(AuthToken authToken) {
        this.authToken = authToken;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}
