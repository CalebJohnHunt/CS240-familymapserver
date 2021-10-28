package service;

import dao.Database;

/**
 * Parent class of Services to hold an instance of a database.
 */
public abstract class Service {
    /**
     * The way to interact with the database.
     */
    protected Database db;

    protected Service() {
        this.db = new Database();
    }
}
