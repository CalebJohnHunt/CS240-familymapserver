package dao;

import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {
    private Database db;
    private Person bestPerson;
    private PersonDAO pDao;

    @BeforeEach
    public void setup() throws DataAccessException {
        db = new Database();
        bestPerson = new Person("dead-beef", "jim_browning", "Jimothy", "Browning",
                "m","face-feed", "cafe-babe", "face-babe");
        Connection conn = db.getConnection();
        db.clearTables();
        pDao = new PersonDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        pDao.insert(bestPerson);
        Person foundPerson = pDao.find(bestPerson.getPersonID());
        assertNotNull(foundPerson);
        assertEquals(bestPerson, foundPerson);
    }

    @Test
    public void insertFail() throws DataAccessException {
        pDao.insert(bestPerson);
        assertThrows(DataAccessException.class, ()-> pDao.insert(bestPerson));
    }

    @Test
    public void findPass() throws DataAccessException {
        pDao.insert(bestPerson);
        Person foundPerson = pDao.find(bestPerson.getPersonID());
        assertNotNull(foundPerson);
        assertEquals(bestPerson, foundPerson);
    }

    @Test
    public void findFailNoInserts() throws DataAccessException {
        assertNull(pDao.find(bestPerson.getPersonID()));
    }

    @Test
    public void findFailWithInsert() throws DataAccessException {
        pDao.insert(bestPerson);
        assertNull(pDao.find("definitely_fake"));
    }

    @Test
    public void deletePass() throws DataAccessException {
        pDao.insert(bestPerson);
        pDao.delete(bestPerson.getPersonID());
        Person foundPerson = pDao.find(bestPerson.getPersonID());
        assertNull(foundPerson);
    }

    @Test
    public void clearPass() throws DataAccessException {
        pDao.insert(bestPerson);
        pDao.clearTable();
        Person foundUser = pDao.find(bestPerson.getPersonID());
        assertNull(foundUser);
    }
}

