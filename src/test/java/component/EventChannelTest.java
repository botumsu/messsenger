package component;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Event;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

public class EventChannelTest {

    EventChannel testEventChannel;
    ExecutorService executorService;
    EventChannel testEventChannelSpy;

    @Before
    public void setUp() {
        executorService = mock(ExecutorService.class);
        testEventChannel = new EventChannel(executorService);
        testEventChannelSpy = spy(testEventChannel);
    }

    @Test
    public void testAddSubscriber() {
        //Arrange
        Subscriber subscriber = mock(Subscriber.class);

        //Act
        testEventChannel.addSubscriber(subscriber);

        //Assert
        Assert.assertEquals(testEventChannel.getSubscribers().size(), 1);
    }

    @Test
    public void testRemoveSubscriber() {
        //Arrange
        Subscriber subscriber = mock(Subscriber.class);

        //Act
        testEventChannel.removeSubscriber(subscriber);

        //Assert
        Assert.assertEquals(testEventChannel.getSubscribers().size(), 0);
    }

    @Test
    public void testAddPublisher() {
        //Arrange
        Publisher publisher = mock(Publisher.class);

        //Act
        testEventChannel.addPublisher(publisher);

        //Assert
        Assert.assertEquals(testEventChannel.getPublishers().size(), 1);
    }

    @Test
    public void testRemovePublisher() {
        //Arrange
        Publisher publisher = mock(Publisher.class);

        //Act
        testEventChannel.removePublisher(publisher);

        //Assert
        Assert.assertEquals(testEventChannel.getPublishers().size(), 0);
    }

    @Test
    public void testPublish() {
        //Arrange
        Event event = mock(Event.class);
        Publisher publisher = mock(Publisher.class);
        Set<Subscriber> subscribers = new HashSet<>();

        //Act
        testEventChannelSpy.publish(event, publisher);

        //Assert
        verify(testEventChannelSpy, times(1)).dispatch(event, subscribers);
    }

    @Test
    public void testGetExecutorService() {
        //Arrange
        when(testEventChannelSpy.getExecutorService()).thenReturn(executorService);

        //Act
        ExecutorService actual = testEventChannel.getExecutorService();

        //Assert
        Assert.assertEquals(actual, executorService);
    }

    @Test
    public void testGetSubscribers() {
        //Arrange
        CopyOnWriteArraySet<Subscriber> subscribers = new CopyOnWriteArraySet<>();
        when(testEventChannelSpy.getSubscribers()).thenReturn(subscribers);

        //Act
        CopyOnWriteArraySet<Subscriber> actual = testEventChannel.getSubscribers();

        //Assert
        Assert.assertEquals(actual, subscribers);
    }

    @Test
    public void testGetPublishers() {
        //Arrange
        CopyOnWriteArraySet<Publisher> publishers = new CopyOnWriteArraySet<>();
        when(testEventChannelSpy.getPublishers()).thenReturn(publishers);

        //Act
        CopyOnWriteArraySet<Publisher> actual = testEventChannel.getPublishers();

        //Assert
        Assert.assertEquals(actual, publishers);
    }
}
