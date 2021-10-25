package service.result;

/**
 * Result of /user/login.
 */
public class LoginResult extends Result {
    /**
     * The unique ID for the new authToken for this login.
     */
    private String authtoken;
    /**
     * The username of the user who just logged in.
     */
    private String username;
    /**
     * The ID of the person belonging to the user who just logged in.
     */
    private String personID;

    /**
     * Creates a successful LoginResult.
     * @param authTokenId the unique ID for the new authToken for this login.
     * @param username the username of the user who just logged in.
     * @param personId the ID of the person belonging to the user who just logged in.
     * @return a successful LoginResult.
     */
    public LoginResult(String authTokenId, String username, String personId) {
        setAuthtoken(authTokenId);
        setUsername(username);
        setPersonID(personId);
        setSuccess(true);
    }

    /**
     * Creates a failed LoginResult.
     * @param message the reason the call to the API failed.
     */
    public LoginResult(String message) {
        setMessage(message);
        setSuccess(false);
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
