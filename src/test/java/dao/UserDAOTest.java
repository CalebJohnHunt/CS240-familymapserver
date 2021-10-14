package dao;

import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
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
    public void findFailNoInserts() throws DataAccessException {
        assertNull(uDao.find(bestUser.getUsername()));
    }

    // Try to find a username that doesn't exist
    @Test
    public void findFailWithInsert() throws DataAccessException {
        uDao.insert(bestUser);
        assertNull(uDao.find("definitely_fake"));
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
