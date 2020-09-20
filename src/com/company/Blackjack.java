package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Blackjack Version 0.2
 * Simplified blackjack. Dealer VS Player.
 * No splitting yet.
 */

public class Blackjack {

    private final static String VERSION = "0.2";

    Deck D = new Deck();

    ArrayList<Cards> playerHand = new ArrayList<>();
    ArrayList<Cards> dealerHand = new ArrayList<>();

    public void playHand() throws InterruptedException{

        System.out.println("Dealing hands...");
        emptyHand();
        D.shuffleDeck();

        dealHand();

        checkHand();

        while(!isBust(playerHand)) {

            if (handValue(playerHand) == 21) break;

            Scanner in = new Scanner(System.in);
            System.out.println("\nWhat will you do? (H = Hit, S = Stay, X = Split, C = Check hands)");
            System.out.print(">");
            String sel = in.nextLine();

            switch (sel) {
                case "H","h" -> hit(playerHand);
                case "S","s" -> {
                    stay();
                    return;
                }
                case "C","c" -> checkHand();
                case "X","x" -> {
                    if (canSplit(playerHand)) {
                        playSplitHand();
                        return;
                    }
                    else System.out.println("You cannot split! Try again.");
                }
                case "cheat dealer" -> {
                    System.out.print("The dealer's hidden card is: ");
                    dealerHand.get(0).printRaw();
                }
                case "cheat deck" -> {
                    System.out.print("The top card of the deck is: ");
                    D.printRaw(0);
                }
                case "help" -> System.out.println("Blackjack Version: " + VERSION + "\nCard values: A = Ace | J = Jack | Q = Queen | K = King\nCard suits: S = Spades | H = Hearts | C = Clubs | D = Diamonds");
                default -> System.out.print("Invalid option. Try again.");
            }
        }

        if (isBust(playerHand)) System.out.println("Busted. You lose!");
        else {

            stay();
        }
    }

    public void playSplitHand() {

    }

    public void dealHand() {

        for (int i = 0; i < 2; i++) {

            playerHand.add(D.Deck.get(0));
            D.Deck.remove(0);
            dealerHand.add(D.Deck.get(0));
            D.Deck.remove(0);
        }
    }

    public void checkHand() {

        System.out.print("Your hand: ");

        for (Cards cards : playerHand) {

            cards.printRaw();
        }

        System.out.println("\nYour hand is worth " + handValue(playerHand) + " points.");

        System.out.print("Dealer hand: [??] ");
        dealerHand.get(1).printRaw();
    }

    public void hit(ArrayList<Cards> hand) {

        hand.add(D.Deck.get(0));
        System.out.print("Hit: ");
        D.Deck.get(0).printRaw();
        D.Deck.remove(0);

        System.out.print("| New hand: ");

        for (Cards cards : hand) {

            cards.printRaw();
        }

        System.out.println("\nHand is now worth " + handValue(hand) + " points.");
    }

    public void stay() throws InterruptedException{

        System.out.print("Dealer flips: ");
        dealerHand.get(0).printRaw();
        System.out.print("\nDealer hand: ");
        for (Cards cards : dealerHand) {

            cards.printRaw();
        }
        System.out.println("\nDealer hand is worth " + handValue(dealerHand) + " points.");
        Thread.sleep(1750);

        while (handValue(dealerHand) < handValue(playerHand)) {

            if (handValue(dealerHand) == 21) break;

            hit(dealerHand);
            Thread.sleep(2250);
        }

        if (isBust(dealerHand)) System.out.println("Dealer busted. You win!");
        else if (handValue(dealerHand) == handValue(playerHand)) {
            System.out.println("It's a tie!");
        }
        else System.out.println("The dealer outscored you. You lose!");
    }

    public void split(ArrayList<Cards> hand) {


    }

    public Boolean canSplit(ArrayList<Cards> hand) {

        return hand.get(0).getCardValue().equals(hand.get(1).getCardValue());
    }

    public int handValue(ArrayList<Cards> hand) {

        int sum = 0;

        ArrayList<Cards> clone = new ArrayList<>(hand);
        clone.removeIf(cards -> cards.getCardValue().equals("A"));

        if (numberOfAces(hand) > 0) {

            for (Cards cards : clone) sum += cards.getNumericBJ();
            switch(numberOfAces(hand)) {

                case 1 -> {
                    int a = sum + 1;
                    int b = sum + 11;
                    if (b > a && b <= 21) return b;
                    else return a;
                }
                case 2 -> {
                    int a = sum + 2;
                    int b = sum + 12;
                    if (b > a && b <= 21) return b;
                    else return a;
                }
                case 3 -> {
                    int a = sum + 3;
                    int b = sum + 13;
                    if (b > a && b <= 21) return b;
                    else return a;
                }
                case 4 -> {
                    int a = sum + 4;
                    int b = sum + 14;
                    if (b > a && b <= 21) return b;
                    else return a;
                }
                default -> {
                    System.out.println("Something went wrong. Exiting...");
                    System.exit(0);
                }
            }
        }

        for (Cards cards : hand) sum += cards.getNumericBJ();
        return sum;
    }

    public Boolean isBust(ArrayList<Cards> hand) {

        return handValue(hand) > 21;
    }

    public Boolean hasAce(ArrayList<Cards> hand) {

        for (Cards cards : hand) {

            if (cards.getCardValue().equals("A")) return true;
        }

        return false;
    }

    public int aceIndex(ArrayList<Cards> hand) {

        for (int i = 0; i < hand.size(); i++) {

            if (hand.get(i).getCardValue().equals("A")) return i;
        }

        return -1;
    }

    public int numberOfAces(ArrayList<Cards> hand) {

        int sum = 0;
        for (Cards cards: hand) {

            if (cards.getCardValue().equals("A")) sum++;
        }

        return sum;
    }

    public void emptyHand() {

        playerHand.clear();
        dealerHand.clear();
        D.Deck.clear();
        D = new Deck();
    }

    public String getVersion() {
        return VERSION;
    }
}
