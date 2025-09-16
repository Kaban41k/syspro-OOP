package ru.nsu.anishchenko;

public class Main {
    public static void main(String[] args) {
        Deck mainDeck = new Deck();

        for (int i = 0; i < 2; i++)
            Player.getCardFromDeck(mainDeck);

        for (int i = 0; i < 2; i++)
            System.out.print(Player.hand.get(i).name + " (" + Player.hand.get(i).cost + ")  ");

        System.out.print("=> " + Player.points);
    }
}
