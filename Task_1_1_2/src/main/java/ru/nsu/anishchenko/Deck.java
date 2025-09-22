package ru.nsu.anishchenko;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Deck of cards class.
 */
public class Deck {
    /**
     * Array of cards.
     */
    public ArrayList<Card> cards = new ArrayList<>();
    /**
     * Number of cards in the deck.
     */
    public int numOfCards = 0;

    /**
     * Fill deck array.
     */
    public Deck() {
        fillDeck();
    }

    /**
     * Add all types of card in deck array.
     */
    private void fillDeck() {
        for (Card.CardSuit suit : Card.CardSuit.values()) {
            for (Card.CardRank rank : Card.CardRank.values()) {
                cards.add(new Card(rank, suit));
                numOfCards++;
            }
        }
    }

    /**
     * Shuffle deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Get card.
     *
     * @return last card in deck array
     */
    public Card getCard() {
        if (numOfCards == 0) {
            fillDeck();
        }

        numOfCards--;

        return cards.remove(cards.size() - 1);
    }

    /**
     * Print all cards name in deck array.
     */
    public void printDeck() {
        for (Card card : cards) {
            System.out.println(card.getName());
        }
    }
}
