package com.example.tictactoe.Classes;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tictactoe.R;

import java.lang.reflect.Array;
import java.util.HashMap;

public class Board {
    HashMap<Button, int[]> pos = new HashMap<Button, int[]>();
    HashMap<int[], Button> btns = new HashMap<int[], Button>();
    HashMap<Button, Character> filled = new HashMap<Button, Character>();
    char turn = 'O';

    public void Board() {

    }

    public void addButton(final Button b, int x, int y) {
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fill(b);
            }
        });
        this.btns.put(new int[] {x, y}, b);
        this.pos.put(b, new int[] {x, y});
        this.filled.put(b, '\0');
    }

    public boolean isFilled(Button b) {
        return this.filled.get(b) != '\0';
    }

    public boolean fill(Button b) {
        if (isFilled(b)) {
            return false;
        }
        else {
            this.filled.put(b, turn);
            if (win(b)) {
                System.out.println("win");
            }
            b.setText("" + turn);
            if (turn == 'O') {
                turn = 'X';
            }
            else {
                turn = 'O';
            }
            return true;
        }
    }

    public boolean checkLeftDiag() {
        int[][] positions = {{0, 0}, {1, 1}, {2, 2}};
        return filled.get(btns.get(positions[0])) == filled.get(btns.get(positions[1])) && filled.get(btns.get(positions[1])) == filled.get(btns.get(positions[2]));
    }

    public boolean checkRightDiag() {
        int[][] positions = {{0, 2}, {1, 1}, {2, 0}};
        return filled.get(btns.get(positions[0])) == filled.get(btns.get(positions[1])) && filled.get(btns.get(positions[1])) == filled.get(btns.get(positions[2]));
    }

    public boolean checkRow(int r) {
        int[][] positions = {{r, 0}, {r, 1}, {r, 2}};
        return filled.get(btns.get(positions[0])) == filled.get(btns.get(positions[1])) && filled.get(btns.get(positions[1])) == filled.get(btns.get(positions[2]));
    }

    public boolean checkColumn(int c) {
        int[][] positions = {{0, c}, {1, c}, {2, c}};
        return filled.get(btns.get(positions[0])) == filled.get(btns.get(positions[1])) && filled.get(btns.get(positions[1])) == filled.get(btns.get(positions[2]));
    }
    public boolean win(Button b) {
        int[] pos = this.pos.get(b);

        int x = pos[0];
        int y = pos[1];

        if (x == y && y == 1) {
            return checkLeftDiag() || checkRightDiag();
        }
        else if (x == y) {
            return checkLeftDiag();
        }
        else if ((x == 0 && y == 2) || (x == 2 && y == 0)) {
            return checkRightDiag();
        }
        else {
            return checkRow(x) || checkColumn(y);
        }
    }

    public void resetBoard() {

    }
}
