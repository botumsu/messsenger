package component;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.Event;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class DispatcherTest {

    private Dispatcher testDispatcher;

    @BeforeClass
    public void setUp() {
        testDispatcher = new Dispatcher();
    }

    @AfterClass
    public void tearDown() {

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
