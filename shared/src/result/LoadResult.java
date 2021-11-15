package result;

/**
 * Result of /load
 */
public class LoadResult extends Result {

    /**
     * Creates a LoadResult.
     * @param message a message explaining that the API call succeeded or why it failed.
     */
    public LoadResult(Boolean success, String message) {
        setMessage(message);
        setSuccess(success);
    }
}
