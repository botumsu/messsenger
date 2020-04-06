package rmi;

import component.EventChannel;
import component.Publisher;
import component.Subscriber;
import model.Player;
import util.EventProvider;

import java.rmi.RemoteException;
import java.util.Optional;

public class Client implements ChatClient {

    private Player player;

    public Client(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() throws RemoteException {
        return player;
    }

    @Override
    public void notifyLogin(Player player) throws RemoteException {
        EventChannel eventChannel = EventProvider.getInstance();
        eventChannel.addSubscriber(new Subscriber(player, eventChannel));
        eventChannel.addPublisher(new Publisher(player, eventChannel));
    }

    @Override
    public void notifyLogout(Player player) throws RemoteException {
        EventChannel eventChannel = EventProvider.getInstance();
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
    }
}
