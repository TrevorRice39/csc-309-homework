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
    boolean cardSelected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView garden0 = findViewById(R.id.iv_garden0);
        garden0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardSelected) {
                    garden0.setColorFilter(Color.TRANSPARENT);
                }
                else {
                    garden0.setColorFilter(Color.LTGRAY, PorterDuff.Mode.DARKEN);
                }
                cardSelected = !cardSelected;
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
        beehive.setImageResource(deck.pack.get(deck.pack.size() - 1).id);

    }
}
