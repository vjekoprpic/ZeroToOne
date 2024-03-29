package com.uninix.v.zerotoone;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class Solved extends AppCompatActivity {

    private int GRID_SIZE;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solved);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        int[] data = extras.getIntArray("results");

        assert data != null;
        int score = data[0];
        int highScore = data[1];
        GRID_SIZE = data[2];

        TextView textHighScore = findViewById(R.id.textHighScoreResult);
        textHighScore.setText(String.valueOf(highScore));

        TextView textScore = findViewById(R.id.textScoreResult);
        textScore.setText(String.valueOf(score));

        ImageView buttonPlay = findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goPlay();
            }
        });

        ImageView buttonMenu = findViewById(R.id.buttonMenu);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
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

        goPlay();

    }

    private void goPlay(){
        Intent intent = new Intent(Solved.this, Game.class);
        intent.putExtra("mode", GRID_SIZE);
        startActivity(intent);

    }
}