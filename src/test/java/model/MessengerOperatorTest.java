package model;

import component.EventChannel;
import component.Publisher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.Event;

import java.util.concurrent.CopyOnWriteArraySet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessengerOperatorTest {
    MessengerOperator testMessengerOperator;
    Event event;
    Player player;
    EventChannel eventChannel;

    @BeforeMethod
    public void setUp() {
        event = mock(Event.class);
        player = mock(Player.class);
        eventChannel = mock(EventChannel.class);
        testMessengerOperator = new MessengerOperator(0, 0);
    }

    @Test
    public void testSendEvent() {
        //Arrange
        Event event = mock(Event.class);
        Player player = mock(Player.class);
        Publisher publisher = mock(Publisher.class);
        CopyOnWriteArraySet<Publisher> publishers = new CopyOnWriteArraySet<>();
        publishers.add(publisher);
        when(eventChannel.getPublishers()).thenReturn(publishers);

        //Act
        testMessengerOperator.sendEvent(event, player);

        //Assert
        //verify(publisher, times(1)).publish(event);
    }

    @Test
    public void testReceiveEvent() {
    }
}
