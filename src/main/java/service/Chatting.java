package service;

import component.EventChannel;
import rmi.ChatServer;
import rmi.Client;
import util.Event;
import util.EventProvider;

import java.rmi.RemoteException;

public class Chatting {
    public void run(ChatServer chatServer, Client initiator, Event event) throws RemoteException {
        System.out.println("Chat is Starting...");
        EventChannel eventChannel = EventProvider.getInstance();
        chatServer.sendEvent(initiator, event);
        while (!eventChannel.getPublishers().isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }
        System.exit(0);
    }
}
