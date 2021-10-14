package dao;

import model.Event;

import java.sql.Connection;
import java.util.List;

/**
 * Accesses data about Events from the database.
 */
public class EventDAO {
    /**
     * The connection to the database.
     */
    private final Connection conn;

    /**
     * Creates an event DAO.
     * @param conn the connection to the database.
     */
    public EventDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts a new event into the database.
     * @param event Event to insert into database.
     * @throws DataAccessException
     */
    public void insert(Event event) throws DataAccessException {}

    /**
     * Finds an event in the database from an eventID.
     * @param eventID ID of the event to find.
     * @return the event with the matching eventID or null if the eventID does not exist
     * @throws DataAccessException
     */
    public Event find(String eventID) throws DataAccessException {
        return null;
    }

    /**
     * Finds all the event tied to a certain username including birth, death, etc.
     * @param username username to find events for.
     * @return List of events tied to the username
     * @throws DataAccessException
     */
    public List<Event> findForUser(String username) throws DataAccessException { return null; }

    /**
     * Removes an event from the database.
     * @param eventID ID of the event to remove.
     * @throws DataAccessException
     */
    public void delete(String eventID) throws DataAccessException {}

    /**
     * Removes all events from the database.
     * @throws DataAccessException
     */
    public void clearTable() throws DataAccessException {}

}
