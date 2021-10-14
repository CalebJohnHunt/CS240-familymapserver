package dao;

import model.Event;
import model.Person;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Maintains the connection to the database.
 */
public class Database {
    /**
     * The connection to the database.
     */
    private Connection conn;

    /**
     * Opens a connection to the database.
     * @return the connection to the database.
     * @throws DataAccessException
     */
    // Whenever we want to make a change to our database we will have to open a connection and use
    // Statements created by that connection to initiate transactions
    public Connection openConnection() throws DataAccessException {
        try {
            // The Structure for this Connection is driver:language:path
            // The path assumes you start in the root of your project unless given a non-relative path
            final String CONNECTION_URL = "jdbc:sqlite:database/familymap.sqlite";

            // Open a database connection to the file given in the path
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }

        return conn;
    }

    /**
     * Gets the connection to the database, and creates it if it does not exist.
     * @return the connection to the database.
     * @throws DataAccessException
     */
    public Connection getConnection() throws DataAccessException {
        if(conn == null) {
            return openConnection();
        } else {
            return conn;
        }
    }

    /**
     * Closes the connection to the database.
     * @throws DataAccessException
     */
    // When we are done manipulating the database it is important to close the connection. This will
    // End the transaction and allow us to either commit our changes to the database or rollback any
    // changes that were made before we encountered a potential error.

    // IMPORTANT: IF YOU FAIL TO CLOSE A CONNECTION AND TRY TO REOPEN THE DATABASE THIS WILL CAUSE THE
    // DATABASE TO LOCK. YOUR CODE MUST ALWAYS INCLUDE A CLOSURE OF THE DATABASE NO MATTER WHAT ERRORS
    // OR PROBLEMS YOU ENCOUNTER
    public void closeConnection(boolean commit) throws DataAccessException {
        try {
            if (commit) {
                // This will commit the changes to the database
                conn.commit();
            } else {
                // If we find out something went wrong, pass a false into closeConnection and this
                // will roll back any changes we made during this connection
                conn.rollback();
            }

            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to close database connection");
        }
    }

    /**
     * Clears every table in the database.
     * @throws DataAccessException
     */
    public void clearTables() throws DataAccessException
    {

        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM Events; DELETE FROM Users; DELETE FROM Persons; DELETE FROM AuthTokens";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }

    /**
     * Clears the old database and loads new data into the tables.
     * @param users list of all users to add to the database.
     * @param persons list of all persons to add to the database.
     * @param events list of all events to add to the database.
     */
    public void loadNewDatabase(List<User> users, List<Person> persons, List<Event> events) {}
}
