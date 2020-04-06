package service;

import model.Player;

import java.util.Scanner;

public class Initializer {

    public Player initializePlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player name: ");
        String playerName = scanner.nextLine();
        return new Player(playerName);
    }

    public Player initializeInitiator(Player player1, Player player2) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which Player will be Initiator: 1 OR 2");
        System.out.println("1) " + player1.getName());
        System.out.println("2) " + player2.getName());
        int initiatorSelection = scanner.nextInt();
        while (initiatorSelection != 1 && initiatorSelection != 2) {
            System.out.println("Please Enter Initiator as 1 for Player1, 2 for Player 2: ");
            initiatorSelection = scanner.nextInt();
        }
        return initiatorSelection == 1 ? player1 : player2;
    }
}
