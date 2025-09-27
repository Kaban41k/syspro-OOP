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

    @Test
    void checkDeckFill() {
        Deck deck = new Deck();
        deck.fillDeck();

        Deck filledDeck = new Deck();

        for (Card.CardSuit suit : Card.CardSuit.values()) {
            for (Card.CardRank rank : Card.CardRank.values()) {
                filledDeck.addCard(new Card(rank, suit));
            }
        }

        assertEquals(52, deck.cards.size());

        for (int i = 0; i < 52; i++) {
            Card cardExpected = filledDeck.getCard();
            Card cardActual = deck.getCard();

            assertEquals(cardExpected.rank, cardActual.rank);
            assertEquals(cardExpected.suit, cardActual.suit);
        }
    }

}