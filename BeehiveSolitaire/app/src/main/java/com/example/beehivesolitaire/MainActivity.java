package com.example.beehivesolitaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
        To play this game, the user can click a card from the beehive, garden, or pile, and place
        it accordingly onto the garden. If there are no moves, the player can click the pack to
        draw three cards and place it on the pile.
     */

    // creating a new deck
    Deck deck;
    // is there a card selected
    boolean isCardSelected = false;
    // what type (garden, beehive, pile)
    String cardSelectedType = "";
    // what position in the garden is clicked
    int gardenSelectedNumber = -1;
    // if it is a garden to garden move, whats the second garden position
    int secondGardenSelectedNumber = -1;
    // the actual card selected
    Deck.Card cardSelected = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // garden 0 image view
        // all garden listeners follow this exact structure
        final ImageView garden0 = findViewById(R.id.iv_garden0);
        garden0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCardSelected) { // if its already selected
                    secondGardenSelectedNumber = 0; // second garden number is zero
                    garden0.setColorFilter(Color.TRANSPARENT); // remove the color
                    placeCard(deck.garden[0]); // attempt to place the card on the garden
                }
                else { // if this is the first selection
                    gardenSelectedNumber = 0; // set garden card to 0
                    selectCard(deck.garden[0], "garden"); // select the card
                    garden0.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN); // set the color to a light gray
                }
                isCardSelected = !isCardSelected;
            }
        });

        final ImageView garden1 = findViewById(R.id.iv_garden1);
        garden1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isCardSelected) {
                    secondGardenSelectedNumber = 1;
                    garden1.setColorFilter(Color.TRANSPARENT);
                    placeCard(deck.garden[1]);
                }
                else {
                    gardenSelectedNumber = 1;
                    selectCard(deck.garden[1], "garden");
                    garden1.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN);
                }
                isCardSelected = !isCardSelected;
            }
        });

        final ImageView garden2 = findViewById(R.id.iv_garden2);
        garden2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isCardSelected) {
                    secondGardenSelectedNumber = 2;
                    garden2.setColorFilter(Color.TRANSPARENT);
                    placeCard(deck.garden[2]);
                }
                else {
                    gardenSelectedNumber = 2;
                    selectCard(deck.garden[2], "garden");
                    garden2.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN);
                }
                isCardSelected = !isCardSelected;
            }
        });

        final ImageView garden3 = findViewById(R.id.iv_garden3);
        garden3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isCardSelected) {
                    secondGardenSelectedNumber = 3;
                    garden3.setColorFilter(Color.TRANSPARENT);
                    placeCard(deck.garden[3]);
                }
                else {
                    gardenSelectedNumber = 3;
                    selectCard(deck.garden[3], "garden");
                    garden3.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN);
                }
                isCardSelected = !isCardSelected;
            }
        });

        final ImageView garden4 = findViewById(R.id.iv_garden4);
        garden4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCardSelected) {
                    secondGardenSelectedNumber = 4;
                    garden4.setColorFilter(Color.TRANSPARENT);
                    placeCard(deck.garden[4]);
                }
                else {
                    gardenSelectedNumber = 4;
                    selectCard(deck.garden[4], "garden");
                    garden4.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN);
                }
                isCardSelected = !isCardSelected;
            }
        });

        final ImageView garden5 = findViewById(R.id.iv_garden5);
        garden5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCardSelected) {
                    secondGardenSelectedNumber = 5;
                    garden5.setColorFilter(Color.TRANSPARENT);
                    placeCard(deck.garden[5]);
                }
                else {
                    gardenSelectedNumber = 5;
                    selectCard(deck.garden[5], "garden");
                    garden5.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN);
                }
                isCardSelected = !isCardSelected;
            }
        });

        // pack image view
        final ImageView pack = findViewById(R.id.iv_pack);
        pack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if there is not a move possible
                if (!deck.movePossible()) {
                    // draw three cards and update screen
                    if(deck.drawThreeFromPack()) {
                        updateCards();
                    }
                    else {
                        // if there are no moves, game is over
                        Context context = getApplicationContext();
                        CharSequence text = "Game over!";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        newGame(); // new game
                        updateCards(); // update screen

                    }
                }
                isCardSelected = false;
            }
        });

        // behive image view
        final ImageView beehive = findViewById(R.id.iv_beehive);
        beehive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCardSelected) { // is a card selected
                    beehive.setColorFilter(Color.TRANSPARENT); // remove color
                    isCardSelected = false; // no card selected anymore
                    return;
                }
                else { // if there was no card selected
                    selectCard(deck.beehive.get(deck.beehive.size() - 1), "beehive"); // select the card
                    beehive.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN); // darken the color
                }
                isCardSelected = !isCardSelected;
            }
        });


        // pile image view
        final ImageView pile = findViewById(R.id.iv_pile);
        pile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCardSelected) { // if there is a card selected
                    pile.setColorFilter(Color.TRANSPARENT); // remove color
                    isCardSelected = false; // no card is card selected
                    return;
                }
                else { // if there is no card selected
                    if (deck.pile.size() > 0) {  // if the pile has cards
                        selectCard(deck.pile.get(deck.pile.size() - 1), "pile"); // select card
                        pile.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN); // darken the color
                    }
                }
                isCardSelected = !isCardSelected;
            }
        });

        newGame();

    }

    private void newGame() {
         deck = new Deck();
        // all possible ranks and suits
        char[] ranks = {'2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k', 'a'};
        char[] suits = {'c', 'd', 'h', 's'};

        // for each rank in ranks
        for (char rank : ranks) {
            // for each suit in suits
            for (char suit : suits) {
                // find the id of c_(rank)(suit) in drawables
                int cardId = getResources().getIdentifier("c_" + rank + "" + suit, "drawable", getPackageName());

                // add the card to our deck
                deck.addCard(rank, suit, cardId);
            }
        }

        deck.newGame();
        updateCards();
    }
    private void updateCards() {
        ImageView garden0 = findViewById(R.id.iv_garden0);
        ImageView garden1 = findViewById(R.id.iv_garden1);
        ImageView garden2 = findViewById(R.id.iv_garden2);
        ImageView garden3 = findViewById(R.id.iv_garden3);
        ImageView garden4 = findViewById(R.id.iv_garden4);
        ImageView garden5 = findViewById(R.id.iv_garden5);

        garden0.setImageResource(deck.garden[0].id);
        garden1.setImageResource(deck.garden[1].id);
        garden2.setImageResource(deck.garden[2].id);
        garden3.setImageResource(deck.garden[3].id);
        garden4.setImageResource(deck.garden[4].id);
        garden5.setImageResource(deck.garden[5].id);

        TextView tv_garden0 = findViewById(R.id.tv_garden0);
        TextView tv_garden1 = findViewById(R.id.tv_garden1);
        TextView tv_garden2 = findViewById(R.id.tv_garden2);
        TextView tv_garden3 = findViewById(R.id.tv_garden3);
        TextView tv_garden4 = findViewById(R.id.tv_garden4);
        TextView tv_garden5 = findViewById(R.id.tv_garden5);

        // update the number of cards in each garden position
        tv_garden0.setText("Cards: " + deck.garden[0].count);
        tv_garden1.setText("Cards: " + deck.garden[1].count);
        tv_garden2.setText("Cards: " + deck.garden[2].count);
        tv_garden3.setText("Cards: " + deck.garden[3].count);
        tv_garden4.setText("Cards: " + deck.garden[4].count);
        tv_garden5.setText("Cards: " + deck.garden[5].count);

        // if there are cards in the beehive, set the image view to the one on top
        ImageView beehive = findViewById(R.id.iv_beehive);
        if (deck.beehive.size() > 0) {
            beehive.setImageResource(deck.beehive.get(deck.beehive.size() - 1).id);
        }
        else {
            beehive.setImageResource(R.drawable.c_);
        }

        // set the pack to the c_b card
        ImageView pack = findViewById(R.id.iv_pack);
        pack.setImageResource(R.drawable.c_b);

        // if there are cards in the pile, set the image view to the one on top
        ImageView pile = findViewById(R.id.iv_pile);
        if (deck.pile.size() > 0) {
            pile.setImageResource(deck.pile.get(deck.pile.size() - 1).id);
        }
        else {
            pile.setImageResource(R.drawable.c_);
        }

        // make sure every color is clared
        garden0.setColorFilter(Color.TRANSPARENT);
        garden1.setColorFilter(Color.TRANSPARENT);
        garden2.setColorFilter(Color.TRANSPARENT);
        garden3.setColorFilter(Color.TRANSPARENT);
        garden4.setColorFilter(Color.TRANSPARENT);
        garden5.setColorFilter(Color.TRANSPARENT);
        beehive.setColorFilter(Color.TRANSPARENT);
        pack.setColorFilter(Color.TRANSPARENT);
        pile.setColorFilter(Color.TRANSPARENT);


        // update number of draws from pack
        TextView draws = findViewById(R.id.tv_draws);
        draws.setText("Draws: " + (int)(deck.pack.size()/3) + " ");

        // update number of cards in pile
        TextView numPile = findViewById(R.id.tv_pileCards);
        numPile.setText("Cards: " + deck.pile.size());

        // update number of cards in beehive
        TextView numBeehive = findViewById(R.id.tv_beehiveCards);
        numBeehive.setText("Cards: " + deck.beehive.size());

    }

    // function called when card selected
    private void selectCard(Deck.Card card, String type) {
        cardSelected = card; // set the card selected to card
        cardSelectedType = type; // set type to typep
    }

    private void placeCard(Deck.Card card) {
        updateCards();
        // function returns true if it was successfully added to garden
        if (!deck.garden[secondGardenSelectedNumber].addToGarden(cardSelected)) {
            return; // else, return
        }
        switch (cardSelectedType) { // what is the type
            case "garden":
                // if garden, you must replace the empty position
                cardSelected.id = R.drawable.c_;
                cardSelected.suit = '\0';
                cardSelected.rank = '\0';
                if (deck.beehive.size() > 0) { // if there are cards on the beehive, replace the empty position with one
                    Deck.Card topBeeHive = deck.getTopBeehive();
                    cardSelected.id = topBeeHive.id;
                    cardSelected.suit = topBeeHive.suit;
                    cardSelected.rank = topBeeHive.rank;
                    deck.garden[gardenSelectedNumber].count = 1;
                }
                else if (deck.pile.size() > 0) { // if there are cards in the pile, replace the empty position with one
                    Deck.Card topPile = deck.getTopPile();
                    cardSelected.id = topPile.id;
                    cardSelected.suit = topPile.suit;
                    cardSelected.rank = topPile.rank;
                    deck.garden[gardenSelectedNumber].count = 1;
                }
                else if (deck.pack.size() >= 3){ // if the pack has cards, draw three to pile, replace the empty position with top of pile
                    deck.drawThreeFromPack();
                    Deck.Card topPile = deck.getTopPile();
                    cardSelected.id = topPile.id;
                    cardSelected.suit = topPile.suit;
                    cardSelected.rank = topPile.rank;
                    deck.garden[gardenSelectedNumber].count = 1;
                }
                else { // no cards available in pack, pile, or beehive
                    if (!deck.movePossible()) { // if no move is possible, you win
                        Context context = getApplicationContext();
                        CharSequence text = "You Win!";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        newGame();
                    }
                }
                break;
            case "beehive":
                // remove top of beehive
                deck.beehive.remove(deck.beehive.size() - 1);
                break;
            case "pack":
                // remove top of pack
                deck.pack.remove(deck.pack.size() - 1);
                break;
            case "pile":
                // remove top of pile
                deck.pile.remove(deck.pile.size() - 1);
                break;
        }
        updateCards();
    }


    final String BEEHIVESUITS = "beehivesuits";
    final String BEEHIVERANKS = "beehiveranks";
    final String BEEHIVEIDS = "beehiveids";
    final String PACKSUITS = "packsuits";
    final String PACKRANKS = "packranks";
    final String PACKIDS = "packids";
    final String PILESUITS = "pilesuits";
    final String PILERANKS = "pileranks";
    final String PILEIDS = "pileids";
    final String GARDENSUITS = "gardensuits";
    final String GARDENRANKS = "gardenranks";
    final String GARDENIDS = "gardenids";
    final String GARDENCOUNT = "gardencount";

    @Override
    public void onSaveInstanceState(Bundle saved) {
        super.onSaveInstanceState(saved);

        // converting my deck object to array lists

        // suits in the beehive
        ArrayList<String> beehiveSuits = new ArrayList<>();
        // ranks in beehive
        ArrayList<String> beehiveRanks = new ArrayList<>();
        // ids in beehive
        ArrayList<Integer> beehiveIds = new ArrayList<>();

        // suits in pack
        ArrayList<String> packSuits = new ArrayList<>();
        // ranks in pack
        ArrayList<String> packRanks = new ArrayList<>();
        // ids in pack
        ArrayList<Integer> packIds = new ArrayList<>();

        // suits in pile
        ArrayList<String> pileSuits = new ArrayList<>();
        // ranks in pile
        ArrayList<String> pileRanks = new ArrayList<>();
        // ids in pile
        ArrayList<Integer> pileIds = new ArrayList<>();

        // suits in garden
        char[] gardenSuits = new char[6];
        // ranks in garden
        char[] gardenRanks = new char[6];
        // ids in garden
        int[] gardenIds = new int[6];
        // number of cards in each garden position
        int[] gardenCount = new int[6];

        // add all beehive cards to beehive array lists
        for (Deck.Card card : deck.beehive) {
            beehiveSuits.add(card.suit + "");
            beehiveRanks.add(card.rank + "");
            beehiveIds.add(card.id);
        }

        // add all pack cards to pack array lists
        for (Deck.Card card : deck.pack) {
            packSuits.add(card.suit + "");
            packRanks.add(card.rank + "");
            packIds.add(card.id);
        }

        // add all pile cards to pile array lists
        for (Deck.Card card : deck.pile) {
            pileSuits.add(card.suit + "");
            pileRanks.add(card.rank + "");
            pileIds.add(card.id);
        }

        // add garden cards to garden array
        for (int i = 0; i < 6; i++) {
            gardenSuits[i] = deck.garden[i].suit;
            gardenRanks[i] = deck.garden[i].rank;
            gardenIds[i] = deck.garden[i].id;
            gardenCount[i] = deck.garden[i].count;
        }

        // save them

        saved.putStringArrayList(BEEHIVESUITS, beehiveSuits);
        saved.putStringArrayList(BEEHIVERANKS, beehiveRanks);
        saved.putIntegerArrayList(BEEHIVEIDS, beehiveIds);

        saved.putStringArrayList(PACKSUITS, packSuits);
        saved.putStringArrayList(PACKRANKS, packRanks);
        saved.putIntegerArrayList(PACKIDS, packIds);

        saved.putStringArrayList(PILESUITS, pileSuits);
        saved.putStringArrayList(PILERANKS, pileRanks);
        saved.putIntegerArrayList(PILEIDS, pileIds);

        saved.putCharArray(GARDENSUITS, gardenSuits);
        saved.putCharArray(GARDENRANKS, gardenRanks);
        saved.putIntArray(GARDENIDS, gardenIds);
        saved.putIntArray(GARDENCOUNT, gardenCount);
    }

    @Override
    public void onRestoreInstanceState(Bundle saved) {
        super.onRestoreInstanceState(saved);

        // converting the array lists back to objects

        ArrayList<String> beehiveSuits = saved.getStringArrayList(BEEHIVESUITS);
        ArrayList<String> beehiveRanks = saved.getStringArrayList(BEEHIVERANKS);
        ArrayList<Integer> beehiveIds = saved.getIntegerArrayList(BEEHIVEIDS);

        ArrayList<String> packSuits = saved.getStringArrayList(PACKSUITS);
        ArrayList<String> packRanks = saved.getStringArrayList(PACKRANKS);
        ArrayList<Integer> packIds = saved.getIntegerArrayList(PACKIDS);

        ArrayList<String> pileSuits = saved.getStringArrayList(PILESUITS);
        ArrayList<String> pileRanks = saved.getStringArrayList(PILERANKS);
        ArrayList<Integer> pileIds = saved.getIntegerArrayList(PILEIDS);

        char[] gardenSuits = saved.getCharArray(GARDENSUITS);
        char[] gardenRanks = saved.getCharArray(GARDENRANKS);
        int[] gardenIds = saved.getIntArray(GARDENIDS);
        int[] gardenCount = saved.getIntArray(GARDENCOUNT);

        // make sure there are no cards in pack beehive or pile
        deck.pack.clear();
        deck.beehive.clear();
        deck.pile.clear();

        // adding cards to beehive
        for (int i = 0; i < beehiveSuits.size(); i++) {
            // make a new card with the beehive data
            Deck.Card card = new Deck.Card(beehiveRanks.get(i).charAt(0), beehiveSuits.get(i).charAt(0), beehiveIds.get(i));
            // add the card
            deck.beehive.add(card);
        }

        for (int i = 0; i < packSuits.size(); i++) {
            // make a new card with the pack data
            Deck.Card card = new Deck.Card(packRanks.get(i).charAt(0), packSuits.get(i).charAt(0), packIds.get(i));
            // add the card
            deck.pack.add(card);
        }

        for (int i = 0; i < pileSuits.size(); i++) {
            // make a new card with the pile data
            Deck.Card card = new Deck.Card(pileRanks.get(i).charAt(0), pileSuits.get(i).charAt(0), pileIds.get(i));
            // add the card
            deck.pile.add(card);
        }

        for (int i = 0; i < 6; i++) {
            // update count, id, rank, and suit for each position in the garden
            deck.garden[i].count = gardenCount[i];
            deck.garden[i].id = gardenIds[i];
            deck.garden[i].rank = gardenRanks[i];
            deck.garden[i].suit = gardenSuits[i];
        }
        // lastly, update the screen
        updateCards();
    }
}
