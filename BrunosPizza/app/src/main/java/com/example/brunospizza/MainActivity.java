package com.example.brunospizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private double taxRate = 0.06;
    private double adultPrice = 29.95;
    private double childPrice = 15.95;

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

        // subtract one from the number of adults
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

        // add one to the number of adults
        btn_adultPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tv_numAdult.getText().toString()) + 1;
                tv_numAdult.setText(num + "");
                updateCost();
            }
        });

        // subtract 1 from number of children
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

        // add one to the number of children
        btn_childAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tv_numChild.getText().toString());
                num++;
                tv_numChild.setText(num + "");
                updateCost();
            }
        });

        // submit and move to payment, if at least one adult is entered
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

    // calculates the cost and updates the cost text view
    protected void updateCost() {
        TextView adult = findViewById(R.id.tv_adult_num);
        TextView child = findViewById(R.id.tv_child_num);
        TextView cost = findViewById(R.id.tv_cost_val);

        int adultNum = Integer.parseInt(adult.getText().toString());
        int childNum = Integer.parseInt(child.getText().toString());

        double costNum = adultNum * adultPrice + childNum * childPrice;
        double total = costNum + costNum * taxRate;

        String totalString = String.format("%.2f", total);
        cost.setText("$" + totalString + " ($" + String.format("%.2f", taxRate) + " tax)");

    }

    // helper function for a toast
    protected void sendToast(String text) {
        Context context = getApplicationContext();

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();;

    }

    // keys for our preferences
    static String child_price_key = "pref_child_price";
    static String adult_price_key = "pref_adult_price";
    static String tax_rate_key = "pref_tax_rate";

    @Override
    public void onResume() {
        super.onResume();


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        // get the child price from the preferences
        childPrice = Double.parseDouble(preferences.getString(child_price_key, "15.95"));
        // get the adult price from the preferences
        adultPrice = Double.parseDouble(preferences.getString(adult_price_key, "29.95"));
        // get the tax rate from the preferences
        taxRate = Double.parseDouble(preferences.getString(tax_rate_key, "0.06"));

        // adjust the textviews
        TextView tv_child_price = findViewById(R.id.tv_children);
        tv_child_price.setText("Children ($" + String.format("%.2f", childPrice) + ")");

        TextView tv_adult_price = findViewById(R.id.tv_adult);
        tv_adult_price.setText("Adults ($" + String.format("%.2f", adultPrice) + ")");

        // update the cost with the new values
        updateCost();

    }
    static final String SAVE_NUM_ADULT = "numAdult";
    static final String SAVE_NUM_CHILD = "numChild";

    // saving the data in the activity
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

    // restoring the data
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

    // system is ready to create the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    // respond to a menu item click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // which item did they click?
        switch ( item.getItemId() ) {
            // go to settings activity
            case R.id.menu_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;

            default:
                // unknown item
                return false;
        }
    }
}
