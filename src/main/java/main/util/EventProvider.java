package main.util;

import main.component.EventChannel;

import java.util.concurrent.Executors;

/**
 * @author Suleyman Karatas - karatasuleyman@gmail.com
 * <p>
 * Thread Safe Singleton Class to give access EventChannel
 */
public class EventProvider {
    /**
     * @param eventChannel EventChannel
     */
    private static EventChannel eventChannel;

    /**
     * Constructor of EventProvider
     */
    private EventProvider() {
    }

    /**
     * Returns Instance of EventChannel, if there is no any, it creates new one for once.
     *
     * @return EventChannel
     */
    public static synchronized EventChannel getInstance() {
        if (eventChannel == null) {
            eventChannel = new EventChannel(Executors.newFixedThreadPool(10));
        }
        return eventChannel;
    }
}
