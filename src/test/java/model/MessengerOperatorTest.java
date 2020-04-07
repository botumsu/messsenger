package model;

import component.EventChannel;
import component.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import util.Event;
import util.EventProvider;

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
    public void testReceiveEvent() {
    }
}
