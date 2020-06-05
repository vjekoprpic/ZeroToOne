package com.uninix.v.zerotoone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StartScreen extends AppCompatActivity {

    ImageView ibcet;
    ImageView ib6;
    ImageView ib8;
    ImageView ib10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        ibcet = (ImageView) findViewById(R.id.modeFour);
        ib6 = (ImageView) findViewById(R.id.modeSix);
        ib8 = (ImageView) findViewById(R.id.modeEight);
        ib10 = (ImageView) findViewById(R.id.modeTen);

        ibcet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, Game.class);
                intent.putExtra("mode", 4);
                startActivity(intent);
            }
        });

        ib6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, Game.class);
                intent.putExtra("mode", 6);
                startActivity(intent);
            }
        });

        ib8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, Game.class);
                intent.putExtra("mode", 8);
                startActivity(intent);
            }
        });

        ib10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, Game.class);
                intent.putExtra("mode", 10);
                startActivity(intent);
            }
        });

    }
}
