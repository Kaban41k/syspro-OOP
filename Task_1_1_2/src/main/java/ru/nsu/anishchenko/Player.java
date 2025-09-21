package ru.nsu.anishchenko;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand = new ArrayList<>();

    private int points = 0;

    public Card getCardFromDeck(Deck deck) {
        Card newCard = deck.getCard();
        hand.add(newCard);
        recalculatePoints();
        return newCard;
    }

    public String getStringOfHand() {
        StringBuilder str = new StringBuilder("[");
        boolean isPointsHidden = false;

        for (int i = 0; i < hand.size(); i++) {
            str.append(hand.get(i).getName());
            if (i != hand.size() - 1)
                str.append(", ");

            if (hand.get(i).isHidden)
                isPointsHidden = true;
        }

        str.append("]");

        if (!isPointsHidden)
            str.append(" => ").append(getPoints());

        return str.toString();
    }

    public Card getCardFromHand(int i) {
        return hand.get(i);
    }

    public void recalculatePoints() {
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

    public int getPoints() {
        return points;
    }
}
