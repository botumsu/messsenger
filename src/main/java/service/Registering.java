package service;

import component.EventChannel;
import component.Publisher;
import component.Subscriber;
import model.Player;
import util.EventProvider;

public class Registering {
    public void registerPlayer(Player player) {
        EventChannel eventChannel = EventProvider.getInstance();
        Subscriber subscriber = new Subscriber(player, eventChannel);
        Publisher publisher = new Publisher(player, eventChannel);
        subscriber.register();
        publisher.register();
    }
}
