package com.company;

public class Cards {

    String cardValue;
    String cardSuit;

    public Cards(String suit, String value) {

        cardValue = value;
        cardSuit = suit;
    }

    public void printCard() {

        System.out.println("This is the " + cardValue + " of " + cardSuit + ".");
    }

    public void printRaw() {

        System.out.print("[" + cardValue + cardSuit + "] ");
    }

    public int getNumericBJ() {

        try {

            return Integer.parseInt(cardValue);
        } catch (Exception e) {

            if (cardValue.equals("A")) {
                return 1;
            }
            else return 10;
        }
    }

    public String getCardValue() {

        return cardValue;
    }
}
