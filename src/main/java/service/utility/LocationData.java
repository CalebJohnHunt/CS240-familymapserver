package service.utility;

/**
 * POJO to represent a JSON array of Locations.
 */
public class LocationData {
    /**
     * The Locations stored in a JSON array.
     */
    private Location[] data;

    public LocationData(Location[] data) {
        this.data = data;
    }

    public Location[] getData() {
        return data;
    }

    public void setData(Location[] data) {
        this.data = data;
    }
}
