package main.component;

import main.component.Dispatcher;
import main.component.Subscriber;
import org.junit.Before;
import org.junit.Test;
import main.util.Event;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class DispatcherTest {

    private Dispatcher testDispatcher;

    @Before
    public void setUp() {
        testDispatcher = new Dispatcher();
    }

    @Test
    public void testDispatch() {
        //Arrange
        Event event = mock(Event.class);
        Subscriber subscriber = mock(Subscriber.class);
        Set<Subscriber> subscribers = new HashSet<>();
        subscribers.add(subscriber);

        //Act
        testDispatcher.dispatch(event, subscribers);

        //Assert
        verify(subscriber, atLeast(1)).receiveEvent(event);
    }
}
