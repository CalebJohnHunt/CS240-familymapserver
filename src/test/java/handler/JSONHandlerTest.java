package handler;

import model.AuthToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONHandlerTest {
    private AuthToken exAuthToken;

    @BeforeEach
    public void setup() {
        exAuthToken = new AuthToken("example_id", "example_username");
    }

    @Test
    public void objectToJsonPass() {
        String expected = "{\"ID\":\"example_id\",\"associatedUsername\":\"example_username\"}";
        String actual = JSONHandler.objectToJson(exAuthToken);
        assertEquals(expected, actual);
    }

    @Test
    public void objectToJsonNullPass() {
        String expected = "{\"ID\":\"example_id\"}";
        String actual = JSONHandler.objectToJson(new AuthToken(exAuthToken.getID(), null));
        assertEquals(expected, actual);
    }
}
