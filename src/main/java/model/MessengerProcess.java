package model;

import component.EventChannel;
import util.Event;
import util.MessageListener;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MessengerProcess implements Messenger, MessageListener {

    private AtomicInteger readMessageCounter = new AtomicInteger(0);
    private AtomicInteger sendMessageCounter = new AtomicInteger(0);

    private AtomicReference<EventChannel> eventChannel = new AtomicReference<>();

    @Override
    public void sendEvent(Event event, Player sender) {
        sendMessageCounter.incrementAndGet();
        System.out.println("Sent message :" + event.getMessage() + "(count: " + sendMessageCounter.get() + ")");
        send(event, sender);
    }

    @Override
    public void receiveEvent(Event event, Player receiver) {
        readMessageCounter.incrementAndGet();
        System.out.println("Read message :" + event.getMessage() + "(count: " + readMessageCounter.get() + " by player:" + receiver.getName() + ")");
        receive(event, receiver);
    }

    private void send(Event event, Player sender) {
        EventChannel currentChannel = eventChannel.get();
        if (readMessageCounter.get() >= 10 && sendMessageCounter.get() >= 10) {

        }
        if (currentChannel != null) {
            currentChannel.publish(event, messageListener -> messageListener.equals(sender));
        }
    }

    private void receive(Event event, Player receiver) {
        EventChannel currentChannel = eventChannel.get();
        if (readMessageCounter.get() >= 10 && sendMessageCounter.get() >= 10) {
        }
    }

    @Override
    public void onMessage(Event event) {
        System.out.println("onMessage :" + event.getMessage());
        readMessageCounter.incrementAndGet();
    }
}
