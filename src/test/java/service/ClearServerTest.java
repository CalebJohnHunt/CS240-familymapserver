package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.Database;
import model.AuthToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.result.ClearResult;

import static org.junit.jupiter.api.Assertions.*;

public class ClearServerTest {
    private ClearService service;
    private ClearResult result;

    @BeforeEach
    public void setup() {
        service = new ClearService();
    }

    @Test
    public void clearEmptyPass() throws DataAccessException {
        result = service.clear();

        assertTrue(result.isSuccess());
        assertEquals("Clear succeeded", result.getMessage());
    }

    @Test
    public void clearPass() throws DataAccessException {
        Database db = new Database();
        AuthTokenDAO atDAO = new AuthTokenDAO(db.getConnection());
        atDAO.insert(new AuthToken("FAKE_ID", "FAKE_USERNAME"));
        db.closeConnection(true);

        result = service.clear();
        assertTrue(result.isSuccess());
        assertEquals("Clear succeeded", result.getMessage());

        atDAO = new AuthTokenDAO(db.getConnection());
        assertNull(atDAO.find("FAKE_ID"));
        db.closeConnection(false);
    }
}
