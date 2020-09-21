package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException{

        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to NP Casino!");
        System.out.print("Select a game:\n1. Blackjack\n2. Texas Holdem\n>");
        String sel = in.nextLine();

        switch(sel) {

            case "1" -> {

                Blackjack BJ = new Blackjack();
                System.out.println("Welcome to NP Blackjack " + BJ.getVersion());
                while (true) {

                    BJ.playHand();

                    System.out.print("\nPlay again? (Y = Yes, N = No)\n>");
                    String pa = in.nextLine();

                    switch(pa) {

                        case "N","n" -> System.exit(0);
                        case "Y", "y" -> {}
                        default -> {
                            System.out.println("Invalid option. Exiting...");
                            return;
                        }
                    }
                }
            }
            case "2" -> {}
            default -> System.out.println("Invalid option. Exiting...");
        }
    }
}
