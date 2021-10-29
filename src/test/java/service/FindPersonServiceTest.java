package service;

import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import dao.PersonDAO;
import model.AuthToken;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.request.FindPersonRequest;
import service.result.FindPersonResult;

import static org.junit.jupiter.api.Assertions.*;

public class FindPersonServiceTest {
    FindPersonService service;
    FindPersonRequest request;
    FindPersonResult  result;
    User exUser;
    Person exPerson;
    AuthToken exAuthToken;

    @BeforeEach
    public void setup() throws DataAccessException {
        exUser   = new User("EX_US_UN", "pass", "@gov", "first", "last", "m", "pID");
        exPerson = new Person("EX_PID", exUser.getUsername(), "pFirst", "pLast", "f", "fID", "mID", "sID");

        service = new FindPersonService();
        Database db = new Database();

        db.openConnection();
        db.clearTables();
        new PersonDAO(db.getConnection()).insert(exPerson);
        db.closeConnection(true);

        exAuthToken = TestUtil.registerUser(exUser);
    }

    @Test
    public void findPass() throws DataAccessException {
        request = new FindPersonRequest(exPerson.getPersonID(), exAuthToken.getID());
        result = service.find(request);

        assertTrue(result.isSuccess());
        assertEquals(exPerson.getPersonID(),           result.getPersonID());
        assertEquals(exPerson.getAssociatedUsername(), result.getAssociatedUsername());
        assertEquals(exPerson.getFirstName(),          result.getFirstName());
        assertEquals(exPerson.getLastName(),           result.getLastName());
        assertEquals(exPerson.getGender(),             result.getGender());
        assertEquals(exPerson.getFatherID(),           result.getFatherID());
        assertEquals(exPerson.getMotherID(),           result.getMotherID());
        assertEquals(exPerson.getSpouseID(),           result.getSpouseID());
    }

    @Test
    public void findFailBadPersonID() throws DataAccessException {
        request = new FindPersonRequest("Wrong ID", exAuthToken.getID());
        result = service.find(request);

        assertFalse(result.isSuccess());
        assertTrue(hasNullField());
        assertEquals("Error: Person not found.", result.getMessage());
    }

    @Test
    public void findFailBadAuth() throws DataAccessException {
        request = new FindPersonRequest(exPerson.getPersonID(), "Wrong ID");
        result = service.find(request);

        assertFalse(result.isSuccess());
        assertTrue(hasNullField());
        assertEquals("Error: Bad AuthToken.", result.getMessage());
    }

    private boolean hasNullField() {
        return result.getPersonID() == null &&
        result.getAssociatedUsername() == null &&
        result.getFirstName() == null &&
        result.getLastName() == null &&
        result.getGender() == null &&
        result.getFatherID() == null &&
        result.getMotherID() == null &&
        result.getSpouseID() == null;
    }
}
