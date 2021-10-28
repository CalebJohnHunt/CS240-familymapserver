package dao;

import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private Database db;
    private User bestUser;
    private UserDAO uDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        bestUser = new User("jim_browning", "cool&password", "jimmy@gmail.com",
                "Jimothy", "Browning", "m", "deed-beef");
        Connection conn = db.getConnection();
        db.clearTables();
        uDao = new UserDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        uDao.insert(bestUser);
        User compareTest = uDao.find(bestUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    // Duplicate fails
    @Test
    public void insertFail() throws DataAccessException {
        uDao.insert(bestUser);
        assertThrows(DataAccessException.class, ()-> uDao.insert(bestUser));
    }

    @Test
    public void validatePass() throws DataAccessException {
        uDao.insert(bestUser);
        boolean isValid = uDao.validate(bestUser.getUsername(), bestUser.getPassword());
        assertTrue(isValid);
    }

    @Test
    public void validateFailBadPassword() throws DataAccessException {
        uDao.insert(bestUser);
        boolean isValid = uDao.validate(bestUser.getUsername(), "Bad_Password");
        assertFalse(isValid);
    }

    @Test
    public void validateFailBadUsername() throws DataAccessException {
        uDao.insert(bestUser);
        boolean isValid = uDao.validate("Bad_Username", bestUser.getPassword());
        assertFalse(isValid);
    }

    @Test
    public void validateFailBadUsernameAndPassword() throws DataAccessException {
        uDao.insert(bestUser);
        boolean isValid = uDao.validate("Bad_Username", "Bad_Password");
        assertFalse(isValid);
    }

    @Test
    public void validateFailNoInsert() throws DataAccessException {
        boolean isValid = uDao.validate(bestUser.getUsername(), bestUser.getPassword());
        assertFalse(isValid);
    }

    // Same as insertPass? All right.
    @Test
    public void findPass() throws DataAccessException {
        uDao.insert(bestUser);

        User foundUser = uDao.find(bestUser.getUsername());

        assertNotNull(foundUser);

        assertEquals(bestUser, foundUser);
    }

    // Try to find a user with an empty table
    @Test
    public void findFailNoInsert() throws DataAccessException {
        assertNull(uDao.find(bestUser.getUsername()));
    }

    // Try to find a username that doesn't exist
    @Test
    public void findFailWithInsert() throws DataAccessException {
        uDao.insert(bestUser);
        assertNull(uDao.find("definitely_fake"));
    }

    @Test
    public void deletePass() throws DataAccessException {
        uDao.insert(bestUser);
        uDao.delete(bestUser.getUsername());
        User foundUser = uDao.find(bestUser.getUsername());
        assertNull(foundUser);
    }

    // Try to clear table
    @Test
    public void clearPass() throws DataAccessException {
        uDao.insert(bestUser);
        uDao.clearTable();
        User foundUser = uDao.find(bestUser.getUsername());
        assertNull(foundUser);
    }
}
