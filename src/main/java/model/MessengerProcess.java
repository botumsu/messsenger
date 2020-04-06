package model;

import component.EventChannel;
import component.Publisher;
import component.Subscriber;
import util.Event;
import util.EventProvider;

import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MessengerProcess implements Messenger, Serializable {

    private static final long serialVersionUID = 6257373018834408757L;

    private AtomicInteger readMessageCounter = new AtomicInteger(0);
    private AtomicInteger sendMessageCounter = new AtomicInteger(0);

    @Override
    public void sendEvent(Event event, Player sender) {
        sendMessageCounter.incrementAndGet();
        send(event, sender);
    }

    @Override
    public void receiveEvent(Event event, Player receiver) {
        readMessageCounter.incrementAndGet();
        System.out.println(receiver.getName() + " received message :" + event.getMessage() + ", receiving count: " + readMessageCounter.get());
        receive(receiver);
        sendEvent(event, receiver);
    }

    private void send(Event event, Player sender) {
        EventChannel eventChannel = EventProvider.getInstance();
        Optional<Publisher> optionalPublisher = eventChannel.getPublishers().stream()
                .filter(publisher -> publisher.getEventUpdater().equals(sender))
                .findFirst();
        if (optionalPublisher.isPresent()) {
            Publisher currentPublisher = optionalPublisher.get();
            currentPublisher.publish(event);
            System.out.println(sender.getName() + " sent message :" + event.getMessage() + ", sending count: " + sendMessageCounter.get());
            if (sendMessageCounter.get() >= 10) {
                eventChannel.getPublishers().remove(currentPublisher);
            }
        }
    }

    private void receive(Player receiver) {
        EventChannel eventChannel = EventProvider.getInstance();
        if (readMessageCounter.get() >= 10) {
            Optional<Subscriber> optionalSubscriber = eventChannel.getSubscribers().stream()
                    .filter(subscriber -> subscriber.getEventUpdater().equals(receiver))
                    .findFirst();
            if (optionalSubscriber.isPresent()) {
                eventChannel.getSubscribers().remove(optionalSubscriber.get());
            }
        }
    }
}
