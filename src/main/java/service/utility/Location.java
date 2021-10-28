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
    private Float latitude;
    /**
     * The longitude.
     */
    private Float longitude;

    public Location(String country, String city, Float latitude, Float longitude) {
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

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
