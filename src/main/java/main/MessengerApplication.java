package main;

import main.model.Player;
import main.service.Chatting;
import main.service.Initializer;
import main.service.Registering;
import main.util.Event;

public class MessengerApplication {

    private static Initializer initializer = new Initializer();
    private static Registering registering = new Registering();
    private static Chatting chatting = new Chatting();

    public static void main(String[] args) {

        Player player1 = initializer.initializePlayer(args[0]);
        Player player2 = initializer.initializePlayer(args[1]);
        registering.registerPlayer(player1);
        registering.registerPlayer(player2);
        Player initiator = player1.getName().equals(args[2]) ? player1 : player2;
        Event event = new Event(args[3]);

        chatting.run(initiator, event);
    }
}
