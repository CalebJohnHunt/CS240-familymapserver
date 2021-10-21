package service.result;

import model.Event;

/**
 * Result of /event.
 */
public class GetFamilyEventsResult extends Result {
    /**
     * The events of the user's family.
     */
    private Event[] data;

    /**
     * Creates a successful GetFamilyEventsResult.
     * @param data the events of the user's family.
     */
    public GetFamilyEventsResult(Event[] data) {
        setData(data);
        setSuccess(true);
    }

    /**
     * Creates a failed GetFamilyEventsResult.
     * @param message the reason the call to the API failed.
     */
    public GetFamilyEventsResult(String message) {
        setMessage(message);
        setSuccess(false);
    }

    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
        this.data = data;
    }
}
