package com.jorivanderkolk.dancealicious;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ChooseTeam extends AppCompatActivity {

    Context context;
    TextView welcomeMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_team);
        context = getApplicationContext();

        welcomeMsg = findViewById(R.id.app_name);

        setName();

        // Invoke the method that initializes all onclick listeners
        initOnClickListeners();
    }

    void initOnClickListeners() {

        final Button joinOrange = findViewById(R.id.btn_orange);
        final Button joinBlue = findViewById(R.id.btn_blue);
        final Button joinRed = findViewById(R.id.btn_red);
        final Button joinYellow = findViewById(R.id.btn_yellow);


        joinOrange.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                joinTeam("Orange");
            }
        });

        joinBlue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                joinTeam("Blue");
            }
        });

        joinRed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                joinTeam("Red");
            }
        });

        joinYellow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                joinTeam("Yellow");
            }
        });
    }

    private void joinTeam(String team){
        SavePreferences("team", team);
        Toast.makeText(context, "Join team " + team, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, CurrentTeam.class));
    }


    private void setName() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Welcome, ");
        //stringBuilder.append(readFromFile(context));
        stringBuilder.append(LoadPreferences("name"));
        stringBuilder.append("!");

        String playerName = stringBuilder.toString();

        //final TextView welcomeName = findViewById(R.id.app_name);
        welcomeMsg.setText("");
        welcomeMsg.setText(playerName);
    }

    /*
    private String readFromFile(Context context) {

        String name = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    //receiveString += "\n";
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                name = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return name;
    }
    */
    public void SavePreferences(String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences("PLAYER_DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String LoadPreferences(String key) {
        SharedPreferences pref = getSharedPreferences("PLAYER_DATA", MODE_PRIVATE);
        return pref.getString(key, "No name given");
    }
}
