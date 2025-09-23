package ru.nsu.anishchenko;

import java.util.ArrayList;

/**
 * A man at a table with a hand of cards class.
 */
public class Player {
    private final ArrayList<Card> hand;

    private int points;

    /**
     * Constructs a Player with the specified name.
     */
    public Player() {
        hand = new ArrayList<>();
        points = 0;
    }

    /**
     * Get a card from the deck.
     *
     * @param deck deck from which you need to get a card
     * @return card from deck
     */
    public Card getCardFromDeck(Deck deck) {
        Card newCard = deck.getCard();
        hand.add(newCard);
        recalculatePoints();
        return newCard;
    }

    /**
     * Structures the hand into a string form.
     *
     * @return a string of the names of all the cards in the hand and player point.
     */
    public String getStringOfHand() {
        StringBuilder str = new StringBuilder("[");
        boolean isPointsHidden = false;

        for (int i = 0; i < hand.size(); i++) {
            str.append(hand.get(i).getName());
            if (i != hand.size() - 1) {
                str.append(", ");
            }

            if (hand.get(i).isHidden) {
                isPointsHidden = true;
            }
        }

        str.append("]");

        if (!isPointsHidden) {
            str.append(" => ").append(getPoints());
        }

        return str.toString();
    }

    /**
     * Get card from hand.
     *
     * @param i card index
     * @return card with index i from hand array
     */
    public Card getCardFromHand(int i) {
        return hand.get(i);
    }

    /**
     * Recalculate hand points.
     */
    private void recalculatePoints() {
        int newPoints = 0;
        ArrayList<Card> aces = new ArrayList<>();

        for (Card card : hand) {
            if (card.rank.toString().equals("ACE")) {
                aces.add(card);
                continue;
            }

            newPoints += card.getCost();
        }

        for (Card ace : aces) {
            if (newPoints <= 10) {
                newPoints += 11;
                ace.setCost(11);
            } else {
                newPoints += 1;
                ace.setCost(1);
            }
        }

        points = newPoints;
    }

    /**
     * Get points.
     *
     * @return points
     */
    public int getPoints() {
        return points;
    }
}
