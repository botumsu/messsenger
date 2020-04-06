package util;

import component.EventChannel;

import java.util.concurrent.Executors;

public class EventProvider {
    private static EventChannel eventChannel;

    private EventProvider() {
    }

    public static synchronized EventChannel getInstance() {
        if (eventChannel == null) {
            eventChannel = new EventChannel(Executors.newFixedThreadPool(10));
        }
        return eventChannel;
    }
}
