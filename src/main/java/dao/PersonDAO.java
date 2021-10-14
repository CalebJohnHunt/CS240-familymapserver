package dao;

import model.Person;

import java.sql.*;
import java.util.List;

/**
 * Access data about persons from the database.
 */
public class PersonDAO {
    /**
     * The connection to the database.
     */
    private final Connection conn;

    /**
     * Creates a person DAO.
     * @param conn the connection to the database.
     */
    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds a person to the database.
     * @param person person to add to database.
     * @throws DataAccessException
     */
    public void insert(Person person) throws DataAccessException {
        String sql = "INSERT INTO Persons (PersonID, AssociatedUsername, FirstName, LastName, Gender, FatherID, MotherID, SpouseID) " +
                "VALUES (?,?,?,?,?,?,?,?);";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting person");
        }
    }

    /**
     * Finds a person matching the ID from the database.
     * @param personID ID of the person to find.
     * @return the person with the matching ID
     * @throws DataAccessException
     */
    public Person find(String personID) throws DataAccessException {
        ResultSet rs = null;
        String sql = "SELECT * FROM Persons WHERE PersonID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Person(rs.getString("PersonID"), rs.getString("AssociatedUsername"),
                                  rs.getString("FirstName"), rs.getString("LastName"),
                                  rs.getString("Gender"), rs.getString("FatherID"),
                                  rs.getString("MotherID"), rs.getString("SpouseID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding person");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Finds all family members of the user including mother, father, and spouse.
     * @param username the username of the user.
     * @return a list of all the family members of the user.
     * @throws DataAccessException
     */
    public List<Person> findFamilyOfPerson(String username) throws DataAccessException { return null; }

    /**
     * Removes a person from the database.
     * @param personID ID of the person to remove.
     * @throws DataAccessException
     */
    public void delete(String personID) throws DataAccessException {}

    /**
     * Removes all persons from the database.
     * @throws DataAccessException
     */
    public void clearTable() throws DataAccessException {
        String sql = "DELETE FROM Persons;";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while clearing Persons table");
        }
    }

}
