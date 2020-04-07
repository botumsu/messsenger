package model;

import component.EventChannel;
import component.Publisher;
import component.Subscriber;
import util.Event;
import util.EventProvider;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MessengerOperator implements Messenger {

    private AtomicInteger readMessageCounter;
    private AtomicInteger sendMessageCounter;

    public MessengerOperator(int readMessageCounter, int sendMessageCounter) {
        this.readMessageCounter = new AtomicInteger(readMessageCounter);
        this.sendMessageCounter = new AtomicInteger(sendMessageCounter);
    }

    @Override
    public void sendEvent(Event event, Player sender) {
        sendMessageCounter.incrementAndGet();
        send(event, sender);
    }

    @Override
    public void receiveEvent(Event event, Player receiver) {
        readMessageCounter.incrementAndGet();
        receive(receiver, event);
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
            System.out.println(sender.getName() + " sent message: " + event.getMessage() + ", sending count: " + sendMessageCounter.get());
            if (sendMessageCounter.get() >= 10) {
                currentPublisher.unregister();
            }
        }
    }

    private void receive(Player receiver, Event event) {
        EventChannel eventChannel = EventProvider.getInstance();
        Optional<Subscriber> optionalSubscriber = eventChannel.getSubscribers().stream()
                .filter(subscriber -> subscriber.getEventUpdater().equals(receiver))
                .findFirst();
        if (optionalSubscriber.isPresent()) {
            StringBuilder receivedMessage = new StringBuilder(event.getMessage());
            System.out.println(receiver.getName() + " received message: " + receivedMessage.toString() + ", receiving count: " + readMessageCounter.get());
            event.setMessage(receivedMessage.append(" ").append(readMessageCounter).toString());
            if (readMessageCounter.get() >= 10) {
                optionalSubscriber.get().unregister();
            }
        }
    }
}
