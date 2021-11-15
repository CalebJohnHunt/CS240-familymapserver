package service;

import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import model.AuthToken;
import model.Event;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.FindEventRequest;
import result.FindEventResult;

import static org.junit.jupiter.api.Assertions.*;

public class FindEventServiceTest {
    FindEventService service;
    FindEventRequest request;
    FindEventResult  result;
    User exUser;
    Event exEvent;
    AuthToken exAuthToken;
    Database db;

    @BeforeEach
    public void setup() throws DataAccessException {
        exUser = new User("EX_US_UN", "pass", "@gov", "first", "last", "m", "pID");
        exEvent = new Event("EX_ID", exUser.getUsername(), "EX_PID", -2f, 4567.213f, "Georgia", "DC", "EX_TYPE", 20000);

        service = new FindEventService();
        db = new Database();

        db.openConnection();
        db.clearTables();
        new EventDAO(db.getConnection()).insert(exEvent);
        db.closeConnection(true);

        exAuthToken = TestUtil.registerUser(exUser);
    }

    @Test
    public void findPass() throws DataAccessException {
        request = new FindEventRequest(exEvent.getEventID(), exAuthToken.getID());
        result = service.find(request);

        assertTrue(result.isSuccess());
        assertEquals(exEvent.getEventID(),            result.getEventID());
        assertEquals(exEvent.getPersonID(),           result.getPersonID());
        assertEquals(exEvent.getEventType(),          result.getEventType());
        assertEquals(exEvent.getAssociatedUsername(), result.getAssociatedUsername());
        assertEquals(exEvent.getCity(),               result.getCity());
        assertEquals(exEvent.getCountry(),            result.getCountry());
        assertEquals(exEvent.getLatitude(),           result.getLatitude());
        assertEquals(exEvent.getLongitude(),          result.getLongitude());
        assertEquals(exEvent.getYear(),               result.getYear());
    }

    @Test
    public void findFailBadEventID() throws DataAccessException {
        request = new FindEventRequest("Wrong ID", exAuthToken.getID());
        result = service.find(request);

        assertFalse(result.isSuccess());
        assertTrue(resultHasNullFields());
        assertEquals("Error: Event not found.", result.getMessage());
    }

    @Test
    public void findFailBadAuth() throws DataAccessException {
        request = new FindEventRequest(exEvent.getEventID(), "Wrong ID");
        result = service.find(request);

        assertFalse(result.isSuccess());
        assertTrue(resultHasNullFields());
        assertEquals("Error: Bad AuthToken.", result.getMessage());
    }

    private boolean resultHasNullFields() {
        return result.getEventID() == null &&
        result.getPersonID() == null &&
        result.getEventType() == null &&
        result.getAssociatedUsername() == null &&
        result.getCity() == null &&
        result.getCountry() == null &&
        result.getLatitude() == null &&
        result.getLongitude() == null &&
        result.getYear() == null;
    }
}
