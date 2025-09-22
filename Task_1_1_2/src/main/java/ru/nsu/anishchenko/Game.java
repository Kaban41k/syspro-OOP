package ru.nsu.anishchenko;

import java.util.Scanner;

/**
 * Blackjack game.
 */
public class Game {
    private Player dealer = new Player();
    private Player player = new Player();

    private Deck deck;

    private int roundN = 0;
    private boolean isGameStopped = true;

    private int dealerPoints = 0;
    private int playerPoints = 0;

    /**
     * Print welcome.
     */
    public Game() {
        print("Добро пожаловать в Блэкджек!\n");
    }

    /**
     * New round of blackjack.
     */
    public void startNewRound() {
        isGameStopped = false;

        deck = new Deck();
        deck.shuffle();

        dealer = new Player();
        player = new Player();

        print("-".repeat(10) + " Раунд " + roundN + " " + "-".repeat(10));

        dealCards();
        playerMove();
        dealerMove();

        endRound();
    }

    /**
     * Deal two cards to the player and the dealer.
     */
    private void dealCards() {
        if (isGameStopped) {
            return;
        }

        for (int i = 0; i < 2; i++) {
            player.getCardFromDeck(deck);
            dealer.getCardFromDeck(deck);
        }

        dealer.getCardFromHand(1).turnOver();

        print("Дилер раздал карты");
        printHands();

        if (player.getPoints() == 21) isGameStopped = true;
    }

    /**
     * Player's Turn.
     * The player get a card until he stops, win or lose.
     */
    private void playerMove() {
        if (isGameStopped) {
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int msg;

        print("\n--Ваш ход--");
        print("Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...");
        while ((msg = scanner.nextInt()) != 0) {
            if (msg != 1) {
                print("Неверный ввод...");
                continue;
            }

            print("Вы открыли карту " + player.getCardFromDeck(deck).getName());
            printHands();

            if (player.getPoints() > 21) {
                isGameStopped = true;
                break;
            }

            print("\nВведите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...");
        }
    }

    /**
     * Dealer's Turn.
     * The dealer get a card until the points are greater than or equal to 17.
     */
    private void dealerMove() {
        if (isGameStopped) {
            return;
        }

        print("\n--Ход дилера--");
        dealer.getCardFromHand(1).turnOver();
        print("Дилер открывает закрытую карту " + dealer.getCardFromHand(1).getName() + "\n");
        printHands();

        while (dealer.getPoints() < 17) {
            print("Дилер открывает карту " + dealer.getCardFromDeck(deck).getName());
            printHands();
            print("");
        }

        if (dealer.getPoints() > 21) {
            isGameStopped = true;
        }
    }

    /**
     * Stop the round and determine the winner.
     */
    private void endRound() {
        isGameStopped = true;
        roundN++;

        print("");

        if (player.getPoints() > 21 || dealer.getPoints() <= 21 && player.getPoints() < dealer.getPoints()) {
            print("Дилер выиграл раунд!");
            dealerPoints++;
        } else {
            print("Вы выиграли раунд!");
            playerPoints++;
        }

        print("Счёт " + playerPoints + ":" + dealerPoints);
    }

    /**
     * Print the cards and points in the player's and dealer's hands.
     */
    private void printHands() {
        print("     Ваши карты: " + player.getStringOfHand());
        print("     Карты дилера: " + dealer.getStringOfHand());
    }

    /**
     * Just short print.
     */
    private void print(String str) {
        System.out.println(str);
    }
}
