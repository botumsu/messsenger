package component;

import util.Event;
import util.MessageListener;

public class Publisher {
    private MessageListener messageListener;

    public Publisher(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public void publish(Event event, EventChannel eventChannel) {
        eventChannel.publish(event, messageListener -> messageListener.equals(this));
    }

    public void addPublisher(EventChannel eventChannel) {
        eventChannel.addPublisher(this);
    }

    public void removePublisher(EventChannel eventChannel) {
        eventChannel.removePublisher(this);
    }

    public MessageListener getMessageListener() {
        return messageListener;
    }
}
