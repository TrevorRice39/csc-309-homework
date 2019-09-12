package com.example.brunospizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv_numAdult = (TextView)findViewById(R.id.tv_adult_num);
        final TextView tv_numChild = (TextView)findViewById(R.id.tv_child_num);

        Button btn_adultMin = (Button)findViewById(R.id.btn_adult_min);
        Button btn_adultPlus = (Button)findViewById(R.id.btn_adult_add);

        Button btn_childMin = (Button)findViewById(R.id.btn_child_min);
        Button btn_childAdd = (Button)findViewById(R.id.btn_child_add);

        Button btn_submit = (Button)findViewById(R.id.btn_submit);

        btn_adultMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tv_numAdult.getText().toString());
                if (num > 0) {
                    num--;
                }
                tv_numAdult.setText(num + "");
                updateCost();
            }
        });

        btn_adultPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tv_numAdult.getText().toString()) + 1;
                tv_numAdult.setText(num + "");
                updateCost();
            }
        });

        btn_childMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tv_numChild.getText().toString());
                if (num > 0) {
                    num--;
                }
                tv_numChild.setText(num + "");
                updateCost();
            }
        });

        btn_childAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tv_numChild.getText().toString());
                num++;
                tv_numChild.setText(num + "");
                updateCost();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tv_numAdult.getText().toString());
                if (num == 0) {
                    sendToast("You must have at least one adult!");
                }
                else {
                    Intent intent = new Intent(MainActivity.this, CreditCard.class);
                    intent.putExtra("EXTRA_SESSION_ID", num);
                    startActivity(intent);
                }

            }
        });




    }

    protected void updateCost() {
        TextView adult = findViewById(R.id.tv_adult_num);
        TextView child = findViewById(R.id.tv_child_num);
        TextView cost = findViewById(R.id.tv_cost_val);

        int adultNum = Integer.parseInt(adult.getText().toString());
        int childNum = Integer.parseInt(child.getText().toString());

        double costNum = adultNum * 29.95 + childNum * 15.95;
        double total = costNum + costNum * 0.06;

        String totalString = String.format("%.2f", total);
        cost.setText(totalString + "");

    }
    protected void sendToast(String text) {
        Context context = getApplicationContext();

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();;

    }
    static final String SAVE_NUM_ADULT = "numAdult";
    static final String SAVE_NUM_CHILD = "numChild";
    @Override
    public void onSaveInstanceState( Bundle savedInstanceState ) {
        super.onSaveInstanceState( savedInstanceState );

        TextView tv_numAdult = (TextView)findViewById(R.id.tv_adult_num);
        TextView tv_numChild = (TextView)findViewById(R.id.tv_child_num);
        TextView tv_cost = findViewById(R.id.tv_cost_val);

        // save the current letters on the board
        int numAdult = Integer.parseInt(tv_numAdult.getText().toString());
        int numChild = Integer.parseInt(tv_numChild.getText().toString());

        savedInstanceState.putInt(SAVE_NUM_ADULT, numAdult);
        savedInstanceState.putInt(SAVE_NUM_CHILD, numChild);
        updateCost();
    }

    @Override
    public void onRestoreInstanceState( Bundle savedInstanceState ) {
        super.onRestoreInstanceState( savedInstanceState );

        TextView tv_numAdult = (TextView)findViewById(R.id.tv_adult_num);
        TextView tv_numChild = (TextView)findViewById(R.id.tv_child_num);

        int numAdult = savedInstanceState.getInt(SAVE_NUM_ADULT);
        int numChild = savedInstanceState.getInt(SAVE_NUM_CHILD);

        tv_numAdult.setText(numAdult + "");
        tv_numChild.setText(numChild + "");
        updateCost();
    }
}
