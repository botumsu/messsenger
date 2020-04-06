package model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.Event;

import static org.mockito.Mockito.mock;

public class MessengerProcessTest {
    MessengerProcess testMessengerProcess;
    Event event;
    Player player;

    @BeforeMethod
    public void setUp() {
        event = mock(Event.class);
        player = mock(Player.class);
        testMessengerProcess = new MessengerProcess();
    }

    @Test
    public void testSendEvent() {
        //TODO
    }

    @Test
    public void testReceiveEvent() {
        //TODO
    }
}
