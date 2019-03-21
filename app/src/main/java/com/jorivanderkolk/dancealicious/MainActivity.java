package com.jorivanderkolk.dancealicious;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Invoke the method that initializes all onclick listeners
        initOnClickListeners();
    }

    void initOnClickListeners() {
        // Initialize print-button onclick listener
        final TextView myWhat = findViewById(R.id.code_expl);
        myWhat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Context context = getApplicationContext();
                Snackbar.make(findViewById(R.id.ConstraintLayout),"You will get this code at the party", Snackbar.LENGTH_LONG).show();
                //Toast.makeText(context,"Yeey...", Toast.LENGTH_SHORT).show();
            }
        });

        final Button joinGame = findViewById(R.id.btn_join);
        joinGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Context context = getApplicationContext();
                // Get the input (EditView) and output (TextView) elements
                EditText input = findViewById(R.id.game_code);

                // Get text from input, paste text into output
                String result = input.getText().toString();

                if(result.equals("1234")){

                    dance();
                    Toast.makeText(context,"Correct code", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context,"Wrong code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void dance(){
        startActivity(new Intent(this, dance.class));
    }
}
