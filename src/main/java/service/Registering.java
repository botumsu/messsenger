package service;

import component.Publisher;
import component.Subscriber;
import model.Player;

import static util.EventProvider.eventChannel;

public class Registering {
    public void registerPlayer(Player player) {
        Subscriber subscriber = new Subscriber(player, eventChannel);
        Publisher publisher = new Publisher(player, eventChannel);
        subscriber.register();
        publisher.register();
    }
}
