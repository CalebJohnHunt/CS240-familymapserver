package result;

/**
 * Result for /user/register.
 */
public class RegisterResult extends Result {
    /**
     * The unique ID for the new authToken created when the user registered.
     */
    private String authtoken;
    /**
     * The username of the newly registered user.
     */
    private String username;
    /**
     * The unique ID of the person belonging to the new user.
     */
    private String personID;

    /**
     * Creates a successful RegisterResult.
     * @param authTokenId the unique ID for the new authToken created when the user registered.
     * @param username the username of the newly registered user.
     * @param personId the unique ID of the person belonging to the new user.
     */
    public RegisterResult(String authTokenId, String username, String personId) {
        setAuthtoken(authTokenId);
        setUsername(username);
        setPersonID(personId);
        setSuccess(true);
    }

    /**
     * Creates a failed RegisterResult.
     * @param message the reason the call to the API failed.
     */
    public RegisterResult(String message) {
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
