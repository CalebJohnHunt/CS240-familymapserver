package service;

import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import result.LoginResult;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {
    private User user;
    private UserDAO uDAO;

    private LoginService service;
    private LoginRequest request;
    private LoginResult  result;

    @BeforeEach
    public void setup() throws DataAccessException {
        service = new LoginService();
        Database db = new Database();
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);

        user = new User("u_un1", "pass1", "@gov1", "u_first1", "u_last1", "m", "u_pid1");
        new UserDAO(db.getConnection()).insert(user);
        db.closeConnection(true);
    }

    @Test
    public void loginPass() throws DataAccessException {
        request = new LoginRequest(user.getUsername(), user.getPassword());
        result  = service.login(request);

        assertTrue(result.isSuccess());
        assertEquals(user.getPersonID(), result.getPersonID());
        assertEquals(user.getUsername(), result.getUsername());
        assertNotNull(result.getAuthtoken());
    }

    @Test
    public void loginFailUsername() throws DataAccessException {
        request = new LoginRequest("wrong username", user.getPassword());
        result  = service.login(request);

        assertFalse(result.isSuccess());
        assertEquals("Error: Username or password could not be found.", result.getMessage());
    }

    @Test
    public void loginFailPassword() throws DataAccessException {
        request = new LoginRequest(user.getUsername(), "wrong password");
        result  = service.login(request);

        assertFalse(result.isSuccess());
        assertEquals("Error: Username or password could not be found.", result.getMessage());
    }
}
