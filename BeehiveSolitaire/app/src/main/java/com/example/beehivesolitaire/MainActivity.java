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
        System.out.println("here");
        if (!deck.garden[secondGardenSelectedNumber].addToGarden(cardSelected)) {
            return;
        }
        System.out.println("here");
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
