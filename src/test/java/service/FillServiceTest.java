package service;

import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.FillRequest;
import result.FillResult;

import static org.junit.jupiter.api.Assertions.*;

public class FillServiceTest {
    private FillService service;
    private FillRequest request;
    private FillResult  result;
    private User exUser;
    private Database db;

    @BeforeEach
    public void setup() throws DataAccessException {
        exUser = new User("Real_User", "pass", "@gov", "first", "last", "m", "pID");

        service = new FillService();
        db = new Database();

        db.openConnection();
        db.clearTables();
        new UserDAO(db.getConnection()).insert(exUser);
        db.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
    }

    @Test
    public void fillPassDefault() throws DataAccessException {
        request = new FillRequest(exUser.getUsername());
        result  = service.fill(request);

        assertTrue(result.isSuccess());
        assertEquals("Successfully added 31 persons and 91 events to the database", result.getMessage());
    }

    @Test
    public void fillPassExplicit() throws DataAccessException {
        request = new FillRequest(exUser.getUsername(), 4);
        result  = service.fill(request);

        assertTrue(result.isSuccess());
        assertEquals("Successfully added 31 persons and 91 events to the database", result.getMessage());
    }

    @Test
    public void fillPassCustom() throws DataAccessException {
        final int GENS = 7; // Change this one!
        final int PERSONS = (int) Math.pow(2, GENS+1)-1;
        final int EVENTS  = (PERSONS-1)*3 + 1;
        request = new FillRequest(exUser.getUsername(), GENS);
        result = service.fill(request);

        assertTrue(result.isSuccess());
        assertEquals("Successfully added " + PERSONS + " persons and " + EVENTS + " events to the database", result.getMessage());
    }

    @Test
    public void fillFailBadUsername() throws DataAccessException {
        request = new FillRequest("Wrong Username!");
        result = service.fill(request);

        assertFalse(result.isSuccess());
        assertEquals("Error: Username not found.", result.getMessage());
    }
}
