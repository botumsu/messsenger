package main.model;

import main.util.Event;
import main.util.EventUpdater;

/**
 * @author Suleyman Karatas - karatasuleyman@gmail.com
 * <p>
 * Impletements EventUpdater for onEvent trigger, and model class for Player
 */
public class Player implements EventUpdater {
    /**
     * @param messenger Messenger - Creates its own MessengerOperator with zero sending and receiving counters to access Messenger operations
     * @param name String - Keeps Player name
     */
    Messenger messenger = new MessengerOperator(0, 0);
    private String name;

    /**
     * Constructor of Player
     *
     * @param name String
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Sends event from Player
     *
     * @param event Event
     */
    public void sendEvent(Event event) {
        messenger.sendEvent(event, this);
    }

    /**
     * Receives event for Player
     *
     * @param event Event
     */
    public void receiveEvent(Event event) {
        messenger.receiveEvent(event, this);
    }

    /**
     * Returns Player name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * onEvent triggers receiveEvent for Player
     *
     * @param event Event
     */
    @Override
    public void onEvent(Event event) {
        receiveEvent(event);
    }
}
