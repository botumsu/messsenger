package main.util;

/**
 * @author Suleyman Karatas - karatasuleyman@gmail.com
 * <p>
 * Interface of EventUpdater
 */
public interface EventUpdater {
    /**
     * Trigger event
     *
     * @param event Event
     */
    void onEvent(Event event);
}
