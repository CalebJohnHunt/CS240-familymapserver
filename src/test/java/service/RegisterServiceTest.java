package service;

import dao.DataAccessException;
import dao.Database;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.request.RegisterRequest;
import service.result.RegisterResult;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterServiceTest {
    private User user;

    private RegisterService service;
    private RegisterRequest request;
    private RegisterResult  result;

    @BeforeEach
    public void setup() throws DataAccessException {
        service = new RegisterService();
        Database db = new Database();
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);

        user = new User("u_un1", "pass1", "@gov1", "u_first1", "u_last1", "m", "u_pid1");
    }

    @Test
    public void registerPass() throws DataAccessException {
        request = new RegisterRequest(user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getGender());
        result  = service.register(request);

        assertTrue(result.isSuccess());
        assertNotNull(result.getAuthtoken());
        assertEquals(user.getUsername(), result.getUsername());
        assertNotNull(result.getAuthtoken());
    }

    @Test
    public void registerFailSameUsername() throws DataAccessException {
        request = new RegisterRequest(user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getGender());
        result  = service.register(request);

        result  = service.register(request);

        assertFalse(result.isSuccess());
        assertEquals("Error: Could not register user.", result.getMessage());
    }
}
