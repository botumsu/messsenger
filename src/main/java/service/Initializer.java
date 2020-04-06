package service;

import model.Player;

import java.util.Scanner;

public class Initializer {

    public Player initializePlayer(String playerName) {
        return new Player(playerName);
    }
}
