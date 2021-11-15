package result;

import model.Person;

/**
 * Result of /person.
 */
public class GetFamilyResult extends Result {
    /**
     * The user's family members.
     */
    private Person[] data;

    /**
     * Creates a successful GetFamilyResult.
     * @param data the user's family members.
     */
    public GetFamilyResult(Person[] data) {
        setData(data);
        setSuccess(true);
    }

    /**
     * Creates a failed GetFamilyResult.
     * @param message the reason the call to the API failed.
     */
    public GetFamilyResult(String message) {
        setMessage(message);
        setSuccess(false);
    }
    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data = data;
    }
}
