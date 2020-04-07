package component;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.Event;
import util.EventUpdater;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

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
        //Act
        testSubscriber.register();

        //Assert
        verify(eventChannel, times(1)).addSubscriber(Mockito.any());
    }

    @Test
    public void testUnregister() {
        //Act
        testSubscriber.unregister();

        //Assert
        verify(eventChannel, times(1)).removeSubscriber(Mockito.any());
    }

    @Test
    public void testReceiveEvent() {
        //Arrange
        Event event = mock(Event.class);
        ExecutorService executorService = mock(ExecutorService.class);
        when(eventChannel.getExecutorService()).thenReturn(executorService);

        //Act
        testSubscriber.receiveEvent(event);

        //verify()
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
