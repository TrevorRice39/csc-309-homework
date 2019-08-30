package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tictactoe.Classes.Board;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Board board = new Board();

        board.addButton((Button)findViewById(R.id.btn_00), 0, 0);
        board.addButton((Button)findViewById(R.id.btn_01), 0, 1);
        board.addButton((Button)findViewById(R.id.btn_02), 0, 2);

        board.addButton((Button)findViewById(R.id.btn_10), 1, 0);
        board.addButton((Button)findViewById(R.id.btn_11), 1, 1);
        board.addButton((Button)findViewById(R.id.btn_12), 1, 2);

        board.addButton((Button)findViewById(R.id.btn_20), 2, 0);
        board.addButton((Button)findViewById(R.id.btn_21), 2, 1);
        board.addButton((Button)findViewById(R.id.btn_22), 2, 2);

        ((Button)findViewById(R.id.btn_reset)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.resetBoard();
            }
        });

        Toast toast = Toast.makeText(getApplicationContext(),
                "This is a message displayed in a Toast",
                Toast.LENGTH_SHORT);

        toast.show();

    }
}
