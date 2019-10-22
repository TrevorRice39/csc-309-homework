package com.example.beehivesolitaire;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    // initial cards
    Card[] cards = new Card[52];

    // list of cards in beehive
    ArrayList<Card> beehive = new ArrayList<Card>();
    // list of cards in pack
    ArrayList<Card> pack = new ArrayList<Card>();
    // list of cards in pile
    ArrayList<Card> pile = new ArrayList<Card>();
    // the 6 garden cards
    GardenCard[] garden = new GardenCard[6];

    // number of cards added to the deck in main
    int numCards = 0;

    // card class to hold rank, suit, and id for a card
    public static class Card {
        // each card has a rank, suit, and id
        char rank;
        char suit;
        int id;

        // constructor
        Card(char rank, char suit, int id) {
            this.rank = rank;
            this.suit = suit;
            this.id = id;
        }
    }

    // garden gard is an extension of card
    public class GardenCard extends Card {
        // number of cards in a garden card position
        int count = 1;

        // constructor
        GardenCard (Card card) {
            super(card.rank, card.suit, card.id);
        }
        GardenCard(char rank, char suit, int id) {
            super(rank, suit, id);
        }

        // add to garden method
        public boolean addToGarden(Card card) {
            char rank = card.rank;
            char suit = card.suit;
            int id = card.id;
            // if the rank is equal to this garden position, and it is a different card, add it
            if (rank == this.rank && id != this.id) {
                this.rank = rank;
                this.suit = suit;

                // check to see if the added card is also in the garden
                boolean inGarden = false;
                int index = -1;
                for (int i = 0; i < 6; i++) {
                    if (garden[i].id == id) { // if it is
                        inGarden = true;
                        index = i;
                    }
                }

                // if the added card was in the garden add the count from its card
                if (inGarden) {
                    this.count += garden[index].count;
                    garden[index].count = 1;
                }
                else {
                    // else increment by 1
                    this.count++;
                }
                this.id = id;


                if (count == 4) { // if there are 4 cards in this position
                    Card newCard; // we need a new card here
                    if (beehive.size() > 0) { // can we draw from the beehive
                        newCard = getTopBeehive();
                    }
                    else if(pile.size() > 0) { // if not, can we draw from pile
                        newCard = getTopPile();
                    }
                    else if (pack.size() >= 3){// can we draw from pack
                        drawThreeFromPack();
                        newCard = getTopPile();
                    }
                    else { // no card can be put in the empty position
                        newCard = null;
                    }
                    this.count = 1;
                    this.rank = newCard.rank;
                    this.suit = newCard.suit;
                    this.id = newCard.id;
                }
                return true; // successfully added
            }
            return false; // cannot add to this position
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
        // removes and returns top card of beehive if possible
        if (beehive.size() == 0) {
            return null;
        }
        Card card = beehive.get(beehive.size() - 1);
        beehive.remove(beehive.size() - 1);
        return card;
    }

    Card getTopPile() {
        // removes and retursn top card from pile if possible
        if (pile.size() == 0) {
            return null;
        }
        Card card = pile.get(pile.size() - 1);
        pile.remove(pile.size() - 1);
        return card;
    }

    // is there a move possible
    boolean movePossible() {
        // if any rank in the garden equals another position's rank, a move is possible
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if (garden[i].rank == garden[j].rank) {
                    return true;
                }
            }
        }

        // if the top card of the beehive matches a card in the garden, a move is possible
        if (beehive.size() > 0) {
            Card card = beehive.get(beehive.size() - 1);
            for (int i = 0; i < 6; i++) {
                if (garden[i].rank == card.rank) {
                    return true;
                }
            }
        }

        // if the top card in the pile matches a card in the garden, a move is possible
        if (pile.size() > 0) {
            Card card = pile.get(pile.size() - 1);
            for (int i = 0; i < 6; i++) {
                if (garden[i].rank == card.rank) {
                    return true;
                }
            }
        }

        // no move is possible
        return false;
    }

    boolean drawThreeFromPack() {
        // when the pack is selected, remove three from pack and add it to pile
        if (pack.size() != 0) {
            // get the three cards
            int index = pack.size() - 1;
            Card card1 = pack.get(index);
            Card card2 = pack.get(index -1);
            Card card3 = pack.get(index - 2);
            // remove them
            pack.remove(index);
            pack.remove(index - 1);
            pack.remove(index - 2);

            // add them to the pile
            pile.add(card1);
            pile.add(card2);
            pile.add(card3);
            return true;
        }
        return false;
    }
}
