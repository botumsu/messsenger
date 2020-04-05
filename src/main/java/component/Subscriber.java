package component;

import util.Event;
import util.MessageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class Subscriber {
    private MessageListener messageListener;
    private Executor executor;

    ThreadLocal<List<Event>> receivedEvents = ThreadLocal.withInitial(ArrayList::new);

    public Subscriber(MessageListener messageListener, EventChannel eventChannel) {
        this.messageListener = messageListener;
        this.executor = eventChannel.getExecutorService();
    }

    public void subscribe(EventChannel eventChannel) {
        eventChannel.addSubscriber(this);
    }

    public void unsubscribe(EventChannel eventChannel) {
        eventChannel.removeSubscriber(this);
    }

    public List<Event> getEvents() {
        return receivedEvents.get();
    }

    public void receiveEvent(Event event) {
        receivedEvents.get().add(event);
        synchronized (this) {
            executor.execute(() -> {
                try {
                    receivedEvents.get().add(event);
                    receivedEvents.get().forEach(eachEvent -> messageListener.onMessage(eachEvent));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public Executor getExecutor() {
        return executor;
    }

    public MessageListener getMessageListener() {
        return messageListener;
    }

    public ThreadLocal<List<Event>> getReceivedEvents() {
        return receivedEvents;
    }
}
