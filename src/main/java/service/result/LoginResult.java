package service.result;

/**
 * Result of /user/login.
 */
public class LoginResult extends Result {
    /**
     * The unique ID for the new authToken for this login.
     */
    private String authTokenID;
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
    public static LoginResult createSuccessful(String authTokenId, String username, String personId) {
        LoginResult inst = new LoginResult();
        inst.setAuthTokenID(authTokenId);
        inst.setUsername(username);
        inst.setPersonID(personId);
        inst.setSuccess(true);

        return inst;
    }

    public String getAuthTokenID() {
        return authTokenID;
    }

    public void setAuthTokenID(String authTokenID) {
        this.authTokenID = authTokenID;
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
