package ru.nsu.anishchenko;

public class CardDecoder {
    private static final String[] suits = {"Червы", "Бубны", "Трефы", "Пики"};
    private static final String[] values =
            {"Туз", "Двойка", "Тройка", "Четвёрка", "Пятёрка", "Шестёрка", "Семёрка",
                    "Восьмёрка", "Девятка", "Десятка", "Валет", "Дама", "Король"};


    public static Card decode(int[] cardCode) {
        String name = values[cardCode[1]] + " " + suits[cardCode[0]];
        int cost;

        if (cardCode[1] > 9) cost = 10;
        else cost = cardCode[1] + 1;

        Card decodedCard = new Card();
        decodedCard.name = name;
        decodedCard.cost = cost;

        return decodedCard;
    }
}
