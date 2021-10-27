package model;

/**
 * An event which occurred to a person in the database.
 */
public class Event {
    /**
     * Unique ID to this event.
     */
    private String eventID;
    /**
     * The username of the user to which this event occurred.
     */
    private String associatedUsername;
    /**
     * The unique ID of the person to which this event occurred.
     */
    private String personID;
    /**
     * The latitude of the location of this event.
     */
    private float latitude;
    /**
     * The longitude of the location of this event.
     */
    private float longitude;
    /**
     * The country in which this event occurred.
     */
    private String country;
    /**
     * The city in which this event occurred.
     */
    private String city;
    /**
     * The type of event.
     */
    private String eventType;
    /**
     * The year when this event occurred.
     */
    private int year;

    /**
     * Creates a new event.
     * @param eventID the unique ID to this event.
     * @param associatedUsername the username of the user to which this event occurred.
     * @param personID the unique ID of the person to which this event occurred.
     * @param latitude the latitude of the location of this event.
     * @param longitude the longitude of the location of this event.
     * @param country the country in which this event occurred.
     * @param city the city in which this event occurred.
     * @param eventType the type of event.
     * @param year the year in which this event occurred.
     */
    public Event(String eventID, String associatedUsername, String personID, float latitude, float longitude,
                 String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        Event oe = (Event) obj;

        return oe.getEventID().equals(getEventID()) &&
                oe.getAssociatedUsername().equals(getAssociatedUsername()) &&
                oe.getPersonID().equals(getPersonID()) &&
                oe.getLatitude() == getLatitude() &&
                oe.getLongitude() == getLongitude() &&
                oe.getCountry().equals(getCountry()) &&
                oe.getCity().equals(getCity()) &&
                oe.getEventType().equals(getEventType()) &&
                oe.getYear() == getYear();
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventID='" + eventID + '\'' +
                ", associatedUsername='" + associatedUsername + '\'' +
                ", personID='" + personID + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", eventType='" + eventType + '\'' +
                ", year=" + year +
                '}';
    }
}
