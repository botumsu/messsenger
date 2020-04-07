package model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.Event;

import static org.mockito.Mockito.*;

public class PlayerTest {
    Player testPlayer;
    Messenger messenger;
    Player testPlayerSpy;

    @BeforeMethod
    public void setUp() {
        testPlayer = new Player("test");
        messenger = mock(MessengerOperator.class);
        testPlayerSpy = spy(testPlayer);
    }

    @Test
    public void testSendEvent() {
        //Arrange
        Event event = mock(Event.class);

        //Act
        testPlayer.sendEvent(event);

        //Assert
        //verify(messenger, times(1)).sendEvent(event, testPlayer);
    }

    @Test
    public void testReceiveEvent() {
    }

    @Test
    public void testGetName() {
    }

    @Test
    public void testOnEvent() {
    }
}
