package model;

/**
 * Unique token to signify someone is currently logged in with a certain account.
 */
public class AuthToken {
    /**
     * Unique String to identify this token.
     */
    private String ID;
    /**
     * The username of the user who created this token by logging in.
     */
    private String associatedUsername;

    /**
     * Creates an authToken
     * @param ID Unique String to identify this token.
     * @param associatedUsername The username of the user who created this token by logging in.
     */
    public AuthToken(String ID, String associatedUsername) {
        this.ID = ID;
        this.associatedUsername = associatedUsername;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        AuthToken oat = (AuthToken) obj;
        return oat.getID().equals(getID()) &&
                oat.getAssociatedUsername().equals(getAssociatedUsername());
    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "ID='" + ID + '\'' +
                ", associatedUsername='" + associatedUsername + '\'' +
                '}';
    }
}
