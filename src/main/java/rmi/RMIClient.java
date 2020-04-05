package rmi;

import model.Player;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIClient {

    private static ChatServer chatServer;
    private static ChatClient clientStub;

    public static void main(String[] args) throws IOException, RemoteException, AlreadyBoundException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry("localhost", 6666);
        if (registry == null) {
            System.err.println("RMI Server is not running");
            return;
        }
        chatServer = (ChatServer) registry.lookup("RMI_SERVER");

        registerClient(chatServer, new Client(new Player("firstPlayer")));
        registerClient(chatServer, new Client(new Player("secondPlayer")));

        System.out.println("RMI Client connected to RMI Server");
    }

    private static void registerClient(ChatServer chatServer, Client client) throws RemoteException {
        clientStub = (ChatClient) UnicastRemoteObject.exportObject(client, 0);
        List<ChatClient> currentUsers = chatServer.register(clientStub);
        client.addPlayers(currentUsers);
    }
}
