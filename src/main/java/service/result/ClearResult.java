package service.result;

/**
 * Result of /clear.
 */
public class ClearResult extends Result {

    /**
     * Creates a ClearResult
     * @param message a message explaining that the API call succeeded or why it failed.
     */
    public ClearResult(Boolean success, String message) {
        setMessage(message);
        setSuccess(success);
    }
}
