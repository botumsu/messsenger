package component;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.Event;
import util.EventUpdater;

import static org.mockito.Mockito.*;

public class PublisherTest {
    Publisher testPublisher;
    EventChannel eventChannel;
    EventUpdater eventUpdater;

    @BeforeMethod
    public void setUp() {
        eventChannel = mock(EventChannel.class);
        eventUpdater = mock(EventUpdater.class);
        testPublisher = new Publisher(eventUpdater, eventChannel);
    }

    @AfterMethod
    public void tearDown() {
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
