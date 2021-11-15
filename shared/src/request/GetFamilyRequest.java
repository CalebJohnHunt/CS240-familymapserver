package request;

/**
 * Request for /person.
 */
public class GetFamilyRequest {
    /**
     * The id of the authToken for the user who made the request.
     */
    private String authTokenID;

    /**
     * Creates a GetFamilyRequest.
     * @param authToken the authToken of the user who made the request.
     */
    public GetFamilyRequest(String authToken) {
        this.authTokenID = authToken;
    }

    public String getAuthTokenID() {
        return authTokenID;
    }

    public void setAuthTokenID(String authTokenID) {
        this.authTokenID = authTokenID;
    }
}
