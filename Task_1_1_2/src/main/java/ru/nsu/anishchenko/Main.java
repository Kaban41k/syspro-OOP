package ru.nsu.anishchenko;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        for (int i = 0; i < 3; i++) {
            game.startRoundWithDefaultDeck(new Scanner(System.in));
        }
    }
}
