package service;

import component.Subscriber;
import model.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

public class InitializerTest {

    Initializer testInitializer;

    @Before
    public void setUp() throws Exception {
        testInitializer = new Initializer();
    }

    @Test
    public void initializePlayer() throws Exception {
        //Arrange
        Player player = Mockito.mock(Player.class);
        PowerMockito.whenNew(Player.class).withAnyArguments().thenReturn(player);

        //Act
        //Player actual = testInitializer.initializePlayer(player.getName());

        //Assert
        //Assert.assertEquals(actual, player);
    }
}
