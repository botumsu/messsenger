package model;

import util.Event;
import util.MessageListener;

public class Player implements MessageListener {

    Messenger messenger = new MessengerProcess();

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public void sendEvent(Event event) {
        messenger.sendEvent(event, this);
    }

    public void receiveEvent(Event event) {
        messenger.receiveEvent(event, this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void onMessage(Event event) {
        receiveEvent(event);
    }
}
