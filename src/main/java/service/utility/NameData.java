package service.utility;

/**
 * POJO to represent JSON arrays of names.
 */
public class NameData {
    /**
     * Array of names.
     */
    private String[] data;

    public NameData(String[] data) {
        this.data = data;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
