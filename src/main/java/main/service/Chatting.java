package main.service;

import main.component.EventChannel;
import main.model.Player;
import main.util.Event;
import main.util.EventProvider;

/**
 * @author Suleyman Karatas - karatasuleyman@gmail.com
 * <p>
 * Starting chat application
 */
public class Chatting {

    /**
     * Runs program until there is no any Publishers in EventChannel
     *
     * @param initiator Player - Player which sends the first Event
     * @param event     Event
     */
    public void run(Player initiator, Event event) {
        System.out.println("Chat is Starting...");
        EventChannel eventChannel = EventProvider.getInstance();
        initiator.sendEvent(event);
        while (!eventChannel.getPublishers().isEmpty()) {
            //Waiting for message ending process
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }
        System.exit(0);
    }
}
