package main.util;

/**
 * @author Suleyman Karatas - karatasuleyman@gmail.com
 * <p>
 * Keeps Event for communication
 */
public class Event {

    /**
     * @param message String - Keeps message
     */
    private String message;

    /**
     * Constructor of Event
     *
     * @param message String
     */
    public Event(String message) {
        this.message = message;
    }

    /**
     * Returns message
     *
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message
     *
     * @param message String
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
