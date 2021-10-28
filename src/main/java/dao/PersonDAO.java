package dao;

import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * @throws DataAccessException Error accessing data
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
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting person");
        }
    }

    /**
     * Finds a person matching the ID from the database.
     * @param personID ID of the person to find.
     * @return the person with the matching ID
     * @throws DataAccessException Error accessing data
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

    // NOTE: Not sure if this API should also get multiple generations?
    // I'm going to leave it here for now in case it does.
    /**
     * Finds all family members of the user including mother, father, and spouse (excluding self).
     * @param username the username of the user.
     * @return a list of all the non-null family members of the user.
     * @throws DataAccessException Error accessing data
     */
    public List<Person> findFamilyOfPersonList(String username) throws DataAccessException {
        return new ArrayList<>(findFamilyOfPersonSet(username));
    }

    public Set<Person> findFamilyOfPersonSet(String username) throws DataAccessException {
        Set<Person> familyMembers = new HashSet<>();

        String personID = getPersonIDFromUsername(username);
        findFamilyOfPersonHelper(personID, familyMembers);

        return familyMembers;
    }

    private void findFamilyOfPersonHelper(String personID, Set<Person> familyMembers) throws DataAccessException {
        if (personID == null) {
            return;
        }
        for (Person p : findImmediateFamilyOfPerson(personID)) {
            if (familyMembers.contains(p)) {
                continue;
            }
            familyMembers.add(p);
            findFamilyOfPersonHelper(p.getFatherID(), familyMembers);
            findFamilyOfPersonHelper(p.getMotherID(), familyMembers);
            findFamilyOfPersonHelper(p.getSpouseID(), familyMembers);
        }
    }

    /**
     * Get the PersonID of the User with username
     * @param username identifies the user to find
     * @return the PersonID of the User with username
     * @throws DataAccessException Error accessing data
     */
    public String getPersonIDFromUsername(String username) throws DataAccessException {
        String sql = "SELECT PersonID FROM Persons WHERE AssociatedUsername = ?;";
        ResultSet rs = null;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("PersonID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding user");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * Finds the father, mother, and spouse of the Person with personID.
     * @param personID identifies the Person
     * @return a list of the non-null immediate family members
     * @throws DataAccessException Error accessing data
     */
    private List<Person> findImmediateFamilyOfPerson(String personID) throws DataAccessException {
        String sql = "SELECT FatherID, MotherID, SpouseID FROM Persons WHERE PersonID = ?;";
        ResultSet rs = null;
        List<Person> family = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);

            rs = stmt.executeQuery();

            if (rs.next()) {
                Person member;
                if ((member = find(rs.getString("FatherID"))) != null) {
                    family.add(member);
                }
                if ((member = find(rs.getString("MotherID"))) != null) {
                    family.add(member);
                }
                if ((member = find(rs.getString("SpouseID"))) != null) {
                    family.add(member);
                }
                return family;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding immediate family");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return family;
    }

    /**
     * Removes a person from the database.
     * @param personID ID of the person to remove.
     * @throws DataAccessException Error accessing data
     */
    public void delete(String personID) throws DataAccessException {
        String sql = "DELETE FROM Persons WHERE personID = ?;";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while deleting Person");
        }
    }

    /**
     * Removes all persons from the database.
     * @throws DataAccessException Error accessing data
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
