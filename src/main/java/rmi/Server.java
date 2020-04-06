package rmi;

import model.Player;
import util.Event;

import java.rmi.RemoteException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Server implements ChatServer {

    private Map<String, ChatClient> userNameClient = new LinkedHashMap<>();

    @Override
    public void register(ChatClient chatClient) throws RemoteException {
        if (!userNameClient.containsKey(chatClient.getPlayer().getName())) {
            userNameClient.put(chatClient.getPlayer().getName(), chatClient);
            try {
                chatClient.notifyLogin(new Player(chatClient.getPlayer().getName()));
            } catch (RemoteException e) {
                System.out.println("Couldn't register the client");
            }
        }
    }

    @Override
    public void unregister(ChatClient chatClient) throws RemoteException {
        if (userNameClient.containsKey(chatClient.getPlayer().getName())) {
            userNameClient.forEach((name, client) -> {
                try {
                    Player player = client.getPlayer();
                    if (player != null) {
                        client.notifyLogout(player);
                    }
                } catch (RemoteException e) {
                    System.out.println("Couldn't unregister the client");
                }
            });
            userNameClient.remove(chatClient.getPlayer().getName());
        }
    }

    @Override
    public void sendEvent(ChatClient fromPlayer, Event event) throws RemoteException {
        fromPlayer.getPlayer().sendEvent(event);
    }
}
