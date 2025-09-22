package ru.nsu.anishchenko;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        for (int i = 0; i < 3; i++) {
            game.startNewRound();
        }
    }
}
