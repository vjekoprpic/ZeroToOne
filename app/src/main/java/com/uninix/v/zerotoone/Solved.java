package com.uninix.v.zerotoone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class Solved extends AppCompatActivity {

    private int GRID_SIZE;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solved);

        Bundle extras = getIntent().getExtras();
        int[] data = extras.getIntArray("results");

        assert data != null;
        int score = data[0];
        int highScore = data[1];

        TextView textHighScore = findViewById(R.id.textHighScoreResult);
        textHighScore.setText(String.valueOf(highScore));

        TextView textScore = findViewById(R.id.textScoreResult);
        textScore.setText(String.valueOf(score));

        ImageView imageButton = findViewById(R.id.playButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Solved.this, StartScreen.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Solved.this, StartScreen.class);
        startActivity(intent);
    }
}