package service.result;

/**
 * Result for /user/register.
 */
public class RegisterResult extends Result {
    /**
     * The unique ID for the new authToken created when the user registered.
     */
    private String authTokenID;
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
     * @return a successful RegisterResult.
     */
    public static RegisterResult createSuccessful(String authTokenId, String username, String personId) {
        RegisterResult inst = new RegisterResult();
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
