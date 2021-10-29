package service.utility;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.Database;
import model.AuthToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenGeneratorTest {
    private Database db;
    private String username;

    @BeforeEach
    public void setup() throws DataAccessException {
        db = new Database();
        db.openConnection();
        db.clearTables();

        username = "new_username";
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void generatePass() throws DataAccessException {
        String authTokenID = AuthTokenGenerator.generate(username, db.getConnection());
        AuthToken authToken = new AuthTokenDAO(db.getConnection()).find(authTokenID);

        assertNotNull(authToken);
        assertEquals(username, authToken.getAssociatedUsername());
        assertEquals(authTokenID, authToken.getID());
    }
}
