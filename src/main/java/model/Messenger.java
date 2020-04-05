package model;

import util.Event;

public interface Messenger {

    void sendEvent(Event event, Player sender);

    void receiveEvent(Event event, Player receiver);

}
