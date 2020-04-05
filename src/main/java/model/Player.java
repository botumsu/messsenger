package model;

import util.Event;
import util.EventUpdater;

import java.io.Serializable;

public class Player implements EventUpdater, Serializable {

    private static final long serialVersionUID = 6231265419588843692L;

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
