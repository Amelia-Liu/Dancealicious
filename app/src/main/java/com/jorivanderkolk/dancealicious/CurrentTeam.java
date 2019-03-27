package com.jorivanderkolk.dancealicious;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CurrentTeam extends AppCompatActivity {

    TextView yourT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_team);

        yourT = findViewById(R.id.Your_Team);

        setData();
    }

    private void setData(){
        yourT.setText("You joined team " + LoadPreferences("team"));
    }

    public void SavePreferences(String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences("PLAYER_DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String LoadPreferences(String key) {
        SharedPreferences pref = getSharedPreferences("PLAYER_DATA", MODE_PRIVATE);
        return pref.getString(key, "No team available");
    }



}
