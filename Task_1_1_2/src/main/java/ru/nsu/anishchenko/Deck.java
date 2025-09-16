package ru.nsu.anishchenko;

import java.util.Random;

public class Deck {
    public boolean[][] cardsInDeck =
            {{true, true, true, true, true, true, true, true, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, true, true, true, true, true}};

    public int nCardsInDeck = 52;


    public int[] getRandomCard() {
        if (nCardsInDeck == 0) {
            return new int[]{-1, -1};
        }

        Random random = new Random();

        int randomRow;
        int randomCol;

        do {
            randomRow = random.nextInt(4);
            randomCol = random.nextInt(13);
        } while (!cardsInDeck[randomRow][randomCol]);

        cardsInDeck[randomRow][randomCol] = false;
        nCardsInDeck--;

        return new int[]{randomRow, randomCol};
    }

    private void printDeck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                System.out.print(" " + cardsInDeck[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
