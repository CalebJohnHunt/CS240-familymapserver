package service.request;

import model.AuthToken;

/**
 * Request for /person.
 */
public class GetFamilyRequest {
    /**
     * The authToken of the user who made the request.
     */
    private AuthToken authToken;

    /**
     * Creates a GetFamilyRequest.
     * @param authToken the authToken of the user who made the request.
     */
    public GetFamilyRequest(AuthToken authToken) {
        this.authToken = authToken;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}
