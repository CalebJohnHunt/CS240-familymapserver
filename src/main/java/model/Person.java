package model;

/**
 * A person.
 */
public class Person {
    /**
     * The unique ID of the person object.
     */
    private String personID;
    /**
     * The username associated with this person.
     */
    private String associatedUsername;
    /**
     * The first name of the person.
     */
    private String firstName;
    /**
     * The last name of the person.
     */
    private String lastName;
    /**
     * The gender of the person (m/f).
     */
    private String gender;
    /**
     * The person's father.
     */
    private String fatherID;
    /**
     * The person's mother.
     */
    private String motherID;
    /**
     * The person's spouse.
     */
    private String spouseID;

    /**
     * Creates a person.
     * @param personID the unique ID of the person object.
     * @param associatedUsername the username associated with this person.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param gender   the gender of the person (m/f).
     * @param fatherID the person's father.
     * @param motherID the person's mother.
     * @param spouseID the person's spouse.
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName,
                  String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
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

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        Person op = (Person) obj;
        return op.getPersonID().equals(getPersonID()) &&
                op.getAssociatedUsername().equals(getAssociatedUsername()) &&
                op.getFirstName().equals(getFirstName()) &&
                op.getLastName().equals(getLastName()) &&
                op.getGender().equals(getGender()) &&
                op.getFatherID().equals(getFatherID()) &&
                op.getMotherID().equals(getMotherID()) &&
                op.getSpouseID().equals(getSpouseID());
    }
}
