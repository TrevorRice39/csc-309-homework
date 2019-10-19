package com.example.beehivesolitaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // creating a new deck
    Deck deck = new Deck();
    boolean isCardSelected = false;
    String cardSelectedType = "";
    int gardenSelectedNumber = -1;
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
                gardenSelectedNumber = 0;
                if (isCardSelected) {
                    garden0.setColorFilter(Color.TRANSPARENT);
                    placeCard(deck.garden[0]);
                }
                else {
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
                gardenSelectedNumber = 1;
                if (isCardSelected) {
                    garden1.setColorFilter(Color.TRANSPARENT);
                    placeCard(deck.garden[1]);
                }
                else {
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
                gardenSelectedNumber = 2;
                if (isCardSelected) {
                    garden2.setColorFilter(Color.TRANSPARENT);
                    placeCard(deck.garden[2]);
                }
                else {
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
                gardenSelectedNumber = 3;
                if (isCardSelected) {
                    garden3.setColorFilter(Color.TRANSPARENT);
                    placeCard(deck.garden[3]);
                }
                else {
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
                gardenSelectedNumber = 4;
                if (isCardSelected) {
                    garden4.setColorFilter(Color.TRANSPARENT);
                    placeCard(deck.garden[4]);
                }
                else {
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
                gardenSelectedNumber = 5;
                if (isCardSelected) {
                    garden5.setColorFilter(Color.TRANSPARENT);
                    placeCard(deck.garden[5]);
                }
                else {
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
                if (isCardSelected) {
                    pack.setColorFilter(Color.TRANSPARENT);
                }
                else {
                    selectCard(deck.pack.get(deck.pack.size() - 1), "pack");
                    pack.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN);
                }
                isCardSelected = !isCardSelected;
            }
        });

        final ImageView beehive = findViewById(R.id.iv_beehive);
        beehive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCardSelected) {
                    beehive.setColorFilter(Color.TRANSPARENT);
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
                }
                else {
                    selectCard(deck.pile.get(deck.pile.size() - 1), "pile");
                    pile.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN);
                }
                isCardSelected = !isCardSelected;
            }
        });


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

        ImageView beehive = findViewById(R.id.iv_beehive);
        beehive.setImageResource(deck.beehive.get(deck.beehive.size() - 1).id);

        ImageView pack = findViewById(R.id.iv_pack);
        pack.setImageResource(deck.pack.get(deck.pack.size() - 1).id);

        ImageView pile = findViewById(R.id.iv_pile);

        garden0.setColorFilter(Color.TRANSPARENT);
        garden1.setColorFilter(Color.TRANSPARENT);
        garden2.setColorFilter(Color.TRANSPARENT);
        garden3.setColorFilter(Color.TRANSPARENT);
        garden4.setColorFilter(Color.TRANSPARENT);
        garden5.setColorFilter(Color.TRANSPARENT);
        beehive.setColorFilter(Color.TRANSPARENT);
        pack.setColorFilter(Color.TRANSPARENT);
        pile.setColorFilter(Color.TRANSPARENT);
    }

    private void selectCard(Deck.Card card, String type) {
        cardSelected = card;
        cardSelectedType = type;
    }

    private void placeCard(Deck.Card card) {
        updateCards();

        if (!deck.garden[gardenSelectedNumber].addToGarden(cardSelected)) {
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
                }
                System.out.println("GARDEN");
                break;
            case "beehive":
                System.out.println("BEEHIVE");
                deck.beehive.remove(deck.beehive.size() - 1);
                break;
            case "pack":
                System.out.println("PACK");;
                deck.pack.remove(deck.pack.size() - 1);
                break;
            case "pile":
                System.out.println("PILE");
                deck.pile.remove(deck.pile.size() - 1);
                break;
        }
        updateCards();
    }

}
