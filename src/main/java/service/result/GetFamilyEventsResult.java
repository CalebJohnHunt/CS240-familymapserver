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
     * @return a successful GetFamilyEventsResult
     */
    public static GetFamilyEventsResult createSuccessful(Event[] data) {
        GetFamilyEventsResult inst = new GetFamilyEventsResult();
        inst.setData(data);
        inst.setSuccess(true);

        return inst;
    }

    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
        this.data = data;
    }
}
