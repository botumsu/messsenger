package model;

import util.Event;
import util.EventUpdater;

import java.io.Serializable;

public class Player implements EventUpdater {

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

    @Override
    public void onEvent(Event event) {
        receiveEvent(event);
    }
}
