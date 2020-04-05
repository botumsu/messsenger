package rmi;

import model.Player;
import util.Event;

import java.rmi.RemoteException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Server implements ChatServer {

    private Map<String, ChatClient> userNameClient = new LinkedHashMap<>();

    @Override
    public ChatClient register(ChatClient chatClient) throws RemoteException {
        if (!userNameClient.containsKey(chatClient.getName())) {
            userNameClient.put(chatClient.getName(), chatClient);

            userNameClient.forEach((name, client) -> {
                try {
                    client.notifyLogin(new Player(name));
                } catch (RemoteException e) {
                    System.out.println(e);
                    System.out.println("Couldn't register the client");
                }
            });
        }
        return userNameClient.get(chatClient.getName());
    }

    @Override
    public void unregister(ChatClient chatClient) throws RemoteException {
        if (userNameClient.containsKey(chatClient.getName())) {
            userNameClient.forEach((name, client) -> {
                try {
                    Player player = client.getPlayers().get(client.getName());
                    if (player != null) {
                        client.notifyLogout(player);
                    }
                } catch (RemoteException e) {
                    System.out.println("Couldn't unregister the client");
                }
            });
            userNameClient.remove(chatClient.getName());
        }
    }

    @Override
    public void sendEvent(ChatClient fromPlayer, Event event) throws RemoteException {
        fromPlayer.getPlayers().forEach((s, player) -> {
            player.sendEvent(event);
        });
    }
}
