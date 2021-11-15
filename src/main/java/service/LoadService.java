package service;

import dao.DataAccessException;
import dao.EventDAO;
import dao.PersonDAO;
import dao.UserDAO;
import request.LoadRequest;
import result.LoadResult;

/**
 * Service for /load.
 */
public class LoadService extends Service {

    /**
     * Calls the /load API.
     * @param request the request to the /load API.
     * @return the response from the /load API.
     */
    public LoadResult load(LoadRequest request) throws DataAccessException {
        int userCount   = 0;
        int personCount = 0;
        int eventCount  = 0;

        try {
            db.openConnection();
            db.clearTables();

            UserDAO uDAO = new UserDAO(db.getConnection());
            for (var o : request.getUsers()) {
                uDAO.insert(o);
                ++userCount;
            }

            PersonDAO pDAO = new PersonDAO(db.getConnection());
            for (var o : request.getPersons()) {
                pDAO.insert(o);
                ++personCount;
            }

            EventDAO eDAO = new EventDAO(db.getConnection());
            for (var o : request.getEvents()) {
                eDAO.insert(o);
                ++eventCount;
            }

            db.closeConnection(true);
            return new LoadResult(true, "Successfully added "+  userCount +" users, "+ personCount +" persons, and " + eventCount +" events to the database.");
        } catch (DataAccessException e) {
            e.printStackTrace(); // TODO: Logger
            db.closeConnection(false);
            return new LoadResult(false, "Error: Server error.");
        }
    }
}
