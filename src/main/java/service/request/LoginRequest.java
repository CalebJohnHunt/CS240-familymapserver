package service.request;


/**
 * Request to /user/login.
 */
public class LoginRequest {
    /**
     * The username of the user attempting to log in.
     */
    private String username;
    /**
     * The password of the user attempting to log in.
     */
    private String password;

    /**
     * Creates a LoginRequest.
     * @param username the username of the user attempting to log in.
     * @param password the password of the user attempting to log in.
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
