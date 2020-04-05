import component.Publisher;
import component.Subscriber;
import model.Player;
import util.Event;

import static util.EventProvider.eventChannel;

public class MessengerApplication {

    public static void main(String[] args) {
        Player player = new Player("mahmut");
        Player player2 = new Player("mehmet");

        Subscriber subsMahmut = new Subscriber(player, eventChannel);
        Subscriber subsMehmet = new Subscriber(player2, eventChannel);
        subsMahmut.register();
        subsMehmet.register();

        Publisher pubMahmut = new Publisher(player, eventChannel);
        Publisher pubMehmet = new Publisher(player, eventChannel);
        pubMahmut.register();
        pubMehmet.register();

        Event event = new Event("selam");

        player.sendEvent(event);

    }

}
