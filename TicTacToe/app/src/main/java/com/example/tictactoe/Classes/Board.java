package com.example.tictactoe.Classes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tictactoe.MainActivity;
import com.example.tictactoe.R;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
public class Board {
    Button[][] buttons = new Button[3][3]; // keeps track of our buttons so we can update the text
    char[][] filled = new char[3][3]; // simple array to tell what buttons have been clicked
    List<String> clicked = new ArrayList<String>(); // list used to randomly select moves for the computer's turn
    char turn = 'O';
    Random rand = new Random();

    Activity activity;
    AlertDialog.Builder playerWinDialog, computerWinDialog, tieDialog;
    TextView winsTextView, tiesTextView, lossesTextView; // displays number of wins, ties, and losses to user

    public Board(Activity activity) { // passing in activity so we can update the screen within this class
        this.activity = activity;

        // creating the dialogs for win, loss, and tie events
        this.playerWinDialog = new AlertDialog.Builder(this.activity);
        playerWinDialog.setMessage("You win!");
        playerWinDialog.setTitle("Game over");

        this.computerWinDialog = new AlertDialog.Builder(this.activity);
        computerWinDialog.setMessage("The computer wins!");
        computerWinDialog.setTitle("Game over");

        this.tieDialog = new AlertDialog.Builder(this.activity);
        tieDialog.setMessage("Tie!");
        tieDialog.setTitle("Game over");

        // in any case, win, tie, or loss, reset the board when "New Game" button is clicked
        playerWinDialog.setPositiveButton("New Game",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        resetBoard();
                    }
                });

        computerWinDialog.setPositiveButton("New Game",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        resetBoard();
                    }
                });

        tieDialog.setPositiveButton("New Game",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        resetBoard();
                    }
                });

        playerWinDialog.setCancelable(false);
        computerWinDialog.setCancelable(false);
        tieDialog.setCancelable(false);

        // adding all 9 buttons to our class
        addButton((Button)this.activity.findViewById(R.id.btn_00), 0, 0);
        addButton((Button)this.activity.findViewById(R.id.btn_01), 0, 1);
        addButton((Button)this.activity.findViewById(R.id.btn_02), 0, 2);

        addButton((Button)this.activity.findViewById(R.id.btn_10), 1, 0);
        addButton((Button)this.activity.findViewById(R.id.btn_11), 1, 1);
        addButton((Button)this.activity.findViewById(R.id.btn_12), 1, 2);

        addButton((Button)this.activity.findViewById(R.id.btn_20), 2, 0);
        addButton((Button)this.activity.findViewById(R.id.btn_21), 2, 1);
        addButton((Button)this.activity.findViewById(R.id.btn_22), 2, 2);

        // button that resets the score, in the case you want to start fresh
        ((Button)this.activity.findViewById(R.id.btn_reset)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                winsTextView.setText("0");
                tiesTextView.setText("0");
                lossesTextView.setText("0");
            }
        });

        this.winsTextView = this.activity.findViewById(R.id.tv_num_wins);
        this.tiesTextView = this.activity.findViewById(R.id.tv_num_ties);
        this.lossesTextView = this.activity.findViewById(R.id.tv_num_losses);
        this.winsTextView.setText("0");
        this.tiesTextView.setText("0");
        this.lossesTextView.setText("0");

        // adding initial values to list, 0 - 8 which correspond to each button in a 3x3 array
        /*
            i.e.
            0 => 0, 0
            3 => 1, 0
            5 => 1, 2
            n => n/3, n%3
         */
        for (int i = 0; i < 9; i++) {
            clicked.add(i+"");
        }

    }

    /*
        This function adds the button to our buttons array, and sets it's on click function which is
        to fill the button with either an 'X' or 'O';

        @params:
            Button b: the button being added
            int x, y: the position of the button in the array
     */
    public void addButton(final Button b, final int x, final int y) {
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fill(x, y);
            }
        });
        buttons[x][y] = b;
    }

    /*
        Returns if the square has been selected already by looking it up in the filled array

        @params:
            int x,  y: position of button to check
     */
    public boolean isFilled(int x, int y) {
        return this.filled[x][y] != '\0';
    }


    /*
        This function handles clicking the buttons, and also determines if there is a win or a tie
        directly after a turn ends. This function returns false if there is already an 'X' or 'O' in
        the inputted position. This function also handles the computer's turn. The computer automatically
        makes its turn directly after the player clicks, in the case that there is a legal move available

        @params:
            int x, y: position clicked

     */
    public boolean fill(int x, int y) {
        if (isFilled(x, y)) { // if filled, return false
            return false;
        }
        else {
            // putting the players character ('O') in the filled array
            this.filled[x][y] = turn;

            // removing this move from our clicked list
            this.clicked.remove(x*3 +y+"");

            // setting the button text to turn
            this.buttons[x][y].setText("" + turn);

            // check if there is a win or tie after the player moves
            if (win(x, y)) {
                // adding 1 to the win text view
                this.winsTextView.setText((Integer.parseInt(this.winsTextView.getText().toString())+1) + "");
                this.playerWinDialog.show();
                return false;
            }
            else if (this.clicked.size() == 0) {
                // adding 1 to the ties text view
                this.tiesTextView.setText((Integer.parseInt(this.tiesTextView.getText().toString())+1) + "");
                this.tieDialog.show();
                return false;
            }

            // changing turn
            this.turn = 'X';

            // finding a random position in our list for our computer's turn
            int random = rand.nextInt(clicked.size());

            // parsing the position
            int pos = Integer.parseInt(clicked.get(random));

            // removing it from the list so it cant be clicked again
            this.clicked.remove(pos+"");
            System.out.println("pos = " + pos/3 + " " + pos%3);
            this.buttons[pos/3][pos%3].setText("" + turn);
            this.filled[pos/3][pos%3] = turn;

            // check if there is a win or tie after the computer's turn
            if (win(pos/3, pos%3)) {
                // adding 1 to the losses text view
                this.lossesTextView.setText((Integer.parseInt(this.lossesTextView.getText().toString())+1) + "");
                this.computerWinDialog.show();
                return false;
            }
            // if the size is zero, no more moves are left for th ecomputer
            else if (this.clicked.size() == 0) {
                // adding 1 to the ties text view
                this.tiesTextView.setText((Integer.parseInt(this.tiesTextView.getText().toString())+1) + "");
                this.tieDialog.show();
                return false;
            }

            this.turn = 'O';
            return true;
        }
    }

    /*
        A group of functions to check if there is a win
     */
    public boolean checkLeftDiag() {
        int[][] positions = {{0, 0}, {1, 1}, {2, 2}};
        return (filled[positions[0][0]][positions[0][1]] == filled[positions[1][0]][positions[1][1]]) && (filled[positions[1][0]][positions[1][1]] == filled[positions[2][0]][positions[2][1]]);
    }

    public boolean checkRightDiag() {
        int[][] positions = {{0, 2}, {1, 1}, {2, 0}};
        return (filled[positions[0][0]][positions[0][1]] == filled[positions[1][0]][positions[1][1]]) && (filled[positions[1][0]][positions[1][1]] == filled[positions[2][0]][positions[2][1]]);
    }

    public boolean checkRow(int r) {
        int[][] positions = {{r, 0}, {r, 1}, {r, 2}};
        return (filled[positions[0][0]][positions[0][1]] == filled[positions[1][0]][positions[1][1]]) && (filled[positions[1][0]][positions[1][1]] == filled[positions[2][0]][positions[2][1]]);
    }

    public boolean checkColumn(int c) {
        int[][] positions = {{0, c}, {1, c}, {2, c}};
        return (filled[positions[0][0]][positions[0][1]] == filled[positions[1][0]][positions[1][1]]) && (filled[positions[1][0]][positions[1][1]] == filled[positions[2][0]][positions[2][1]]);
    }

    /*
        Wrapper function that returns a boolean if there is a win in the current state of the board.
        It only checks what is needed, therefore, the position of the last move is passed into the function

        @params:
            int x, y: position to check from, the only current win possible must contain this position
     */
    public boolean win(int x, int y) {
        // initially there is no win
        boolean won = false;

        // always check row and column for win
        won = checkRow(x) || checkColumn(y);

        // if x == y, check left diagonal
        if (x == y) {
            won = won || checkLeftDiag();
            // if x == y, we must check the right diagonal aswel
            if (x == 1) {
                won = won || checkRightDiag();
            }
        }
        // if the difference is 2, check the right diagonal
        else if (Math.abs(x-y) == 2) {
            won = won || checkRightDiag();
        }

        return won;
    }

    /*
        Resets our buttons text, turn, the clicked list, and the filled array. It also re-initializes
        the clicked array
     */
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.buttons[i][j].setText("");
                this.turn = 'O';
                this.filled[i][j] = '\0';
            }
        }

        this.clicked.clear();

        for (int i = 0; i < 9; i++) {
            clicked.add(i+"");
        }
    }
}
