package ru.nsu.anishchenko;

import java.util.Scanner;

public class Game {
    private Player dealer = new Player();
    private Player player = new Player();

    private Deck deck;

    private int roundN = 0;
    private boolean isGameStopped = true;

    private int dealerPoints = 0;
    private int playerPoints = 0;

    public Game() {
        print("Добро пожаловать в Блэкджек!\n");
    }

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

    private void dealCards() {
        if (isGameStopped) return;

        for (int i = 0; i < 2; i++) {
            player.getCardFromDeck(deck);
            dealer.getCardFromDeck(deck);
        }

        dealer.getCardFromHand(1).turnOver();

        print("Дилер раздал карты");
        printHands();

        if (player.getPoints() == 21) isGameStopped = true;
    }

    private void playerMove() {
        if (isGameStopped) return;

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
        scanner.close();
    }

    private void dealerMove() {
        if (isGameStopped) return;

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

    private void printHands() {
        print("     Ваши карты: " + player.getStringOfHand());
        print("     Карты дилера: " + dealer.getStringOfHand());
    }

    private void print(String str) {
        System.out.println(str);
    }
}
