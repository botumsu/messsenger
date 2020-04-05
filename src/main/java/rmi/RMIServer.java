package rmi;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    public static void main(String[] args) throws IOException, RemoteException, AlreadyBoundException {

        System.out.println("Starting RMI server");
        LocateRegistry.createRegistry(6666);

        Server chatServer = new Server();
        ChatServer serverStub = (ChatServer) UnicastRemoteObject.exportObject(chatServer, 0);

        // Bind the remote object's stub in the registry
        Registry registry = LocateRegistry.getRegistry(6666);
        registry.bind("RMI_SERVER", serverStub);

        System.out.println("RMI Server is started");
    }
}
