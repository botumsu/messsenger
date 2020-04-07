package main.component;

import main.util.Event;
import main.util.EventUpdater;

/**
 * @author Suleyman Karatas - karatasuleyman@gmail.com
 * <p>
 * Handler of Publisher
 */
public class Publisher {
    /**
     * @param eventUpdater EventUpdater
     * @param eventChannel EventChannel
     */
    private EventUpdater eventUpdater;
    private EventChannel eventChannel;

    /**
     * Constructor of Publisher, so Publisher can be initialize with Player and communication binding
     *
     * @param eventUpdater EventUpdater - Binding with relation with Player, so Player can be added as Publisher
     * @param eventChannel EventChannel - Channel to make communication between Subscribers via Dispatcher
     */
    public Publisher(EventUpdater eventUpdater, EventChannel eventChannel) {
        this.eventUpdater = eventUpdater;
        this.eventChannel = eventChannel;
    }

    /**
     * Publishes event to EventChannel
     *
     * @param event Event
     */
    public void publish(Event event) {
        eventChannel.publish(event, this);
    }

    /**
     * Register Publisher to EventChannel Storage
     */
    public void register() {
        eventChannel.addPublisher(this);
    }

    /**
     * Unregister Publisher from EventChannel Storage
     */
    public void unregister() {
        eventChannel.removePublisher(this);
    }

    /**
     * Returns Player which is saved as Publisher
     *
     * @return EventUpdater
     */
    public EventUpdater getEventUpdater() {
        return eventUpdater;
    }
}
