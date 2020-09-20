package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    int numSuits = 4;
    int numValues = 13;
    String[] typeSuits = {"S", "H", "C", "D"};
    String[] typeValues = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    ArrayList<Cards> Deck = new ArrayList<>();

    public Deck() {

        for (int i = 0; i < numSuits; i++) {
            for (int j = 0; j < numValues; j++) {
                Deck.add(new Cards(typeSuits[i], typeValues[j]));
            }
        }
    }

    public void shuffleDeck() {

        Collections.shuffle(Deck);
    }

    public void printRaw(int card) {

        Deck.get(card).printRaw();
    }
}
