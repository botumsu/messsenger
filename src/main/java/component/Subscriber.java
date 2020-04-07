package component;

import util.Event;
import util.EventUpdater;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {
    private EventUpdater eventUpdater;
    private EventChannel eventChannel;

    ThreadLocal<List<Event>> receivedEvents = ThreadLocal.withInitial(ArrayList::new);

    public Subscriber(EventUpdater eventUpdater, EventChannel eventChannel) {
        this.eventUpdater = eventUpdater;
        this.eventChannel = eventChannel;
    }

    public void register() {
        eventChannel.addSubscriber(this);
    }

    public void unregister() {
        eventChannel.removeSubscriber(this);
    }

    public void receiveEvent(Event event) {
        List<Event> events = receivedEvents.get();
        events.add(event);
        synchronized (this) {
            eventChannel.getExecutorService().execute(() -> {
                try {
                    events.forEach(eachEvent -> eventUpdater.onEvent(eachEvent));
                    events.remove(event);
                } catch (Exception e) {
                    System.out.print("Receiving event couldn't trigger onEvent function");
                }
            });
        }
    }

    public EventUpdater getEventUpdater() {
        return eventUpdater;
    }
}
