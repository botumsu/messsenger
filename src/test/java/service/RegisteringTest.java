package service;

import component.EventChannel;
import component.Publisher;
import component.Subscriber;
import model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import util.EventProvider;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Registering.class, EventProvider.class})
public class RegisteringTest {

    Registering testRegistering;
    EventChannel eventChannel;

    @Before
    public void setUp() {
        eventChannel = mock(EventChannel.class);
        testRegistering = new Registering();
    }

    @Test
    public void registerPlayer() throws Exception {
        //Arrange
        PowerMockito.mockStatic(EventProvider.class);
        PowerMockito.when(EventProvider.getInstance()).thenReturn(eventChannel);
        Player player = mock(Player.class);
        Subscriber subscriber = mock(Subscriber.class);
        Publisher publisher = mock(Publisher.class);
        PowerMockito.whenNew(Subscriber.class).withAnyArguments().thenReturn(subscriber);
        PowerMockito.whenNew(Publisher.class).withAnyArguments().thenReturn(publisher);

        //Act
        testRegistering.registerPlayer(player);

        //Assert
        verify(subscriber, times(1)).register();
        verify(publisher, times(1)).register();
    }
}
