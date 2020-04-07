package main.component;

import main.component.EventChannel;
import main.component.Publisher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import main.util.Event;
import main.util.EventUpdater;

import static org.mockito.Mockito.*;

public class PublisherTest {
    Publisher testPublisher;
    EventChannel eventChannel;
    EventUpdater eventUpdater;

    @Before
    public void setUp() {
        eventChannel = mock(EventChannel.class);
        eventUpdater = mock(EventUpdater.class);
        testPublisher = new Publisher(eventUpdater, eventChannel);
    }

    @Test
    public void testPublish() {
        //Arrange
        Event event = mock(Event.class);

        //Act
        testPublisher.publish(event);

        //Assert
        verify(eventChannel, times(1)).publish(event, testPublisher);
    }

    @Test
    public void testRegister() {
        //Act
        testPublisher.register();

        //Assert
        verify(eventChannel, times(1)).addPublisher(testPublisher);
    }

    @Test
    public void testUnregister() {
        //Act
        testPublisher.unregister();

        //Assert
        verify(eventChannel, times(1)).removePublisher(testPublisher);
    }

    @Test
    public void testGetEventUpdater() {
        //Arrange
        Publisher testPublisherSpy = spy(testPublisher);
        when(testPublisherSpy.getEventUpdater()).thenReturn(eventUpdater);

        //Act
        EventUpdater actual = testPublisher.getEventUpdater();

        //Assert
        Assert.assertEquals(actual, eventUpdater);
    }
}
