package service.utility;

/**
 * POJO to hold basic attributes of a location in JSON.
 */
public class Location {
    /**
     * The country.
     */
    private String country;
    /**
     * The city.
     */
    private String city;
    /**
     * The latitude.
     */
    private float latitude;
    /**
     * The longitude.
     */
    private float longitude;

    public Location(String country, String city, float latitude, float longitude) {
        this.country = country;
        this.city = city;
        this.latitude = latitude;
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
}
