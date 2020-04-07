package main.model;

import main.component.EventChannel;
import main.component.Publisher;
import main.component.Subscriber;
import main.util.Event;
import main.util.EventProvider;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Suleyman Karatas - karatasuleyman@gmail.com
 * <p>
 * Implementation for Messenger, provides actions for event which has relationship with Player
 */
public class MessengerOperator implements Messenger {

    /**
     * @param readMessageCounter AtomicInteger - Threadsafe Integer
     * @param sendMessageCounter AtomicInteger - Threadsafe Integer
     */
    private AtomicInteger receiveEventCounter;
    private AtomicInteger sendEventCounter;

    /**
     * @param receiveEventCounter int - Keeps received events count for Player
     * @param sendEventCounter    int - Keeps sent events count for Player
     */
    public MessengerOperator(int receiveEventCounter, int sendEventCounter) {
        this.receiveEventCounter = new AtomicInteger(receiveEventCounter);
        this.sendEventCounter = new AtomicInteger(sendEventCounter);
    }

    /**
     * Sends event from Player and increases sending counter
     *
     * @param event  Event
     * @param sender Player
     */
    @Override
    public void sendEvent(Event event, Player sender) {
        sendEventCounter.incrementAndGet();
        send(event, sender);
    }

    /**
     * Receives event for Player and increase receiving counter. After receiving event, it sends back.
     *
     * @param event    Event
     * @param receiver Player
     */
    @Override
    public void receiveEvent(Event event, Player receiver) {
        receiveEventCounter.incrementAndGet();
        receive(receiver, event);
        sendEvent(event, receiver);
    }

    /**
     * Gets Publisher according to Player, and publishes the event and checks sending counter to unregister it or not
     * Also it displays player name, event and sending count
     *
     * @param event  Event
     * @param sender Player
     */
    private void send(Event event, Player sender) {
        EventChannel eventChannel = EventProvider.getInstance();
        Optional<Publisher> optionalPublisher = eventChannel.getPublishers().stream()
                .filter(publisher -> publisher.getEventUpdater().equals(sender))
                .findFirst();
        if (optionalPublisher.isPresent()) {
            Publisher currentPublisher = optionalPublisher.get();
            currentPublisher.publish(event);
            System.out.println(sender.getName() + " sent message: " + event.getMessage() + ", sending count: " + sendEventCounter.get());
            if (sendEventCounter.get() >= 10) {
                currentPublisher.unregister();
            }
        }
    }

    /**
     * Gets Subscriber according to Player, and checks receiving counter to unregister it or not.
     * Also it displays player name, event and receiving counter
     *
     * @param receiver
     * @param event
     */
    private void receive(Player receiver, Event event) {
        EventChannel eventChannel = EventProvider.getInstance();
        Optional<Subscriber> optionalSubscriber = eventChannel.getSubscribers().stream()
                .filter(subscriber -> subscriber.getEventUpdater().equals(receiver))
                .findFirst();
        if (optionalSubscriber.isPresent()) {
            StringBuilder receivedMessage = new StringBuilder(event.getMessage());
            System.out.println(receiver.getName() + " received message: " + receivedMessage.toString() + ", receiving count: " + receiveEventCounter.get());
            event.setMessage(receivedMessage.append(" ").append(receiveEventCounter).toString());
            if (receiveEventCounter.get() >= 10) {
                optionalSubscriber.get().unregister();
            }
        }
    }
}
