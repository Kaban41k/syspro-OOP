package ru.nsu.anishchenko;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    void checkHiddenCardName() {
        Card card = new Card(Card.CardRank.values()[0], Card.CardSuit.values()[0]);
        card.turnOver();
        assertEquals("<(закрытая карта)>", card.getName());
    }

    @Test
    void checkSetCardCost() {
        Card card = new Card(Card.CardRank.values()[0], Card.CardSuit.values()[0]);
        int c = card.rank.getCost();

        card.rank.setCost(1000);
        assertEquals(c, card.rank.getCost());

        card.rank.setCost(10);
        assertEquals(10, card.rank.getCost());
    }

}
