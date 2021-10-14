package service.result;

/**
 * Result of /load
 */
public class LoadResult extends ParentResult {

    /**
     * Creates a successful LoadResult.
     * @param message a message explaining that the API call succeeded.
     * @return a successful LoadResult.
     */
    public static LoadResult createSuccessful(String message) {
        LoadResult inst = new LoadResult();
        inst.setMessage(message);
        inst.setSuccess(true);
        return inst;
    }
}
