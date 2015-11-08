package com.oz.button123;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class StartMenu extends ActionBarActivity {

    private EditText num;
    private TextView txtout, txtmin,txtmax,txtsecret;

    String input;
    int min = 0, max= 100 , secret,flag = 0,guess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        //receive data from Main
        String sec = getIntent().getExtras().getString("sNumber");
        secret = Integer.parseInt(sec);
        txtsecret = (TextView) findViewById(R.id.txtSecret);
        txtsecret.setText(sec);

        txtmin = (TextView) findViewById(R.id.txtMin);
        txtmax = (TextView) findViewById(R.id.txtMax);

        txtmin.setText("0");
        txtmax.setText("100");

        Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rn = new Random();
                secret = rn.nextInt(100) + 1;

                txtsecret.setText(Integer.toString(secret));
                  min = 0;
                  max = 100;
                txtmin = (TextView) findViewById(R.id.txtMin);
                txtmax = (TextView) findViewById(R.id.txtMax);
                txtmin.setText(Integer.toString(min));
                txtmax.setText(Integer.toString(max));
            }
        }
        );
    }

    public void buttonOnClick(View v) {

        Button btnCheck = (Button) v;
        num = (EditText) findViewById(R.id.txtNum);
        txtout = (TextView) findViewById(R.id.txtOut);
        txtmin = (TextView) findViewById(R.id.txtMin);
        txtmax = (TextView) findViewById(R.id.txtMax);
        txtsecret = (TextView) findViewById(R.id.txtSecret);

        Toast toast =new Toast(getApplicationContext());

        input = num.getText().toString();
        guess = Integer.parseInt(input);

        if(guess <= min || guess >= max) {
            //txtout.setText("Plz try again!");
            toast.makeText(StartMenu.this,"Plz try again!",toast.LENGTH_SHORT).show();
        }
        else{
            if (guess == secret) {
                txtout.setText("u got it!");
            }

            else if(guess > secret) {
                max = guess;
                txtout.setText(num.getText());
                txtmin.setText(Integer.toString(min));
                txtmax.setText(Integer.toString(max));
            }

            else{
                min = guess;
                txtout.setText(num.getText());
                txtmin.setText(Integer.toString(min));
                txtmax.setText(Integer.toString(max));
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
