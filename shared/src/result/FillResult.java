package result;

/**
 * Result of /fill/[username]/{generations}.
 */
public class FillResult extends Result {

    /**
     * Creates a FillResult.
     * @param message a message explaining that the API call succeeded or why it failed.
     */
    public FillResult(Boolean success, String message) {
        setMessage(message);
        setSuccess(success);
    }
}
