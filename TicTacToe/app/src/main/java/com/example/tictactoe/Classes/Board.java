package com.example.tictactoe.Classes;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tictactoe.R;

import java.lang.reflect.Array;
import java.util.HashMap;

public class Board {
    Button[][] buttons = new Button[3][3];
    char[][] filled = new char[3][3];
    char turn = 'O';

    public void Board() {

    }

    public void addButton(final Button b, final int x, final int y) {
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println(x + " " +  y);
                fill(x, y);
            }
        });
        buttons[x][y] = b;
    }

    public boolean isFilled(int x, int y) {
        return this.filled[x][y] != '\0';
    }

    public boolean fill(int x, int y) {
        if (isFilled(x, y)) {
            return false;
        }
        else {
            this.filled[x][y] = turn;
            if (win(x, y)) {
            }
            buttons[x][y].setText("" + turn);
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
    public boolean win(int x, int y) {

        if (x == y && y == 1) {
            return checkLeftDiag() || checkRightDiag() || checkRow(x) || checkColumn(y);
        }
        else if (x == y) {
            return checkLeftDiag() || checkRow(x) || checkColumn(y);
        }
        else if ((x == 0 && y == 2) || (x == 2 && y == 0)) {
            return checkRightDiag() || checkRow(x) || checkColumn(y);
        }
        else {
            return checkRow(x) || checkColumn(y);
        }
    }

    public void resetBoard() {

    }
}
