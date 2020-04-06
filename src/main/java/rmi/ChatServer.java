package rmi;

import util.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatServer extends Remote {

    void register(ChatClient chatClient) throws RemoteException;

    void unregister(ChatClient chatClient) throws RemoteException;

    void sendEvent(ChatClient fromPlayer, Event event) throws RemoteException;

}
