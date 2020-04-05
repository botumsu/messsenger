package component;

import util.Event;

public class Publisher {

    private Subscriber subscriber;

    public Publisher(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void publish(Event event, EventChannel eventChannel) {
        eventChannel.publish(event, messageListener -> messageListener.equals(subscriber.getMessageListener()));
    }
}
