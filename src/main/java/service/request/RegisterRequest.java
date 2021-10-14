package service.request;

/**
 * Request to /user/register.
 */
public class RegisterRequest {
    /**
     * The username of the new user.
     */
    private String username;
    /**
     * The password of the new user.
     */
    private String password;
    /**
     * The email of the new user.
     */
    private String email;
    /**
     * The first name of the new user.
     */
    private String firstName;
    /**
     * The last name of the new user.
     */
    private String lastName;
    /**
     * The gender of the new user (m/f).
     */
    private String gender;

    /**
     * Creates a RegisterRequest.
     * @param username the username of the new user.
     * @param password the password of the new user.
     * @param email the email of the new user.
     * @param firstName the first name of the new user.
     * @param lastName the last name of the new user.
     * @param gender the gender of the new user (m/f).
     */
    public RegisterRequest(String username,  String password, String email,
                           String firstName, String lastName, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
