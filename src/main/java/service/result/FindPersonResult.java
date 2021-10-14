package service.result;

import model.Person;

/**
 * Result of /person/[personID].
 */
public class FindPersonResult extends ParentResult {
    /**
     * The person which was found.
     */
    private Person person;

    /**
     * Creates a successful FindPersonResult.
     * @param person the person which was found.
     * @return a successful FindPersonResult.
     */
    public static FindPersonResult createSuccessful(Person person) {
        FindPersonResult inst = new FindPersonResult();
        inst.setPerson(person);
        inst.setSuccess(true);
        return inst;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
