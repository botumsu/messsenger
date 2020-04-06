package rmi;

import service.Chatting;
import service.Initializer;
import util.Event;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {

    private static Initializer initializer = new Initializer();
    private static Chatting chatting = new Chatting();

    public static void main(String[] args) throws IOException, RemoteException, AlreadyBoundException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry("localhost", 6666);
        if (registry == null) {
            System.err.println("RMI Server is not running");
            return;
        }
        ChatServer chatServer = (ChatServer) registry.lookup("RMI_SERVER");

        Client client1 = initializer.initializePlayer(chatServer, args[0]);
        Client client2 = initializer.initializePlayer(chatServer, args[1]);
        Client initiator = client1.getPlayer().getName().equals(args[2]) ? client1 : client2;
        Event event = new Event(args[3]);

        chatting.run(chatServer, initiator, event);
    }

}
