package service;

import rmi.ChatClient;
import rmi.ChatServer;
import rmi.Client;
import util.Event;

import java.rmi.RemoteException;
import java.util.Scanner;

import static util.EventProvider.eventChannel;

public class Chatting {
    public void run(ChatServer chatServer, Client initiator, Client otherClient) throws RemoteException {
        Client nextPlayer = initiator;
        System.out.println("Chat is Starting...");
        while (!eventChannel.getPublishers().isEmpty()) {
            System.out.println("Enter Message for " + nextPlayer.getName());
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            chatServer.sendEvent(nextPlayer, new Event(message));
            nextPlayer = switchPlayer(nextPlayer, initiator, otherClient);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }
        System.exit(0);
    }

    private static Client switchPlayer(Client currentClient, Client client1, Client client2) {
        return currentClient.equals(client1) ? client2 : client1;
    }
}
