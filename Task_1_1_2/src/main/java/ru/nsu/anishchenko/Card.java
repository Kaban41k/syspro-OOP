package ru.nsu.anishchenko;

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

    private int cost;

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
        cost = Math.min(rank.ordinal() + 1, 10);
    }

    /**
     * Return full card info.
     *
     * @return card rank, suit, cost
     */
    public String getName() {
        if (!isHidden) {
            return rank.getRank() + " " + suit.getSuit() + " (" + cost + ")";
        }
        return "<(закрытая карта)>";
    }

    /**
     * Return full card info.
     * Rank suit (cost)
     *
     * @param newCost new card cost
     */
    public void setCost(int newCost) {
        if (MINCOST <= newCost && newCost <= MAXCOST) {
            cost = newCost;
        }
    }

    /**
     * Return card cost.
     *
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Turn over card.
     * Make card hidden or visible.
     */
    public void turnOver() {
        isHidden = !isHidden;
    }

    enum CardRank {
        ACE("Туз"),
        TWO("Двойка"),
        THREE("Тройка"),
        FOUR("Четвёрка"),
        FIVE("Пятёрка"),
        SIX("Шестёрка"),
        SEVEN("Семёрка"),
        EIGHT("Восьмёрка"),
        NINE("Девятка"),
        TEN("Десятка"),
        JACK("Валет"),
        QUEEN("Королева"),
        KING("Король");

        private final String rank;

        public String getRank() {
            return rank;
        }

        CardRank(String rank) {
            this.rank = rank;
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
