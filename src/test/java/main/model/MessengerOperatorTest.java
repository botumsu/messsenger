package main.model;

import main.component.EventChannel;
import main.component.Publisher;
import main.component.Subscriber;
import main.model.MessengerOperator;
import main.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import main.util.Event;
import main.util.EventProvider;

import java.util.concurrent.CopyOnWriteArraySet;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EventProvider.class)
public class MessengerOperatorTest {
    MessengerOperator testMessengerOperator;
    Event event;
    Player player;
    EventChannel eventChannel;

    @Before
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

        PowerMockito.mockStatic(EventProvider.class);
        PowerMockito.when(EventProvider.getInstance()).thenReturn(eventChannel);
        when(eventChannel.getPublishers()).thenReturn(publishers);
        when(publisher.getEventUpdater()).thenReturn(player);

        //Act
        testMessengerOperator.sendEvent(event, player);

        //Assert
        verify(publisher, times(1)).publish(event);
    }

    @Test
    public void testReceiveAndSendEvent() {
        //Arrange
        Event event = mock(Event.class);
        Player player = mock(Player.class);
        Publisher publisher = mock(Publisher.class);
        CopyOnWriteArraySet<Publisher> publishers = new CopyOnWriteArraySet<>();
        publishers.add(publisher);
        Subscriber subscriber = mock(Subscriber.class);
        CopyOnWriteArraySet<Subscriber> subscribers = new CopyOnWriteArraySet<>();
        subscribers.add(subscriber);

        PowerMockito.mockStatic(EventProvider.class);
        PowerMockito.when(EventProvider.getInstance()).thenReturn(eventChannel);
        when(eventChannel.getPublishers()).thenReturn(publishers);
        when(eventChannel.getSubscribers()).thenReturn(subscribers);
        when(subscriber.getEventUpdater()).thenReturn(player);
        when(publisher.getEventUpdater()).thenReturn(player);
        when(event.getMessage()).thenReturn("test");

        //Act
        testMessengerOperator.receiveEvent(event, player);

        //Assert
        verify(publisher, times(1)).publish(event);
    }

    @Test
    public void testExceedSendingCount() {
        //Arrange
        testMessengerOperator = new MessengerOperator(9, 9);
        Event event = mock(Event.class);
        Player player = mock(Player.class);
        Publisher publisher = mock(Publisher.class);
        CopyOnWriteArraySet<Publisher> publishers = new CopyOnWriteArraySet<>();
        publishers.add(publisher);

        PowerMockito.mockStatic(EventProvider.class);
        PowerMockito.when(EventProvider.getInstance()).thenReturn(eventChannel);
        when(eventChannel.getPublishers()).thenReturn(publishers);
        when(publisher.getEventUpdater()).thenReturn(player);

        //Act
        testMessengerOperator.sendEvent(event, player);

        //Assert
        verify(publisher, times(1)).unregister();
    }

    @Test
    public void testExceedReceivingCount() {
        //Arrange
        testMessengerOperator = new MessengerOperator(9, 9);
        Event event = mock(Event.class);
        Player player = mock(Player.class);
        CopyOnWriteArraySet<Publisher> publishers = new CopyOnWriteArraySet<>();
        Subscriber subscriber = mock(Subscriber.class);
        CopyOnWriteArraySet<Subscriber> subscribers = new CopyOnWriteArraySet<>();
        subscribers.add(subscriber);

        PowerMockito.mockStatic(EventProvider.class);
        PowerMockito.when(EventProvider.getInstance()).thenReturn(eventChannel);
        when(eventChannel.getSubscribers()).thenReturn(subscribers);
        when(subscriber.getEventUpdater()).thenReturn(player);

        PowerMockito.mockStatic(EventProvider.class);
        PowerMockito.when(EventProvider.getInstance()).thenReturn(eventChannel);
        when(eventChannel.getPublishers()).thenReturn(publishers);
        when(event.getMessage()).thenReturn("test");

        //Act
        testMessengerOperator.receiveEvent(event, player);

        //Assert
        verify(subscriber, times(1)).unregister();
    }
}
