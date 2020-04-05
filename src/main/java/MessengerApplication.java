import component.Publisher;
import component.Subscriber;
import model.Player;
import util.Event;

import static util.EventProvider.staticEvent;

public class MessengerApplication {

    public static void main(String[] args) {
        Player player = new Player("mahmut");
        Player player2 = new Player("mehmet");

        Subscriber subsMahmut = new Subscriber(player, staticEvent);
        Subscriber subsMehmet = new Subscriber(player2, staticEvent);
        subsMahmut.register();
        subsMehmet.register();

        Publisher pubMahmut = new Publisher(player, staticEvent);
        Publisher pubMehmet = new Publisher(player, staticEvent);
        pubMahmut.register();
        pubMehmet.register();

        Event event = new Event("selam");

        player.sendEvent(event);

    }

}
