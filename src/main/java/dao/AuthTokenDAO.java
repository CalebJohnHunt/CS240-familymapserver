package dao;

import model.AuthToken;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Accesses data about AuthTokens from the database.
 */
public class AuthTokenDAO {
    /**
     * The connection to the database.
     */
    private final Connection conn;

    /**
     * Creates a DAO with a connection to the database
     * @param conn the connection to the database.
     */
    public AuthTokenDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts a new AuthToken into the database.
     * @param authToken AuthToken to insert.
     * @throws DataAccessException Error accessing data
     */
    public void insert(AuthToken authToken) throws DataAccessException {
        String sql = "INSERT INTO AuthTokens (TokenID, AssociatedUsername) VALUES (?,?);";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authToken.getID());
            stmt.setString(2, authToken.getAssociatedUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting AuthToken");
        }
    }

    /**
     * Finds and returns an AuthToken from the database from an AuthToken ID
     * @param authTokenID ID of AuthToken to find.
     * @return the authToken with the matching ID or null if no such authToken exists
     * @throws DataAccessException Error accessing data
     */
    public AuthToken find(String authTokenID) throws DataAccessException {
        String sql = "SELECT * FROM AuthTokens WHERE TokenID = ?;";
        ResultSet rs = null;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authTokenID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new AuthToken(rs.getString("TokenID"), rs.getString("AssociatedUsername"));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while finding AuthToken");
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
     * Finds all the AuthTokens associated with a certain username.
     * @param username the associated username.
     * @return AuthTokens associated with the username.
     */
    public List<AuthToken> findForUser(String username) throws DataAccessException {
        String sql = "SELECT * FROM AuthTokens WHERE AssociatedUsername = ?;";
        List<AuthToken> authTokens = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                authTokens.add(new AuthToken(rs.getString("TokenID"), rs.getString("AssociatedUsername")));
            }

            return authTokens;
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while finding AuthTokens for user");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Removes an AuthToken from the database from an AuthToken ID.
     * @param authTokenID ID of AuthToken to remove.
     * @throws DataAccessException Error accessing data
     */
    public void delete(String authTokenID) throws DataAccessException {
        String sql = "DELETE FROM AuthTokens WHERE TokenID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authTokenID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while deleting AuthToken");
        }
    }

    /**
     * Removes all AuthTokens from the database.
     * @throws DataAccessException Error accessing data
     */
    public void clearTable() throws DataAccessException {
        String sql = "DELETE FROM AuthTokens;";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while clearing AuthToken table");
        }
    }

}
