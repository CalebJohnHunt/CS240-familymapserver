package service;

import dao.DataAccessException;
import dao.Database;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.request.LoadRequest;
import service.result.LoadResult;

import static org.junit.jupiter.api.Assertions.*;

public class LoadServiceTest {
    private User[]   users;
    private Person[] persons;
    private Event[]  events;

    LoadService service;
    LoadRequest request;
    LoadResult  result;

    @BeforeEach
    public void setup() throws DataAccessException {
        users   = new User[]   {
                new User("u_un1", "pass1", "@gov1", "u_f1", "u_l1", "m", "u_PID1"),
                new User("u_un2", "pass2", "@gov2", "u_f2", "u_l2", "m", "u_PID2"),
        };
        persons = new Person[] {
                new Person("p_PID1", users[0].getUsername(), "p_f1", "p_l1", "f", "p_FID1", "p_MID1", null),
                new Person("p_PID2", "p_un2", "p_f2", "p_l2", "f", "p_FID2", null, null),
                new Person("p_PID3", "p_un3", "p_f3", "p_l3", "f", "p_FID3", "p_MID3", "p_SID3"),
        };
        events  = new Event[]  {
                new Event("e_EID1", users[0].getUsername(), persons[0].getPersonID(), 400.1f, -298.999f, "Count", "cit", "Bored", 0),
                new Event("e_EID2", "e_un2", "e_pid2", 4002.1f, -4298.999f, "Count2", "cit2", "Bo2r2ed", 99),
        };

        Database db = new Database();
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);

        service = new LoadService();
    }

    @Test
    public void loadPass() throws DataAccessException {
        request = new LoadRequest(users, persons, events);
        result  = service.load(request);

        assertTrue(result.isSuccess());
        assertEquals("Successfully added "+  users.length +" users, "+ persons.length +" persons, and " + events.length +" events to the database.", result.getMessage());
    }

    @Test
    public void loadPassEmptyArrays() throws DataAccessException {
        request = new LoadRequest(new User[] {}, new Person[] {}, new Event[] {});
        result = service.load(request);

        assertTrue(result.isSuccess());
        assertEquals("Successfully added 0 users, 0 persons, and 0 events to the database.", result.getMessage());
    }
}
