package service.result;

import model.Event;

/**
 * Result of /event/[eventID].
 */
public class FindEventResult extends ParentResult {
    /**
     * The event which was found.
     */
    private Event event;

    /**
     * Creates a successful FindEventResult.
     * @param event the event which was found.
     * @return a successful FindEventResult.
     */
    public static FindEventResult createSuccessful(Event event) {
        FindEventResult inst = new FindEventResult();
        inst.setEvent(event);
        inst.setSuccess(true);

        return inst;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
