package model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import util.Event;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Player.class)
public class PlayerTest {
    Player testPlayer;
    Player testPlayerSpy;

    @Before
    public void setUp() throws Exception {
        testPlayer = new Player("test");

        testPlayerSpy = spy(testPlayer);
    }

    @Test
    public void testSendEvent() throws Exception {
        //Arrange
        Event event = mock(Event.class);
        MessengerOperator messenger = mock(MessengerOperator.class);
        PowerMockito.whenNew(MessengerOperator.class).withAnyArguments().thenReturn(messenger);

        //Act
        //testPlayer.sendEvent(event);

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
