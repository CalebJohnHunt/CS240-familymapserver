package service.result;

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
     * @return a successful GetFamilyResult.
     */
    public static GetFamilyResult createSuccessful(Person[] data) {
        GetFamilyResult inst = new GetFamilyResult();
        inst.data = data;
        inst.setSuccess(true);

        return inst;
    }

    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data = data;
    }
}
