package com.jorivanderkolk.dancealicious;

import android.content.Context;
import android.content.Intent;
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
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText welcomeNm;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        welcomeNm = findViewById(R.id.game_name);

        setName();

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
                EditText inputCode = findViewById(R.id.game_code);
                EditText inputName = findViewById(R.id.game_name);

                // Get text from input, paste text into output
                String code = inputCode.getText().toString();
                String name = inputName.getText().toString();

                if(code.equals("1234")){

                    dance();
                    writeToFile(name, context);
                    Toast.makeText(context,"Correct code", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context,"Wrong code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void dance(){
        startActivity(new Intent(this, ChooseTeam.class));
    }

    private void writeToFile(String data,Context context) {

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Log.v("Success", "Successfully written to file!");
        }catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void setName() {
        String savedName = readFromFile(context);
        if (savedName != null){
            welcomeNm.setText("");
            welcomeNm.setText(savedName);
        }
    }

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
}
