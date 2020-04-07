package main.service;

import main.component.EventChannel;
import main.component.Publisher;
import main.component.Subscriber;
import main.util.EventProvider;
import main.model.Player;

/**
 * @author Suleyman Karatas - karatasuleyman@gmail.com
 * <p>
 * Register Players to Subscriber and Publisher in EventChannel
 */
public class Registering {

    /**
     * Registers player as new Subscriber and new Publisher to EventChannel.
     *
     * @param player Player
     */
    public void registerPlayer(Player player) {
        EventChannel eventChannel = EventProvider.getInstance();
        Subscriber subscriber = new Subscriber(player, eventChannel);
        Publisher publisher = new Publisher(player, eventChannel);
        subscriber.register();
        publisher.register();
    }
}
