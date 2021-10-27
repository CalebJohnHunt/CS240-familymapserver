package dao;

import model.Event;

import java.sql.*;
import java.util.ArrayList;
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
     * @throws DataAccessException Error accessing data
     */
    public void insert(Event event) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Events (EventID, AssociatedUsername, PersonID, Latitude, Longitude, " +
                "Country, City, EventType, Year) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    /**
     * Finds an event in the database from an eventID.
     * @param eventID ID of the event to find.
     * @return the event with the matching eventID or null if the eventID does not exist
     * @throws DataAccessException  Error accessing data
     */
    public Event find(String eventID) throws DataAccessException {
        Event event;
        ResultSet rs = null;
        String sql = "SELECT * FROM Events WHERE EventID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                return event;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding event");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /**
     * Finds all the events tied to a certain username including birth, death, etc.
     * @param username username to find events for.
     * @return List of events tied to the username
     * @throws DataAccessException Error accessing data
     */
    public List<Event> findForUser(String username) throws DataAccessException {
        String sql = "SELECT * FROM EVENTS WHERE AssociatedUsername = ?;";
        ResultSet rs = null;
        List<Event> events = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            while (rs.next()) {
                events.add(new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year")));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while finding events for user");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return events;
    }

    /**
     * Removes an event from the database.
     * @param eventID ID of the event to remove.
     * @throws DataAccessException Error accessing data
     */
    public void delete(String eventID) throws DataAccessException {
        String sql = "DELETE FROM Events WHERE EventID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            stmt.executeUpdate();
        } catch  (SQLException e) {
            throw new DataAccessException("SQL Error encountered while deleting event");
        }

    }

    /**
     * Removes all events from the database.
     * @throws DataAccessException Error accessing data
     */
    public void clearTable() throws DataAccessException {
        String sql = "DELETE FROM Events";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while clearing event table");
        }
    }

}
