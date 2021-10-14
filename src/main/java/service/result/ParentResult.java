package service.result;

/**
 * The base result class. Includes way to created failed results.
 */
public class ParentResult {
    /**
     * The message explaining why the API failed or succeeded.
     */
    private String message;
    /**
     * Whether the API was successful.
     */
    private boolean success;

    /**
     * Creates a failed result; to be used by all child classes when the result fails.
     * @param message the message explaining why the API failed.
     * @return a failed result.
     */
    public static ParentResult createFailed(String message) {
        ParentResult inst = new ParentResult();
        inst.setMessage(message);
        inst.setSuccess(false);
        return inst;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
