package ru.nsu.anishchenko;

import java.util.ArrayList;

public class Player {
    static ArrayList<Card> hand = new ArrayList<>();
    static int points = 0;

    public static void getCardFromDeck(Deck deck) {
        Card newCard = CardDecoder.decode(deck.getRandomCard());

        if (newCard.cost == 1 && points + 10 <= 21)
            newCard.cost = 10;

        points += newCard.cost;
        hand.add(newCard);
    }
}
