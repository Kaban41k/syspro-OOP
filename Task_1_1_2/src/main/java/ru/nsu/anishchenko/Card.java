package ru.nsu.anishchenko;

public class Card {
    private static final int MINCOST = 1;
    private static final int MAXCOST = 11;

    public CardRank rank;
    public CardSuit suit;

    private int cost;

    public boolean isHidden = false;

    public Card(CardRank newRank, CardSuit newSuit) {
        rank = newRank;
        suit = newSuit;
        cost = Math.min(rank.ordinal() + 1, 10);
    }

    public String getName() {
        if (!isHidden)
            return rank.getRank() + " " + suit.getSuit() + " (" + cost + ")";
        return "<(закрытая карта)>";
    }

    public void setCost(int newCost) {
        if (MINCOST <= newCost && newCost <= MAXCOST)
            cost = newCost;
    }

    public int getCost() {
        return cost;
    }

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

        public String getRank() { return rank; }

        CardRank(String rank) { this.rank = rank; }
    }

    enum CardSuit {
        HEARTS("Черви"),
        DIAMONDS("Бубны"),
        CLUBS("Трефы"),
        SPADES("Пики");

        private final String suit;

        public String getSuit() { return suit; }

        CardSuit(String suit) { this.suit = suit; }
    }
}
