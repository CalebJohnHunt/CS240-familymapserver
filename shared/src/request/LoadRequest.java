package request;

import model.Event;
import model.Person;
import model.User;

/**
 * Request to /load.
 */
public class LoadRequest {
    /**
     * The array of users to add to the database.
     */
    private User[] users;
    /**
     * The array of persons to add to the database.
     */
    private Person[] persons;
    /**
     * The array of events to add to the database.
     */
    private Event[] events;

    /**
     * Creates a LoadRequest.
     * @param users the array of users to add to the database.
     * @param persons the array of persons to add to the database.
     * @param events the array of events to add to the database.
     */
    public LoadRequest(User[] users, Person[] persons, Event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
