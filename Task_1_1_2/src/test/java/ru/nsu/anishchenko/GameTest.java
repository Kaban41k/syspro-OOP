package ru.nsu.anishchenko;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void checkPlayersInstantWin() {
        Deck deck = new Deck();
        Game game = new Game();

        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.CLUBS));
        deck.addCard(new Card(Card.CardRank.KING, Card.CardSuit.DIAMONDS));
        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.HEARTS));
        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.DIAMONDS));

        String input = "";

        assertEquals(Game.GameResult.PLAYERWIN, game.startNewRound(deck, new Scanner(input)));
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

        assertEquals(Game.GameResult.PLAYERLOSE, game.startNewRound(deck, new Scanner(input)));
    }

    @Test
    void checkDraw() {
        Deck deck = new Deck();
        Game game = new Game();

        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.CLUBS));
        deck.addCard(new Card(Card.CardRank.NINE, Card.CardSuit.HEARTS));
        deck.addCard(new Card(Card.CardRank.NINE, Card.CardSuit.DIAMONDS));
        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.DIAMONDS));

        String input = "0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertEquals(Game.GameResult.DRAW, game.startNewRound(deck, new Scanner(input)));
    }

    @Test
    void checkPlayersWin() {
        Deck deck = new Deck();
        Game game = new Game();

        deck.addCard(new Card(Card.CardRank.EIGHT, Card.CardSuit.CLUBS));
        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.CLUBS));
        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.HEARTS));
        deck.addCard(new Card(Card.CardRank.EIGHT, Card.CardSuit.HEARTS));
        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.DIAMONDS));

        String input = "1\n0";

        assertEquals(Game.GameResult.PLAYERWIN, game.startNewRound(deck, new Scanner(input)));
    }

    @Test
    void checkDealersWin() {
        Deck deck = new Deck();
        Game game = new Game();

        deck.addCard(new Card(Card.CardRank.SIX, Card.CardSuit.CLUBS));
        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.CLUBS));
        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.HEARTS));
        deck.addCard(new Card(Card.CardRank.EIGHT, Card.CardSuit.HEARTS));
        deck.addCard(new Card(Card.CardRank.ACE, Card.CardSuit.DIAMONDS));

        String input = "1\n0";

        assertEquals(Game.GameResult.PLAYERLOSE, game.startNewRound(deck, new Scanner(input)));
    }
}