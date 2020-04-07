package model;

import util.Event;
import util.EventUpdater;

public class Player implements EventUpdater {

    Messenger messenger = new MessengerOperator(0, 0);

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
