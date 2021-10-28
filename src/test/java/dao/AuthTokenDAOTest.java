package dao;

import model.AuthToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthTokenDAOTest {
    private Database db;
    private AuthToken exAuthToken;
    private AuthTokenDAO atDao;

    @BeforeEach
    public void setup() throws DataAccessException {
        db = new Database();
        exAuthToken = new AuthToken("example_id", "example_username");
        Connection conn = db.getConnection();
        db.clearTables();
        atDao = new AuthTokenDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        atDao.insert(exAuthToken);
        AuthToken foundAuthToken = atDao.find(exAuthToken.getID());
        assertNotNull(foundAuthToken);
        assertEquals(exAuthToken, foundAuthToken);
    }

    @Test
    public void insertFail() throws DataAccessException {
        atDao.insert(exAuthToken);
        assertThrows(DataAccessException.class, ()-> atDao.insert(exAuthToken));
    }
    @Test
    public void findPass() throws DataAccessException {
        atDao.insert(exAuthToken);
        AuthToken foundAuthToken = atDao.find(exAuthToken.getID());
        assertNotNull(foundAuthToken);
        assertEquals(exAuthToken, foundAuthToken);
    }

    @Test
    public void findFailNoInsert() throws DataAccessException {
        assertNull(atDao.find(exAuthToken.getID()));
    }

    @Test
    public void findFailWithInsert() throws DataAccessException {
        atDao.insert(exAuthToken);
        assertNull(atDao.find("definitely_fake"));
    }

    @Test
    public void findForUserPass() throws DataAccessException {
        atDao.insert(exAuthToken);
        AuthToken at2 = new AuthToken("id2", exAuthToken.getAssociatedUsername());
        atDao.insert(at2);
        List<AuthToken> tokens = atDao.findForUser(at2.getAssociatedUsername());
        assertNotNull(tokens);
        List<AuthToken> expected = new ArrayList<>();
        expected.add(exAuthToken);
        expected.add(at2);
        assertEquals(expected, tokens);
    }

    @Test
    public void findForUserFail() throws DataAccessException {
        atDao.insert(exAuthToken);
        AuthToken at2 = new AuthToken("id2", exAuthToken.getAssociatedUsername());
        atDao.insert(at2);
        List<AuthToken> tokens = atDao.findForUser("Wrong username");
        assertEquals(new ArrayList<>(), tokens);
    }

    @Test
    public void deleteUserAuthTokensPass() throws DataAccessException {
        atDao.insert(exAuthToken);
        atDao.insert(new AuthToken("Garble", exAuthToken.getAssociatedUsername()));
        atDao.deleteUserAuthTokens(exAuthToken.getAssociatedUsername());
        assertEquals(new ArrayList<>(), atDao.findForUser(exAuthToken.getAssociatedUsername()));
        assertNull(atDao.find(exAuthToken.getID()));
        assertNull(atDao.find("Garble"));
    }

    @Test
    public void deletePass() throws DataAccessException {
        atDao.insert(exAuthToken);
        atDao.delete(exAuthToken.getID());
        AuthToken foundAuthToken = atDao.find(exAuthToken.getID());
        assertNull(foundAuthToken);
    }

    @Test
    public void clearPass() throws DataAccessException {
        atDao.insert(exAuthToken);
        atDao.clearTable();
        AuthToken foundAuthToken = atDao.find(exAuthToken.getID());
        assertNull(foundAuthToken);
    }
}
