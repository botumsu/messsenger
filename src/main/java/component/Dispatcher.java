package component;

import util.Event;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dispatcher {
    ThreadLocal<Map<Event, Set<Subscriber>>> eventToSubscribers = ThreadLocal.withInitial(HashMap::new);

    public void dispatch(Event event, Set<Subscriber> toSubscribers) {
        Map<Event, Set<Subscriber>> eventSubscribers = eventToSubscribers.get();
        eventSubscribers.put(event, toSubscribers);
        eventSubscribers.forEach((events, subscribers) -> {
            subscribers.forEach(subscriber -> subscriber.receiveEvent(events));
        });

        eventSubscribers.remove(event);
    }

}
