package component;

import util.Event;
import util.MessageListener;

public class Publisher {
    private MessageListener messageListener;
    private EventChannel eventChannel;

    public Publisher(MessageListener messageListener, EventChannel eventChannel) {
        this.messageListener = messageListener;
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

    public MessageListener getMessageListener() {
        return messageListener;
    }
}
