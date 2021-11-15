package service;

import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import model.AuthToken;
import model.Person;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.GetFamilyRequest;
import result.GetFamilyResult;

import static org.junit.jupiter.api.Assertions.*;

public class GetFamilyServiceTest {
    User   user;
    Person person1;
    Person person2;
    AuthToken authToken;

    GetFamilyService service;
    GetFamilyRequest request;
    GetFamilyResult  result;

    @BeforeEach
    public void setup() throws DataAccessException {
        user    = new User("GFES_UN", "pass", "@gov", "first", "last", "f", "uPID");
        person1 = new Person("PID1", user.getUsername(), "first1", "last1", "m", null, null, null);
        person2 = new Person("PID2", user.getUsername(), "first2", "last2", "f", "FID2", "MID2", "SID2");

        Database db = new Database();
        service = new GetFamilyService();

        db.openConnection();
        db.clearTables();
        PersonDAO pDAO = new PersonDAO(db.getConnection());
        pDAO.insert(person1);
        pDAO.insert(person2);
        db.closeConnection(true);

        authToken = TestUtil.registerUser(user);
    }

    @Test
    public void getFamilyPass() throws DataAccessException {
        request = new GetFamilyRequest(authToken.getID());
        result = service.getFamily(request);

        Person[] expected = new Person[] {person1, person2};

        assertTrue(result.isSuccess());
        assertArrayEquals(expected, result.getData());
    }

    @Test
    public void getFamilyFail() throws DataAccessException {
        request = new GetFamilyRequest("Wrong ID!");
        result = service.getFamily(request);

        assertFalse(result.isSuccess());
        assertNull(result.getData());
        assertEquals("Error: Bad AuthToken.", result.getMessage());
    }
}
