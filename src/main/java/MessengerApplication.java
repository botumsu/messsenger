import model.Player;
import service.Chatting;
import service.Initializer;
import service.Registering;

public class MessengerApplication {

    private static Initializer initializer = new Initializer();
    private static Registering registering = new Registering();
    private static Chatting chatting = new Chatting();

    public static void main(String[] args) {

        Player player1 = initializer.initializePlayer();
        Player player2 = initializer.initializePlayer();
        registering.registerPlayer(player1);
        registering.registerPlayer(player2);

        Player initiator = initializer.initializeInitiator(player1, player2);
        chatting.run(initiator, initiator.equals(player1) ? player2 : player1);
    }

}
