package model;

/**
 * A user.
 */
public class User {
    /**
     * The username of the user.
     */
    private String username;
    /**
     * The password of the user.
     */
    private String password;
    /**
     * The email of the user.
     */
    private String email;
    /**
     * The first name of the user.
     */
    private String firstName;
    /**
     * The last name of the user.
     */
    private String lastName;
    /**
     * The gender of the user (m/f).
     */
    private String gender;
    /**
     * The unique ID of the person associated with this user.
     */
    private String personID;

    /**
     *
     * @param username the username of the user.
     * @param password the password of the user.
     * @param email the email of the user.
     * @param firstName the first name of the user.
     * @param lastName the last name of the user.
     * @param gender the gender of the user (m/f).
     * @param personID the unique ID of the person associated with this user.
     */
    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
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

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        User ou = (User) obj;
        return ou.getUsername().equals(getUsername()) &&
                ou.getPassword().equals(getPassword()) &&
                ou.getEmail().equals(getEmail()) &&
                ou.getFirstName().equals(getFirstName()) &&
                ou.getLastName().equals(getLastName()) &&
                ou.getGender().equals(getGender()) &&
                ou.getPersonID().equals(getPersonID());
    }
}
