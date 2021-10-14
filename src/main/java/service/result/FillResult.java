package service.result;

/**
 * Result of /fill/[username]/{generations}.
 */
public class FillResult extends ParentResult {

    /**
     * Creates a successful FillResult.
     * @param message a message explaining that the API call succeeded.
     * @return a successful FillResult.
     */
    public static FillResult createSuccessful(String message) {
        FillResult inst = new FillResult();
        inst.setMessage(message);
        inst.setSuccess(true);
        return inst;
    }
}
