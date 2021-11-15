package request;

/**
 * Request to /person/[personID].
 */
public class FindPersonRequest {
    /**
     * The unique ID of the person to find.
     */
    private String personID;
    /**
     * The AuthToken ID for the user who made the request.
     */
    private String authTokenID;

    /**
     * Creates a FindPersonRequest.
     * @param personId the unique ID of the person to find.
     * @param authTokenID the authTokenID for the user who made the request.
     */
    public FindPersonRequest(String personId, String authTokenID) {
        this.personID = personId;
        this.authTokenID = authTokenID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAuthTokenID() {
        return authTokenID;
    }

    public void setAuthTokenID(String authTokenID) {
        this.authTokenID = authTokenID;
    }
}
