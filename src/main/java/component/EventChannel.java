package component;

import util.Event;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class EventChannel extends Dispatcher {

    private ExecutorService executorService;
    CopyOnWriteArraySet<Subscriber> subscribers = new CopyOnWriteArraySet<>();
    CopyOnWriteArraySet<Publisher> publishers = new CopyOnWriteArraySet<>();

    public EventChannel(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void addPublisher(Publisher publisher) {
        publishers.add(publisher);
    }

    public void removePublisher(Publisher publisher) {
        publishers.remove(publisher);
    }

    public void publish(Event event, Publisher publisher) {
        Set<Subscriber> toSubscribers = subscribers.stream()
                .filter(subscriber -> !subscriber.getMessageListener().equals(publisher.getMessageListener()))
                .collect(Collectors.toSet());
        dispatch(event, toSubscribers);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public CopyOnWriteArraySet<Subscriber> getSubscribers() {
        return subscribers;
    }

    public CopyOnWriteArraySet<Publisher> getPublishers() {
        return publishers;
    }
}
