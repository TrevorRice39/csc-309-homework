package com.example.beehivesolitaire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creating a new deck
        Deck deck = new Deck();

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

    }
}
