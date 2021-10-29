package service.utility;

import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FamilyGeneratorTest {
    private Person person;
    private PersonDAO pDAO;
    private Database db;

    @BeforeEach
    public void setup() throws DataAccessException {
        person = new Person("pid", "p_un", "pfn", "pln", "m", null, null, null);

        db = new Database();

        pDAO = new PersonDAO(db.getConnection());
        db.clearTables();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void runPass0() throws DataAccessException {
        int[] addeds = FamilyGenerator.run(person, 0, db.getConnection());

        final int[] expected = {1, 1}; // root person, birth event

        assertArrayEquals(expected, addeds);
        Person foundPerson = pDAO.find(person.getPersonID());
        assertNotNull(foundPerson);
        assertEquals(person, foundPerson);
    }

    @Test
    public void runPass1() throws DataAccessException {
        int[] addeds = FamilyGenerator.run(person, 1, db.getConnection());

        final int[] expected = {3, 7}; // person + 2 parents, birth*3 + death*2 + marriage*2

        assertArrayEquals(expected, addeds);
        Person foundPerson = pDAO.find(person.getPersonID());
        assertNotNull(foundPerson);
        assertEquals(person, foundPerson);
        List<Person> family = pDAO.findAssociatedPersons(person.getAssociatedUsername());

        assertEquals(expected[0], family.size());
    }

    @Test
    public void runPass4() throws DataAccessException {
        int[] addeds = FamilyGenerator.run(person, 4, db.getConnection());

        final int[] expected = {31, 91};

        assertArrayEquals(expected, addeds);
        Person foundPerson = pDAO.find(person.getPersonID());
        assertNotNull(foundPerson);
        assertEquals(person, foundPerson);
        List<Person> family = pDAO.findAssociatedPersons(person.getAssociatedUsername());

        assertEquals(expected[0], family.size());
    }
}
