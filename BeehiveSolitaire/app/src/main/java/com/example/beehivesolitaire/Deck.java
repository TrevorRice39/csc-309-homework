package com.example.beehivesolitaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    Card[] cards = new Card[52];
    ArrayList<Card> beehive = new ArrayList<Card>();
    ArrayList<Card> pack = new ArrayList<Card>();
    ArrayList<Card> pile = new ArrayList<Card>();
    Card[] garden = new Card[6];

    int numCards = 0;

    // card class to hold rank, suit, and id for a card
    public class Card {
        char rank;
        char suit;
        int id;

        Card(char rank, char suit, int id) {
            this.rank = rank;
            this.suit = suit;
            this.id = id;
        }
    }

    // add a card to the deck, called by main
    void addCard(char rank, char suit, int id) {
        // create a new card with the rank, suit, and id
        cards[numCards++] = new Card(rank, suit, id);
    }

    // function to shuffle the deck
    void shuffleDeck() {
        Random random = new Random();
        for (int i = cards.length - 1; i >= 0; i--) {
            // random position
            int index = random.nextInt(i + 1);

            // swap card at i with random card
            Card temp = cards[index];
            cards[index] = cards[i];
            cards[i] = temp;
        }
    }

    void newGame() {
        // shuffle the deck
        shuffleDeck();

        // add ten cards to beehive
        for (int index = 51 ;index > 41; index--) {
            beehive.add(cards[index]);
        }

        // add 6 cards to the garden
        for (int index = 41; index > 35; index--) {
            garden[index-36] = cards[index];
        }

        // add the rest to the pack
        for (int index = 35; index >= 0; index--) {
            pack.add(cards[index]);
        }
    }
}
