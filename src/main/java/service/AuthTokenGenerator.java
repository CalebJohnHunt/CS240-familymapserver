package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import model.AuthToken;
import utility.IDGenerator;

import java.sql.Connection;

public class AuthTokenGenerator {
    public static String generate(String username, Connection conn) throws DataAccessException {
        AuthTokenDAO atDAO = new AuthTokenDAO(conn);
        String tokenID = IDGenerator.generate();
        atDAO.insert(new AuthToken(tokenID, username));
        return tokenID;
    }
}
