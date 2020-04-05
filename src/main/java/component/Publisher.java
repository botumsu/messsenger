package component;

import util.Event;
import util.EventUpdater;

public class Publisher {
    private EventUpdater eventUpdater;
    private EventChannel eventChannel;

    public Publisher(EventUpdater eventUpdater, EventChannel eventChannel) {
        this.eventUpdater = eventUpdater;
        this.eventChannel = eventChannel;
    }

    public void publish(Event event) {
        eventChannel.publish(event, this);
    }

    public void register() {
        eventChannel.addPublisher(this);
    }

    public void unregister() {
        eventChannel.removePublisher(this);
    }

    public EventUpdater getEventUpdater() {
        return eventUpdater;
    }
}
