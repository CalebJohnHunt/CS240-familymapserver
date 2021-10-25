package service;

import dao.Database;

public class Service {
    protected Database db;

    protected Service() {
        db = new Database();
    }
}
