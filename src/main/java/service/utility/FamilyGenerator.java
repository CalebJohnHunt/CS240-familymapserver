package service.utility;

import dao.DataAccessException;
import dao.EventDAO;
import dao.PersonDAO;
import model.Event;
import model.Person;

import java.sql.Connection;
import java.util.Random;

/**
 * Generates fake family members (ancestors) for a given person.
 */
public class FamilyGenerator {
    /**
     * Default number of generations if one is not specified.
     */
    private static final int DEFAULT_GENERATIONS = 4;
    /**
     * Signifies that this death year should be ignored. Used for current persons.
     */
    public static final int NO_DEATH_YEAR = 0xffffff;

    /**
     * Main way to use random.
     */
    private static final Random random = new Random();
    /**
     * DAO to insert events.
     */
    private static EventDAO eDAO;
    /**
     * DAO to insert persons.
     */
    private static PersonDAO pDAO;
    /**
     * Counter of how many persons we added for this generating. This could have been done through a mathematical
     * formula, but I wanted actual counting to make sure.
     */
    private static int personsAdded;
    /**
     * Counter of how many events we added for this generating. This could have been done through a mathematical
     * formula, but I wanted actual counting to make sure.
     */
    private static int eventsAdded;

    /**
     * Makes fake ancestors for given person, adds them and the person to the database.
     * Each ancestor gets 3 events: birth, death, marriage. Person gets 1: birth.
     * @param person the root person to build ancestors off of.
     * @param generations the number of generations to go back from the person.
     * @param conn a connection to the database for the DAOs.
     * @return a list with two elements: {personsAdded, eventsAdded}.
     */
    public static int[] run(Person person, int generations, Connection conn) {
        setup(conn);

        Person finishedPerson = generateFamily(person, person.getGender(), generations, generateBirthYear(), NO_DEATH_YEAR);
        insertPerson(finishedPerson);
        return new int[] {personsAdded, eventsAdded};
    }

    /**
     * Makes fake ancestors for the given person, using the default number of generations.
     * @param person the root person to build ancestors off of.
     * @param conn the number of generations to go back from the person.
     * @return a list with two elements: {personsAdded, eventsAdded}.
     */
    public static int[] run(Person person, Connection conn) {
        return run(person, DEFAULT_GENERATIONS, conn);
    }

    /**
     * Generates the person and their parents, then continues to do so recursively through the generations.
     * @param person the person to create.
     * @param gender the gender of the person (for random naming).
     * @param generations the number of generations to create.
     * @param birthYear the year of birth for the person.
     * @param deathYear the year of death for the person.
     * @return the person which was generated (with motherID, fatherID, and possibly spouseID!).
     */
    public static Person generateFamily(Person person, String gender, int generations, int birthYear, int deathYear) {
        Person generatePerson = generatePerson(person, gender, birthYear, deathYear);

        if (generations > 0) {
            generateParentsForPerson(generatePerson, generations-1, birthYear);
        }

        // do NOT insert person here. It would double-insert the parents (and thus crash)!

        return generatePerson;
    }

    /**
     * Generates the person; if it's the root person, we only give it a birth event, otherwise generate all the fake data too.
     * @param person the person to generate (null to create a random one).
     * @param gender the gender of the person (for naming).
     * @param birthYear the year of birth for the person.
     * @param deathYear the year of death for the person.
     * @return the person which was generated.
     */
    private static Person generatePerson(Person person, String gender, int birthYear, int deathYear) {
        // If we have a person, it's because it's our base person (our root). We don't want to change anything about
        //  them; we only add a birth event for them.
        //  Otherwise, we're making an ancestor, and thus we make everything up!
        if (person == null) {
            person = new Person();
            person.setGender(gender);
            if (gender.equals("f")) {
                person.setFirstName(NameDataHolder.fRandom());
            } else {
                person.setFirstName(NameDataHolder.mRandom());
            }
            person.setLastName(NameDataHolder.sRandom());

            // This is not perfect. It could be a UUID, but that seems silly
            person.setAssociatedUsername(person.getFirstName() + '_' + person.getLastName() + random.nextInt());

            person.setPersonID(IDGenerator.generate());

            generateEvent(person, LocationDataWrapper.random(), "death", deathYear);
        }

        generateEvent(person, LocationDataWrapper.random(), "birth", birthYear);

        // DON'T Save person in database yet (w/o possible parents)
        // Remember: As soon as we save someone, it's really hard to change them.

        return person;
    }

    /**
     * Generates the two parent Persons and sets the person's motherID and fatherID correctly.
     * @param child the child of the two newly created parents.
     * @param generations the number of generations to complete.
     * @param childBirthYear the year the child was born (so the deaths and births line up).
     */
    private static void generateParentsForPerson(Person child, int generations, int childBirthYear) {

        Person mother = generateFamily(null, "f", generations,
                childBirthYear - random.nextInt(25)-20, childBirthYear + random.nextInt(50)+10);
        Person father = generateFamily(null, "m", generations,
                childBirthYear - random.nextInt(25)-22, childBirthYear + random.nextInt(50)+10);

        mother.setSpouseID(father.getPersonID());
        father.setSpouseID(mother.getPersonID());

        Location location = LocationDataWrapper.random();

        // Generate marriages
        Event marriage = generateEvent(mother, location, "marriage", childBirthYear - random.nextInt(4)-1);
        generateEvent(father, location, marriage.getEventType(), marriage.getYear());

        insertPerson(mother);
        insertPerson(father);

        child.setMotherID(mother.getPersonID());
        child.setFatherID(father.getPersonID());
    }

    /**
     * Set up the DAOs and counter variables.
     * @param conn the connection to the database.
     */
    private static void setup(Connection conn) {
        eDAO = new EventDAO(conn);
        pDAO = new PersonDAO(conn);
        personsAdded = 0;
        eventsAdded = 0;
    }

    /**
     * Generate and insert an event with person and location data and increments the eventsAdded counter.
     * @param person the person the event belongs to.
     * @param location the location where the event happened.
     * @param eventType the type of event.
     * @param year the year of the event.
     * @return the event which was created.
     */
    private static Event generateEvent(Person person, Location location, String eventType, int year) {
        Event event = new Event(IDGenerator.generate(), person.getAssociatedUsername(), person.getPersonID(),
                location.getLatitude(), location.getLongitude(), location.getCountry(), location.getCity(), eventType, year);
        try {
            eDAO.insert(event);
        } catch (DataAccessException e) {
            e.printStackTrace(); // TODO: Logger
        }
        ++eventsAdded;
        return event;
    }

    /**
     * Inserts a person into the database and increments the personsAdded counter.
     * @param person the person to insert.
     */
    private static void insertPerson(Person person) {
        try {
            pDAO.insert(person);
        } catch (DataAccessException e) {
            e.printStackTrace(); // TODO: Logger
        }
        ++personsAdded;
    }

    /**
     * Generates a birth year up to 71 years ago (from 2021).
     * @return the birth year generated.
     */
    private static int generateBirthYear() {
        return 1950 + random.nextInt(71);
    }

    public static int getPersonsAdded() {
        return personsAdded;
    }

    public static int getEventsAdded() {
        return eventsAdded;
    }
}
