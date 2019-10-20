package com.example.beehivesolitaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // creating a new deck
    Deck deck;
    boolean isCardSelected = false;
    String cardSelectedType = "";
    int gardenSelectedNumber = -1;
    int secondGardenSelectedNumber = -1;
    Deck.Card cardSelected = null;
    Deck.GardenCard gardenCardSelected = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView garden0 = findViewById(R.id.iv_garden0);
        garden0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCardSelected) {
                    secondGardenSelectedNumber = 0;
                    garden0.setColorFilter(Color.TRANSPARENT);
                    placeCard(deck.garden[0]);
                }
                else {
                    gardenSelectedNumber = 0;
                    selectCard(deck.garden[0], "garden");
                    garden0.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN);
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

        final ImageView pack = findViewById(R.id.iv_pack);
        pack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if there is not a move possible
                if (!deck.movePossible()) {
                    if(deck.drawThreeFromPack()) {
                        updateCards();
                    }
                    else {
                        Context context = getApplicationContext();
                        CharSequence text = "Game over!";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        newGame();
                        updateCards();

                    }
                }
                isCardSelected = false;
            }
        });

        final ImageView beehive = findViewById(R.id.iv_beehive);
        beehive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCardSelected) {
                    beehive.setColorFilter(Color.TRANSPARENT);
                    isCardSelected = false;
                    return;
                }
                else {
                    selectCard(deck.beehive.get(deck.beehive.size() - 1), "beehive");
                    beehive.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN);
                }
                isCardSelected = !isCardSelected;
            }
        });

        final ImageView pile = findViewById(R.id.iv_pile);
        pile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCardSelected) {
                    pile.setColorFilter(Color.TRANSPARENT);
                    isCardSelected = false;
                    return;
                }
                else {
                    if (deck.pile.size() > 0) {
                        selectCard(deck.pile.get(deck.pile.size() - 1), "pile");
                        pile.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN);
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

        tv_garden0.setText("Cards: " + deck.garden[0].count);
        tv_garden1.setText("Cards: " + deck.garden[1].count);
        tv_garden2.setText("Cards: " + deck.garden[2].count);
        tv_garden3.setText("Cards: " + deck.garden[3].count);
        tv_garden4.setText("Cards: " + deck.garden[4].count);
        tv_garden5.setText("Cards: " + deck.garden[5].count);

        ImageView beehive = findViewById(R.id.iv_beehive);
        if (deck.beehive.size() > 0) {
            beehive.setImageResource(deck.beehive.get(deck.beehive.size() - 1).id);
        }
        else {
            beehive.setImageResource(R.drawable.c_);
        }

        ImageView pack = findViewById(R.id.iv_pack);
        pack.setImageResource(R.drawable.c_b);

        ImageView pile = findViewById(R.id.iv_pile);
        if (deck.pile.size() > 0) {
            pile.setImageResource(deck.pile.get(deck.pile.size() - 1).id);
        }
        else {
            pile.setImageResource(R.drawable.c_);
        }


        garden0.setColorFilter(Color.TRANSPARENT);
        garden1.setColorFilter(Color.TRANSPARENT);
        garden2.setColorFilter(Color.TRANSPARENT);
        garden3.setColorFilter(Color.TRANSPARENT);
        garden4.setColorFilter(Color.TRANSPARENT);
        garden5.setColorFilter(Color.TRANSPARENT);
        beehive.setColorFilter(Color.TRANSPARENT);
        pack.setColorFilter(Color.TRANSPARENT);
        pile.setColorFilter(Color.TRANSPARENT);


        TextView draws = findViewById(R.id.tv_draws);
        draws.setText("Draws: " + (int)(deck.pack.size()/3) + " ");

        TextView numPile = findViewById(R.id.tv_pileCards);
        numPile.setText("Cards: " + deck.pile.size());

        TextView numBeehive = findViewById(R.id.tv_beehiveCards);
        numBeehive.setText("Cards: " + deck.beehive.size());

    }

    private void selectCard(Deck.Card card, String type) {
        cardSelected = card;
        cardSelectedType = type;
    }

    private void placeCard(Deck.Card card) {
        updateCards();
        if (!deck.garden[secondGardenSelectedNumber].addToGarden(cardSelected)) {
            return;
        }
        switch (cardSelectedType) {

            case "garden":
                cardSelected.id = R.drawable.c_;
                cardSelected.suit = '\0';
                cardSelected.rank = '\0';
                if (deck.beehive.size() > 0) {
                    Deck.Card topBeeHive = deck.getTopBeehive();
                    cardSelected.id = topBeeHive.id;
                    cardSelected.suit = topBeeHive.suit;
                    cardSelected.rank = topBeeHive.rank;
                    deck.garden[gardenSelectedNumber].count = 1;
                }
                else if (deck.pile.size() > 0) {
                    Deck.Card topPile = deck.getTopPile();
                    cardSelected.id = topPile.id;
                    cardSelected.suit = topPile.suit;
                    cardSelected.rank = topPile.rank;
                    deck.garden[gardenSelectedNumber].count = 1;
                }
                else {
                    deck.drawThreeFromPack();
                    Deck.Card topPile = deck.getTopPile();
                    cardSelected.id = topPile.id;
                    cardSelected.suit = topPile.suit;
                    cardSelected.rank = topPile.rank;
                    deck.garden[gardenSelectedNumber].count = 1;
                }
                break;
            case "beehive":
                deck.beehive.remove(deck.beehive.size() - 1);
                break;
            case "pack":
                deck.pack.remove(deck.pack.size() - 1);
                break;
            case "pile":
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

        ArrayList<String> beehiveSuits = new ArrayList<>();
        ArrayList<String> beehiveRanks = new ArrayList<>();
        ArrayList<Integer> beehiveIds = new ArrayList<>();

        ArrayList<String> packSuits = new ArrayList<>();
        ArrayList<String> packRanks = new ArrayList<>();
        ArrayList<Integer> packIds = new ArrayList<>();

        ArrayList<String> pileSuits = new ArrayList<>();
        ArrayList<String> pileRanks = new ArrayList<>();
        ArrayList<Integer> pileIds = new ArrayList<>();

        char[] gardenSuits = new char[6];
        char[] gardenRanks = new char[6];
        int[] gardenIds = new int[6];
        int[] gardenCount = new int[6];

        for (Deck.Card card : deck.beehive) {
            beehiveSuits.add(card.suit + "");
            beehiveRanks.add(card.rank + "");
            beehiveIds.add(card.id);
        }

        for (Deck.Card card : deck.pack) {
            packSuits.add(card.suit + "");
            packRanks.add(card.rank + "");
            packIds.add(card.id);
        }

        for (Deck.Card card : deck.pile) {
            pileSuits.add(card.suit + "");
            pileRanks.add(card.rank + "");
            pileIds.add(card.id);
        }

        for (int i = 0; i < 6; i++) {
            gardenSuits[i] = deck.garden[i].suit;
            gardenRanks[i] = deck.garden[i].rank;
            gardenIds[i] = deck.garden[i].id;
            gardenCount[i] = deck.garden[i].count;
        }

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

        deck.pack.clear();
        deck.beehive.clear();
        deck.pile.clear();

        for (int i = 0; i < beehiveSuits.size(); i++) {
            System.out.println(i);
            Deck.Card card = new Deck.Card(beehiveRanks.get(i).charAt(0), beehiveSuits.get(i).charAt(0), beehiveIds.get(i));
            deck.beehive.add(card);
        }

        for (int i = 0; i < packSuits.size(); i++) {
            System.out.println(i);
            Deck.Card card = new Deck.Card(packRanks.get(i).charAt(0), packSuits.get(i).charAt(0), packIds.get(i));
            deck.pack.add(card);
        }

        for (int i = 0; i < pileSuits.size(); i++) {
            System.out.println(i);
            Deck.Card card = new Deck.Card(pileRanks.get(i).charAt(0), pileSuits.get(i).charAt(0), pileIds.get(i));
            deck.pile.add(card);
        }

        for (int i = 0; i < 6; i++) {
            deck.garden[i].count = gardenCount[i];
            deck.garden[i].id = gardenIds[i];
            deck.garden[i].rank = gardenRanks[i];
            deck.garden[i].suit = gardenSuits[i];
        }
        updateCards();
    }
}
