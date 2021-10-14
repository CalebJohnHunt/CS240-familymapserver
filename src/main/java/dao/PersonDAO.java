package dao;

import model.Person;

import java.sql.Connection;
import java.util.List;

/**
 * Access data about persons from the database.
 */
public class PersonDAO {
    /**
     * The connection to the database.
     */
    private final Connection conn;

    /**
     * Creates a person DAO.
     * @param conn the connection to the database.
     */
    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds a person to the database.
     * @param person person to add to database.
     * @throws DataAccessException
     */
    public void insert(Person person) throws DataAccessException {}

    /**
     * Finds a person matching the ID from the database.
     * @param personID ID of the person to find.
     * @return the person with the matching ID
     * @throws DataAccessException
     */
    public Person find(String personID) throws DataAccessException { return null; }

    /**
     * Finds all family members of the user including mother, father, and spouse.
     * @param username the username of the user.
     * @return a list of all the family members of the user.
     * @throws DataAccessException
     */
    public List<Person> findFamilyOfPerson(String username) throws DataAccessException { return null; }

    /**
     * Removes a person from the database.
     * @param personID ID of the person to remove.
     * @throws DataAccessException
     */
    public void delete(String personID) throws DataAccessException {}

    /**
     * Removes all persons from the database.
     * @throws DataAccessException
     */
    public void clearTable() throws DataAccessException {}

}
