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
        subsMahmut.subscribe(staticEvent);
        subsMehmet.subscribe(staticEvent);

        Publisher pubMahmut = new Publisher(player);
        Publisher pubMehmet = new Publisher(player);
        pubMahmut.addPublisher(staticEvent);
        pubMehmet.addPublisher(staticEvent);

        Event event = new Event("selam");

        player.sendEvent(event);

    }

}
