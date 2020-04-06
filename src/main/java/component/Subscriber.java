package component;

import util.Event;
import util.EventUpdater;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class Subscriber {
    private EventUpdater eventUpdater;
    private Executor executor;
    private EventChannel eventChannel;

    ThreadLocal<List<Event>> receivedEvents = ThreadLocal.withInitial(ArrayList::new);

    public Subscriber(EventUpdater eventUpdater, EventChannel eventChannel) {
        this.eventUpdater = eventUpdater;
        this.eventChannel = eventChannel;
        this.executor = eventChannel.getExecutorService();
    }

    public void register() {
        eventChannel.addSubscriber(this);
    }

    public void unregister() {
        eventChannel.removeSubscriber(this);
    }

    public List<Event> getEvents() {
        return receivedEvents.get();
    }

    public void receiveEvent(Event event) {
        List<Event> events = receivedEvents.get();
        events.add(event);
        synchronized (this) {
            executor.execute(() -> {
                try {
                    events.forEach(eachEvent -> eventUpdater.onEvent(eachEvent));
                } catch (Exception e) {
                    System.out.println("Receiving event couldn't trigger onEvent function");
                }
            });
        }
    }

    public Executor getExecutor() {
        return executor;
    }

    public EventUpdater getEventUpdater() {
        return eventUpdater;
    }

    public ThreadLocal<List<Event>> getReceivedEvents() {
        return receivedEvents;
    }
}
