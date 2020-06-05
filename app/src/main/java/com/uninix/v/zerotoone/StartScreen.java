package com.uninix.v.zerotoone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StartScreen extends AppCompatActivity {

    ImageView imageFour;
    ImageView imageSix;
    ImageView imageEight;
    ImageView imageTen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        imageFour = findViewById(R.id.modeFour);
        imageSix = findViewById(R.id.modeSix);
        imageEight = findViewById(R.id.modeEight);
        imageTen = findViewById(R.id.modeTen);

        imageFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, Game.class);
                intent.putExtra("mode", 4);
                startActivity(intent);
            }
        });

        imageSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, Game.class);
                intent.putExtra("mode", 6);
                startActivity(intent);
            }
        });

        imageEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, Game.class);
                intent.putExtra("mode", 8);
                startActivity(intent);
            }
        });

        imageTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, Game.class);
                intent.putExtra("mode", 10);
                startActivity(intent);
            }
        });

    }
}
