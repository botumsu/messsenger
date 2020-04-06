package service;

import model.Player;
import rmi.ChatClient;
import rmi.ChatServer;
import rmi.Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Initializer {

    public Client initializePlayer(ChatServer chatServer) throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        Client client = new Client(player);
        client.notifyLogin(player);
        registerClient(chatServer, client);
        return client;
    }

    private void registerClient(ChatServer chatServer, Client client) throws RemoteException {
        ChatClient clientStub = (ChatClient) UnicastRemoteObject.exportObject(client, 0);
        ChatClient addedClient = chatServer.register(clientStub);
        client.addPlayer(addedClient);
    }

    public Client initializeInitiator(Client client1, Client client2) throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which Player will be Initiator: 1 OR 2");
        System.out.println("1) " + client1.getName());
        System.out.println("2) " + client2.getName());
        int initiatorSelection = scanner.nextInt();
        while (initiatorSelection != 1 && initiatorSelection != 2) {
            System.out.println("Please Enter Initiator as 1 for Player1, 2 for Player 2: ");
            initiatorSelection = scanner.nextInt();
        }
        return initiatorSelection == 1 ? client1 : client2;
    }
}
