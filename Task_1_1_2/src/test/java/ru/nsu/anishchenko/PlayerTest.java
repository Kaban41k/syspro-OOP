package ru.nsu.anishchenko;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

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
        assertEquals(card.rank.getCost(), player.getPoints());
    }

}
