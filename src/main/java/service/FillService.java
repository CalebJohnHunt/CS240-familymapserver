package service;

import dao.DataAccessException;
import dao.PersonDAO;
import model.Person;
import service.request.FillRequest;
import service.result.FillResult;
import service.utility.FamilyGenerator;

import java.util.Random;

/**
 * Service for /fill/[username]/{generations}.
 */
public class FillService extends Service {
    private static final Random random = new Random();

    /**
     * Calls the /fill/[username]/{generations}.
     * @param request the request to the API.
     * @return the response from the API.
     */
    public FillResult fill(FillRequest request) throws DataAccessException {
        PersonDAO pDAO = new PersonDAO(db.getConnection());
        try {
            Person person = pDAO.find(pDAO.getPersonIDFromUsername(request.getUsername()));
            if (person == null) {
                db.closeConnection(false);
                return new FillResult(false, "Error: Username not found.");
            }

            // Remove all the family members of the user. Because of SQLite foreign keys, their events come down with
            //  the persons.
            for (Person p : pDAO.findFamilyOfPersonList(request.getUsername())) {
                pDAO.delete(p.getPersonID());
            }

            pDAO.delete(person.getPersonID());

            // This will set up the person with new parents!
            int[] itemsAdded = FamilyGenerator.run(person, request.getGenerations(), db.getConnection());

            db.closeConnection(true);
            return new FillResult(true, "Successfully added " + itemsAdded[0]
                    + " persons and " + itemsAdded[1] + " events to the database");
        } catch (DataAccessException e) {
            e.printStackTrace(); // TODO: Logger
            db.closeConnection(false);
            return new FillResult(false, "Error: Server error.");
        }
    }
}
