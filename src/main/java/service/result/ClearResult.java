package service.result;

/**
 * Result of /clear.
 */
public class ClearResult extends Result {

    /**
     * Creates a successful ClearResult
     * @param message a message explaining that the API call succeeded.
     * @return a successful ClearResult.
     */
    public static ClearResult createSuccessful(String message) {
        ClearResult inst = new ClearResult();
        inst.setMessage(message);
        inst.setSuccess(true);
        return inst;
    }
}
