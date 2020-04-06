package component;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.EventUpdater;

import java.util.concurrent.Executor;

import static org.mockito.Mockito.*;

public class SubscriberTest {

    Subscriber testSubscriber;
    EventChannel eventChannel;
    EventUpdater eventUpdater;
    Executor executor;

    @BeforeMethod
    public void setUp() {
        eventChannel = mock(EventChannel.class);
        eventUpdater = mock(EventUpdater.class);
        testSubscriber = new Subscriber(eventUpdater, eventChannel);
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testRegister() {
        testSubscriber.register();

        verify(eventChannel, times(1)).addSubscriber(Mockito.any());
    }

    @Test
    public void testUnregister() {
        testSubscriber.unregister();

        verify(eventChannel, times(1)).removeSubscriber(Mockito.any());
    }

    @Test
    public void testReceiveEvent() {
        //TODO
        /*
        //Arrange
        Event event = mock(Event.class);
        ExecutorService executorService = mock(ExecutorService.class);
        List<Event> events = new ArrayList<>();
        events.add(event);
        when(eventChannel.getExecutorService()).thenReturn(executorService);

        //Act
        testSubscriber.receiveEvent(event);

        //verify()
        verify(eventUpdater, times(1)).onEvent(event);*/
    }

    @Test
    public void testGetEventUpdater() {
        //Arrange
        Subscriber testSubscriberSpy = spy(testSubscriber);
        when(testSubscriberSpy.getEventUpdater()).thenReturn(eventUpdater);

        //Act
        EventUpdater actual = testSubscriber.getEventUpdater();

        //Assert
        Assert.assertEquals(actual, eventUpdater);
    }
}
