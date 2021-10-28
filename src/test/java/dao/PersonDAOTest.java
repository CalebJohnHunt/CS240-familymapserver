package dao;

import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
    public void findFamilyPassAllMembers() throws DataAccessException {
        Person mother = new Person("mother-id", bestPerson.getAssociatedUsername(), "mom", "my", "f", null, null, "father-id");
        Person father = new Person("father-id", bestPerson.getAssociatedUsername(), "dad", "dy", "m", null, null, "mother-id");
        Person spouse = new Person("spouse-id", bestPerson.getAssociatedUsername(), "wife", "y", "f", null, null, bestPerson.getPersonID());
        pDao.insert(father);
        pDao.insert(mother);
        pDao.insert(spouse);
        List<Person> expectedFamily = new ArrayList<>();
        expectedFamily.add(father);
        expectedFamily.add(mother);
        expectedFamily.add(spouse);
        expectedFamily.add(bestPerson);

        bestPerson.setFatherID(father.getPersonID());
        bestPerson.setMotherID(mother.getPersonID());
        bestPerson.setSpouseID(spouse.getPersonID());

        pDao.insert(bestPerson);

        List<Person> family = pDao.findAssociatedPersons(bestPerson.getAssociatedUsername());

        assertEquals(expectedFamily, family);
    }

    @Test
    public void findImmediateFamilyPassSpouseOnly() throws DataAccessException {
        Person mother = new Person("mother-id", bestPerson.getAssociatedUsername(), "mom", "my", "f", null, null, "father-id");
        Person father = new Person("father-id", bestPerson.getAssociatedUsername(), "dad", "dy", "m", null, null, "mother-id");
        Person spouse = new Person("spouse-id", bestPerson.getAssociatedUsername(), "wife", "y", "f", null, null, bestPerson.getPersonID());
        pDao.insert(mother);
        pDao.insert(father);
        pDao.insert(spouse);

        bestPerson.setFatherID(father.getPersonID());
        bestPerson.setMotherID(mother.getPersonID());
        bestPerson.setSpouseID(spouse.getPersonID());

        pDao.insert(bestPerson);

        // Father and mother are spouse. Let's use them!
        List<Person> family = pDao.findAssociatedPersons(father.getAssociatedUsername());

        assertEquals(mother, family.get(0));
    }

    @Test
    public void findFamilyPassOnlySelf() throws DataAccessException {
        pDao.insert(bestPerson);

        List<Person> family = pDao.findAssociatedPersons(bestPerson.getAssociatedUsername());

        List<Person> expected = new ArrayList<>();
        expected.add(bestPerson);

        assertEquals(expected, family);
    }

    @Test
    public void findFamilyFail() throws DataAccessException {
        Person mother = new Person("mother-id", bestPerson.getAssociatedUsername(), "mom", "my", "f", null, null, "father-id");
        Person father = new Person("father-id", bestPerson.getAssociatedUsername(), "dad", "dy", "m", null, null, "mother-id");
        Person spouse = new Person("spouse-id", bestPerson.getAssociatedUsername(), "wife", "y", "f", null, null, bestPerson.getPersonID());
        pDao.insert(mother);
        pDao.insert(father);
        pDao.insert(spouse);

        bestPerson.setFatherID(father.getPersonID());
        bestPerson.setMotherID(mother.getPersonID());
        bestPerson.setSpouseID(spouse.getPersonID());

        // Suppose to be username! Not personID
        List<Person> family = pDao.findAssociatedPersons("Unrelated username");

        assertEquals(new ArrayList<Person>(), family);
    }

    @Test
    public void findPass() throws DataAccessException {
        pDao.insert(bestPerson);
        Person foundPerson = pDao.find(bestPerson.getPersonID());
        assertNotNull(foundPerson);
        assertEquals(bestPerson, foundPerson);
    }

    @Test
    public void findFailNoInsert() throws DataAccessException {
        assertNull(pDao.find(bestPerson.getPersonID()));
    }

    @Test
    public void findFailWithInsert() throws DataAccessException {
        pDao.insert(bestPerson);
        assertNull(pDao.find("definitely_fake"));
    }

    @Test
    public void deleteUserPersonsPass() throws DataAccessException {
        pDao.insert(bestPerson);
        pDao.insert(new Person("Garble", bestPerson.getAssociatedUsername(), "firsty", "lasty", "m", null, null, null));
        pDao.deleteUserPersons(bestPerson.getAssociatedUsername());
        assertEquals(new ArrayList<>(), pDao.findAssociatedPersons(bestPerson.getAssociatedUsername()));
        assertNull(pDao.find(bestPerson.getPersonID()));
        assertNull(pDao.find("Garble"));
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

