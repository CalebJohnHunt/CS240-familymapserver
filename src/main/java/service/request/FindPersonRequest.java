package service.request;

import model.AuthToken;

/**
 * Request to /person/[personID].
 */
public class FindPersonRequest {
    /**
     * The unique ID of the person to find.
     */
    private String personID;
    /**
     * The authToken of the user who made the request.
     */
    private AuthToken authToken;

    /**
     * Creates a FindPersonRequest.
     * @param personId the unique ID of the person to find.
     * @param authTokenID the authToken of the user who made the request.
     */
    public FindPersonRequest(String personId, AuthToken authTokenID) {
        this.personID = personId;
        this.authToken = authTokenID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}
