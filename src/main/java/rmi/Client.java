package rmi;

import component.Publisher;
import component.Subscriber;
import model.Player;

import java.rmi.RemoteException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static util.EventProvider.eventChannel;

public class Client implements ChatClient {

    private String userName;
    private Map<String, Player> namePlayers = new LinkedHashMap<>();

    public Client(Player player) {
        this.userName = player.getName();
    }

    @Override
    public void notifyLogin(Player player) throws RemoteException {
        namePlayers.put(player.getName(), player);
        eventChannel.getSubscribers().add(new Subscriber(player, eventChannel));
        eventChannel.getPublishers().add(new Publisher(player, eventChannel));
    }

    @Override
    public void notifyLogout(Player player) throws RemoteException {
        Optional<Subscriber> optionalSubscriber = eventChannel.getSubscribers().stream()
                .filter(subscriber -> subscriber.getEventUpdater().equals(player))
                .findFirst();
        if (optionalSubscriber.isPresent()) {
            eventChannel.getSubscribers().remove(optionalSubscriber.get());
        }
        Optional<Publisher> optionalPublisher = eventChannel.getPublishers().stream()
                .filter(publisher -> publisher.getEventUpdater().equals(player))
                .findFirst();
        if (optionalPublisher.isPresent()) {
            eventChannel.getPublishers().remove(optionalPublisher.get());
        }
        if (namePlayers.containsKey(player.getName())) {
            namePlayers.remove(player.getName());
        }
    }

    @Override
    public Map<String, Player> getPlayers() throws RemoteException {
        return namePlayers;
    }

    public void addPlayer(ChatClient chatClient) {
        try {
            namePlayers.put(chatClient.getName(), new Player(chatClient.getName()));
        } catch (RemoteException e) {
            System.out.println("Clients couldn't be added into players");
        }
    }

    @Override
    public String getName() throws RemoteException {
        return userName;
    }
}
