package service.request;

/**
 * Request to /fill/[username]/{generations}.
 */
public class FillRequest {
    /**
     * The username of the user to fill in info for.
     */
    private String username;
    /**
     * The number of generations to fill in for the user.
     */
    private int generations;

    /**
     * Creates a FillRequest with the default of 4 generations.
     * @param username the username of the user to fill in info for.
     */
    public FillRequest(String username) {
        this.username = username;
        this.generations = 4;
    }

    /**
     * Creates a FillRequest with the specified number of generations.
     * @param username the username of the user to fill in info for.
     * @param generations the number of generations to fill in for the user.
     */
    public FillRequest(String username, int generations) {
        this.username = username;
        this.generations = generations;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }
}
