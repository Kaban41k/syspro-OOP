package ru.nsu.anishchenko;

import java.util.Random;

/**
 * Card class.
 */
public class Card {
    private static final int MINCOST = 1;
    private static final int MAXCOST = 11;

    /**
     * Card rank.
     */
    public CardRank rank;
    /**
     * Card suit.
     */
    public CardSuit suit;

    /**
     * Hide card name in getName if true.
     */
    public boolean isHidden = false;

    /**
     * Initializing rank, suit and cost.
     *
     * @param newRank    card rank
     * @param newSuit    card suit
     */
    public Card(CardRank newRank, CardSuit newSuit) {
        rank = newRank;
        suit = newSuit;
    }

    /**
     * Return full card info.
     * Rank suit (cost)
     *
     * @return card rank, suit, cost
     */
    public String getName() {
        if (!isHidden) {
            return rank.getRank() + " " + suit.getSuit() + " (" + rank.getCost() + ")";
        }
        return "<(закрытая карта)>";
    }

    /**
     * Turn over card.
     * Make card hidden or visible.
     */
    public void turnOver() {
        isHidden = !isHidden;
    }

    enum CardRank {
        ACE("Туз", 1),
        TWO("Двойка", 2),
        THREE("Тройка", 3),
        FOUR("Четвёрка", 4),
        FIVE("Пятёрка", 5),
        SIX("Шестёрка", 6),
        SEVEN("Семёрка", 7),
        EIGHT("Восьмёрка", 8),
        NINE("Девятка", 9),
        TEN("Десятка", 10),
        JACK("Валет", 10),
        QUEEN("Королева", 10),
        KING("Король", 10);

        private final String rank;
        public int cost;

        public String getRank() {
            return rank;
        }

        /**
         * Set new cost
         *
         * @param newCost new card cost
         */
        public void setCost(int newCost) {
            if (MINCOST <= newCost && newCost <= MAXCOST) {
                this.cost = newCost;
            }
        }

        /**
         * Return card cost.
         *
         * @return cost
         */
        public int getCost() {
            return this.cost;
        }

        CardRank(String rank, int cost) {
            this.rank = rank;
            this.cost = cost;
        }
    }

    enum CardSuit {
        HEARTS("Черви"),
        DIAMONDS("Бубны"),
        CLUBS("Трефы"),
        SPADES("Пики");

        private final String suit;

        public String getSuit() {
            return suit;
        }

        CardSuit(String suit) {
            this.suit = suit;
        }
    }
}
