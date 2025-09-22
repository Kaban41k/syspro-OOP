package ru.nsu.anishchenko;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void checkHiddenCardName() {
        Card card = new Card(Card.CardRank.values()[0], Card.CardSuit.values()[0]);
        card.turnOver();
        assertEquals("<(закрытая карта)>", card.getName());
    }

    @Test
    void checkSetCardCost() {
        Card card = new Card(Card.CardRank.values()[0], Card.CardSuit.values()[0]);
        int c = card.getCost();

        card.setCost(1000);
        assertEquals(c, card.getCost());

        card.setCost(10);
        assertEquals(10, card.getCost());
    }

    @Test
    void checkDeckGetCard() {
        Card card = new Card(Card.CardRank.values()[0], Card.CardSuit.values()[0]);
        Deck deck = new Deck();

        deck.addCard(card);
        Card cardFromDeck = deck.getCard();

        assertEquals(card, cardFromDeck);
    }

    @Test
    void checkPlayersEmptyHandString() {
        Player player = new Player();

        assertEquals("[] => 0", player.getStringOfHand());
    }

    @Test
    void checkPlayersOneCardHand() {
        Player player = new Player();
        Card card = new Card(Card.CardRank.values()[0], Card.CardSuit.values()[0]);
        Deck deck = new Deck();

        deck.addCard(card);
        player.getCardFromDeck(deck);
        assertEquals(card, player.getCardFromHand(0));
        assertEquals(card.getCost(), player.getPoints());
    }

    @Test
    void checkPlayersInstantWin() {
        Deck deck = new Deck();
        Game game = new Game();

        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.CLUBS));
        deck.addCard(new Card(Card.CardRank.KING, Card.CardSuit.DIAMONDS));
        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.HEARTS));
        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.DIAMONDS));

        assertTrue(game.startNewRound(deck));
    }

    @Test
    void checkDealersInstantWin() {
        Deck deck = new Deck();
        Game game = new Game();

        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.CLUBS));
        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.HEARTS));
        deck.addCard(new Card(Card.CardRank.KING, Card.CardSuit.DIAMONDS));
        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.DIAMONDS));

        String input = "0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertFalse(game.startNewRound(deck));
    }
}