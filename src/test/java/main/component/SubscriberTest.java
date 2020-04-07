package main.component;

import main.component.EventChannel;
import main.component.Subscriber;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import main.util.Event;
import main.util.EventUpdater;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

public class SubscriberTest {

    Subscriber testSubscriber;
    EventChannel eventChannel;
    EventUpdater eventUpdater;
    ExecutorService executorService;
    ByteArrayOutputStream systemOut = new ByteArrayOutputStream();
    @Before
    public void setUp() {
        eventChannel = mock(EventChannel.class);
        eventUpdater = mock(EventUpdater.class);
        executorService = mock(ExecutorService.class);
        implementAsExecutor(executorService);
        testSubscriber = new Subscriber(eventUpdater, eventChannel);
        System.setOut(new PrintStream(systemOut));
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
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
        when(eventChannel.getExecutorService()).thenReturn(executorService);

        //Act
        testSubscriber.receiveEvent(event);

        //verify()
        verify(eventUpdater, times(1)).onEvent(event);
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

    @Test
    public void testReceiveEventThrowException() {
        //Arrange
        Event event = mock(Event.class);
        when(eventChannel.getExecutorService()).thenReturn(executorService);
        doThrow(Exception.class).when(eventUpdater).onEvent(event);

        //Act
        testSubscriber.receiveEvent(event);

        //Assert
        Assert.assertEquals("Receiving event couldn't trigger onEvent function", systemOut.toString());
    }

    protected void implementAsExecutor(ExecutorService executor) {
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).when(executor).execute(any(Runnable.class));
    }
}
