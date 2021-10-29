package service;

import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import model.AuthToken;
import model.Event;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.request.GetFamilyEventsRequest;
import service.result.GetFamilyEventsResult;

import static org.junit.jupiter.api.Assertions.*;

public class GetFamilyEventsServiceTest {
    User  user;
    Event event1;
    Event event2;
    AuthToken authToken;

    GetFamilyEventsService service;
    GetFamilyEventsRequest request;
    GetFamilyEventsResult  result;

    @BeforeEach
    public void setup() throws DataAccessException {
        user   = new User("GFES_UN", "pass", "@gov", "first", "last", "f", "uPID");
        event1 = new Event("EID1", user.getUsername(), user.getPersonID(), 1.1f, 1.2f, "USA", "PROVO", "Stuff1", 8);
        event2 = new Event("EID2", user.getUsername(), "Diff_Person", 2.1f, 2.2f, "CAN", "OTAWA","Stuff2", 2000);

        Database db = new Database();
        service = new GetFamilyEventsService();

        db.openConnection();
        db.clearTables();
        EventDAO eDAO = new EventDAO(db.getConnection());
        eDAO.insert(event1);
        eDAO.insert(event2);
        db.closeConnection(true);

        authToken = TestUtil.registerUser(user);
    }

    @Test
    public void getFamilyEventsPass() throws DataAccessException {
        request = new GetFamilyEventsRequest(authToken.getID());
        result = service.getFamilyEvents(request);

        Event[] expected = new Event[] {event1, event2};

        assertTrue(result.isSuccess());
        assertArrayEquals(expected, result.getData());
    }

    @Test
    public void getFamilyEventsFail() throws DataAccessException {
        request = new GetFamilyEventsRequest("Wrong ID!");
        result = service.getFamilyEvents(request);

        assertFalse(result.isSuccess());
        assertNull(result.getData());
        assertEquals("Error: Bad AuthToken.", result.getMessage());
    }
}
