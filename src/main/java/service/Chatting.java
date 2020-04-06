package service;

import component.EventChannel;
import model.Player;
import util.Event;
import util.EventProvider;

public class Chatting {
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
