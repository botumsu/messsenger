package main.component;

import main.util.Event;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * @author Suleyman Karatas - karatasuleyman@gmail.com
 * <p>
 * EventChannel stores subscribers and publishers. Also provide channel to Dispatcher
 */
public class EventChannel extends Dispatcher {
    /**
     * @param executorService ExecutorService - Thread executor service
     * @param subscribers CopyOnWriteArraySet<Subscriber> - stores subscribers
     * @param publishers CopyOnWriteArraySet<Publisher> - stores publishers
     */
    private ExecutorService executorService;
    CopyOnWriteArraySet<Subscriber> subscribers = new CopyOnWriteArraySet<>();
    CopyOnWriteArraySet<Publisher> publishers = new CopyOnWriteArraySet<>();

    /**
     * Constructor of EventChannel
     *
     * @param executorService ExecutorService
     */
    public EventChannel(ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * Adds Subscriber to Subscriber Set
     *
     * @param subscriber Subsriber
     */
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * Removes Subscriber from Subscriber Set
     *
     * @param subscriber Subscriber
     */
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Adds Publisher to Publisher Set
     *
     * @param publisher Publisher
     */
    public void addPublisher(Publisher publisher) {
        publishers.add(publisher);
    }

    /**
     * Removes Publisher from Publisher Set
     *
     * @param publisher Publisher
     */
    public void removePublisher(Publisher publisher) {
        publishers.remove(publisher);
    }

    /**
     * Publisher sends event to Subscribers by  using dispatch of Dispatcher
     *
     * @param event     Event
     * @param publisher Publisher
     */
    public void publish(Event event, Publisher publisher) {
        Set<Subscriber> toSubscribers = subscribers.stream()
                .filter(subscriber -> !subscriber.getEventUpdater().equals(publisher.getEventUpdater()))
                .collect(Collectors.toSet());
        dispatch(event, toSubscribers);
    }

    /**
     * Returns Executor Service
     *
     * @return ExecutorService
     */
    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * Returns Subscriber Set
     *
     * @return CopyOnWriteArraySet
     */
    public CopyOnWriteArraySet<Subscriber> getSubscribers() {
        return subscribers;
    }

    /**
     * Returns Publisher Set
     *
     * @return CopyOnWriteArraySet
     */
    public CopyOnWriteArraySet<Publisher> getPublishers() {
        return publishers;
    }

}
