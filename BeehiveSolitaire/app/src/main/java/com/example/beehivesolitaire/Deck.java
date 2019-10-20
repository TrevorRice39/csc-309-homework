package com.example.beehivesolitaire;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    Card[] cards = new Card[52];
    ArrayList<Card> beehive = new ArrayList<Card>();
    ArrayList<Card> pack = new ArrayList<Card>();
    ArrayList<Card> pile = new ArrayList<Card>();
    GardenCard[] garden = new GardenCard[6];

    int numCards = 0;

    // card class to hold rank, suit, and id for a card
    public static class Card {
        char rank;
        char suit;
        int id;

        Card () {

        }

        Card(char rank, char suit, int id) {
            this.rank = rank;
            this.suit = suit;
            this.id = id;
        }
    }

    public class GardenCard extends Card {
        int count = 1;

        GardenCard (Card card) {
            super(card.rank, card.suit, card.id);
        }
        GardenCard(char rank, char suit, int id) {
            super(rank, suit, id);
        }

        public boolean addToGarden(Card card) {
            char rank = card.rank;
            char suit = card.suit;
            int id = card.id;
            if (rank == this.rank && id != this.id) {
                this.rank = rank;
                this.suit = suit;

                boolean inGarden = false;
                int index = -1;
                for (int i = 0; i < 6; i++) {
                    if (garden[i].id == id) {
                        inGarden = true;
                        index = i;
                    }
                }

                if (inGarden) {
                    this.count += garden[index].count;
                    garden[index].count = 1;
                }
                else {
                    this.count++;
                }
                this.id = id;


                if (count == 4) {
                    Card newCard;
                    if (beehive.size() > 0) {
                        newCard = getTopBeehive();
                    }
                    else if(pile.size() > 0) {
                        newCard = getTopPile();
                    }
                    else {
                        drawThreeFromPack();
                        newCard = getTopPile();
                    }
                    this.count = 1;
                    this.rank = newCard.rank;
                    this.suit = newCard.suit;
                    this.id = newCard.id;
                }
                return true;
            }
            return false;
        }


        public boolean isFull() {
            return count == 4;
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

        beehive.clear();
        garden = new GardenCard[6];
        pack.clear();

        // add ten cards to beehive
        for (int index = 51 ;index > 41; index--) {
            beehive.add(cards[index]);
        }

        // add 6 cards to the garden
        for (int index = 41; index > 35; index--) {
            garden[index-36] = new GardenCard(cards[index]);
        }

        // add the rest to the pack
        for (int index = 35; index >= 0; index--) {
            pack.add(cards[index]);
        }
    }

    Card getTopBeehive() {
        if (beehive.size() == 0) {
            return null;
        }
        Card card = beehive.get(beehive.size() - 1);
        beehive.remove(beehive.size() - 1);
        return card;
    }

    Card getTopPile() {
        if (pile.size() == 0) {
            return null;
        }
        Card card = pile.get(pile.size() - 1);
        pile.remove(pile.size() - 1);
        return card;
    }

    boolean movePossible() {
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if (garden[i].rank == garden[j].rank) {
                    return true;
                }
            }
        }

        if (beehive.size() > 0) {
            Card card = beehive.get(beehive.size() - 1);
            for (int i = 0; i < 6; i++) {
                if (garden[i].rank == card.rank) {
                    return true;
                }
            }
        }

        if (pile.size() > 0) {
            Card card = pile.get(pile.size() - 1);
            for (int i = 0; i < 6; i++) {
                if (garden[i].rank == card.rank) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean drawThreeFromPack() {
        if (pack.size() != 0) {
            int index = pack.size() - 1;
            Card card1 = pack.get(index);
            Card card2 = pack.get(index -1);
            Card card3 = pack.get(index - 2);
            pack.remove(index);
            pack.remove(index - 1);
            pack.remove(index - 2);

            pile.add(card1);
            pile.add(card2);
            pile.add(card3);
            return true;
        }
        return false;
    }
}
