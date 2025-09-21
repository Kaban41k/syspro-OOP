package ru.nsu.anishchenko;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public ArrayList<Card> cards = new ArrayList<>();
    public int nCards = 0;

    public Deck() {
        fillDeck();
    }

    private void fillDeck() {
        for (Card.CardSuit suit : Card.CardSuit.values()) {
            for (Card.CardRank rank : Card.CardRank.values()) {
                cards.add(new Card(rank, suit));
                nCards++;
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card getCard() {
        if (nCards == 0) {
            fillDeck();
        }

        return cards.removeLast();
    }

    public void printDeck() {
        for (Card card : cards) {
            System.out.println(card.getName());
        }
    }
}
