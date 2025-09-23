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
     * Clear deck and add all types of card.
     */
    public void fillDeck() {
        cards = new ArrayList<>();

        for (Card.CardSuit suit : Card.CardSuit.values()) {
            for (Card.CardRank rank : Card.CardRank.values()) {
                addCard(new Card(rank, suit));
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
     * Add card.
     *
     * @param card card which need to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Get card.
     *
     * @return last card in deck array
     */
    public Card getCard() {
        if (cards.isEmpty()) {
            fillDeck();
        }

        return cards.removeLast();
    }
}
