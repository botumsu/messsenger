import component.EventChannel;
import component.Publisher;
import component.Subscriber;
import model.Player;
import util.Event;

import java.util.concurrent.Executors;

public class MessageApplication {

    public static void main(String[] args) {
        Player player = new Player("mahmut");
        Player player2 = new Player("mehmet");
        EventChannel eventChannel = new EventChannel(Executors.newFixedThreadPool(10));

        Subscriber subscriber = new Subscriber(player, eventChannel);
        Subscriber subscriber2 = new Subscriber(player2, eventChannel);
        subscriber.subscribe(eventChannel);
        subscriber2.subscribe(eventChannel);

        Publisher publisher = new Publisher(subscriber);
        Event event = new Event("selam");

        publisher.publish(event, eventChannel);

    }

}
