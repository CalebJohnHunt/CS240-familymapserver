package dao;

import model.User;

import java.sql.*;

/**
 * Accesses data about users from the database.
 */
public class UserDAO {
    /**
     * The connection to the database.
     */
    private final Connection conn;

    /**
     * Creates a user DAO.
     * @param conn the connection to the database.
     */
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds a user to the database.
     * @param user the user to add to the database.
     * @throws DataAccessException
     */
    public void insert(User user) throws DataAccessException {
        String sql = "INSERT INTO Users (Username, Password, Email, FirstName, LastName, Gender, PersonID) " +
                "VALUES (?,?,?,?,?,?,?);";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting user");
        }
    }

    /**
     * Fills in fake information about a user back to 4 generations.
     * @param username the username of the user
     */
    public void populateData(String username) {
        populateData(username, 4);
    }

    /**
     * Fills in fake information about a user to a specified number of generations.
     * @param username the username of the user.
     * @param generations the number of generations to go back.
     */
    public void populateData(String username, int generations) {}

    /**
     * Validates that the username and password go together and are in the database.
     * @param username the username of the user.
     * @param password the password of the user.
     * @return whether the user's username and password match those in the database.
     * @throws DataAccessException
     */
    public boolean validate(String username, String password) throws DataAccessException { return false; }

    /**
     * Finds a specified user in the database.
     * @param username the username of the user.
     * @return the user matching the username
     * @throws DataAccessException
     */
    public User find(String username) throws DataAccessException {
        String sql = "SELECT * FROM Users WHERE Username = ?;";
        ResultSet rs = null;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("Username"), rs.getString("Password"),
                                rs.getString("Email"), rs.getString("FirstName"),
                                rs.getString("LastName"), rs.getString("Gender"),
                                rs.getString("personID"));
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

        return null;
    }

    /**
     * Removes a specified user from the database.
     * @param username the username of the user to remove
     * @throws DataAccessException
     */
    public void delete(String username) throws DataAccessException {
        String sql = "DELETE FROM Users WHERE Username = ?;";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while deleting User");
        }
    }

    /**
     * Removes all users from the database.
     * @throws DataAccessException
     */
    public void clearTable() throws DataAccessException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM Users";
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while deleting Users table");
        }
    }

}
