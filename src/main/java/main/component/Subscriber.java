package main.component;

import main.util.Event;
import main.util.EventUpdater;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Suleyman Karatas - karatasuleyman@gmail.com
 * <p>
 * Handler of Subscriber
 */
public class Subscriber {
    /**
     * @param eventUpdated EventUpdater
     * @param eventChannel EventChannel
     * @param receivedEvents List - Keeps Events which are sent by EventChannel via Dispatcher with Thread safe
     */
    private EventUpdater eventUpdater;
    private EventChannel eventChannel;
    ThreadLocal<List<Event>> receivedEvents = ThreadLocal.withInitial(ArrayList::new);

    /**
     * Constructor of Subscriber
     *
     * @param eventUpdater EventUpdater- Binding with relation with Player, so Player can be added as Subscriber
     * @param eventChannel EventChannel - Channel to make communication between Publishers via Dispatcher
     */
    public Subscriber(EventUpdater eventUpdater, EventChannel eventChannel) {
        this.eventUpdater = eventUpdater;
        this.eventChannel = eventChannel;
    }

    /**
     * Register Subscriber to EventChannel Storage
     */
    public void register() {
        eventChannel.addSubscriber(this);
    }

    /**
     * Unregister Subscriber from EventChannel Storage
     */
    public void unregister() {
        eventChannel.removeSubscriber(this);
    }

    /**
     * Handles received messages with Thread executor and triggers onEvent for Player to receive it
     *
     * @param event Event - Event stores message
     */
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

    /**
     * Returns Player which is saved as Subscriber
     *
     * @return EventUpdater
     */
    public EventUpdater getEventUpdater() {
        return eventUpdater;
    }
}
