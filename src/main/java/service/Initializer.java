package service;

import model.Player;
import rmi.ChatClient;
import rmi.ChatServer;
import rmi.Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Initializer {

    public Client initializePlayer(ChatServer chatServer, String clientName) throws RemoteException {
        Player player = new Player(clientName);
        Client client = new Client(player);
        registerClient(chatServer, client);
        return client;
    }

    public void registerClient(ChatServer chatServer, Client client) throws RemoteException {
        ChatClient clientStub = (ChatClient) UnicastRemoteObject.exportObject(client, 0);
        chatServer.register(clientStub);
    }

}
