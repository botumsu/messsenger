package main.model;

import main.util.Event;

/**
 * @author Suleyman Karatas - karatasuleyman@gmail.com
 * <p>
 * Interface to event actions
 */
public interface Messenger {

    /**
     * Provides sending event action to player
     *
     * @param event  Event
     * @param sender Player
     */
    void sendEvent(Event event, Player sender);

    /**
     * Provides receiving event action for player
     *
     * @param event    Event
     * @param receiver Player
     */
    void receiveEvent(Event event, Player receiver);

}
