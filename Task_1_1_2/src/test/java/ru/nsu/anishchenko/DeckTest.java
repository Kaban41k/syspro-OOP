package ru.nsu.anishchenko;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void checkDeckGetCard() {
        Card card = new Card(Card.CardRank.values()[0], Card.CardSuit.values()[0]);
        Deck deck = new Deck();

        deck.addCard(card);
        Card cardFromDeck = deck.getCard();

        assertEquals(card, cardFromDeck);
    }

}