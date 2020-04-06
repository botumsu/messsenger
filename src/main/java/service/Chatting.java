package service;

import component.EventChannel;
import model.Player;
import util.Event;
import util.EventProvider;

import java.util.Scanner;

public class Chatting {
    public void run(Player initiator, Player otherPlayer) {
        Player nextPlayer = initiator;
        System.out.println("Chat is Starting...");
        EventChannel eventChannel = EventProvider.getInstance();
        while (!eventChannel.getPublishers().isEmpty()) {
            System.out.println("Enter Message for " + nextPlayer.getName());
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            nextPlayer.sendEvent(new Event(message));
            nextPlayer = switchPlayer(nextPlayer, initiator, otherPlayer);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }
        System.exit(0);
    }

    private static Player switchPlayer(Player currentPlayer, Player player1, Player player2) {
        return currentPlayer.equals(player1) ? player2 : player1;
    }
}
