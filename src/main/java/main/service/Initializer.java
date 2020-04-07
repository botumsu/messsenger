package main.service;

import main.model.Player;

/**
 * @author Suleyman Karatas - karatasuleyman@gmail.com
 * <p>
 * Initilizing Players
 */
public class Initializer {

    /**
     * Initializes Player according to player name
     *
     * @param playerName String
     * @return Player
     */
    public Player initializePlayer(String playerName) {
        return new Player(playerName);
    }
}
