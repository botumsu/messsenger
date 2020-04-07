package main.component;

import main.util.Event;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Suleyman Karatas - karatasuleyman@gmail.com
 * <p>
 * Handler of dispatching event to subscribers
 */
public class Dispatcher {
    /**
     * @param eventToSubscribers Set - Stores Map for Event and Subscribers with Thread safe
     */
    ThreadLocal<Map<Event, Set<Subscriber>>> eventToSubscribers = ThreadLocal.withInitial(HashMap::new);

    /**
     * Dispatches events to Subscribers
     *
     * @param event         Event - event contains message
     * @param toSubscribers Set - Subscribers to send event
     */
    public void dispatch(Event event, Set<Subscriber> toSubscribers) {
        Map<Event, Set<Subscriber>> eventSubscribers = eventToSubscribers.get();
        eventSubscribers.put(event, toSubscribers);
        eventSubscribers.forEach((events, subscribers) -> subscribers.forEach(subscriber -> subscriber.receiveEvent(events)));

        eventSubscribers.remove(event);
    }
}
