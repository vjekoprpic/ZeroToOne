package com.uninix.v.zerotoone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Objects;


public class Game extends AppCompatActivity {

    private int GRID_SIZE;
    private int counter = 0;
    private int[][] board;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        GRID_SIZE = Objects.requireNonNull(intent.getExtras()).getInt("mode");
        board = new int[GRID_SIZE][GRID_SIZE];

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setColumnCount(GRID_SIZE);
        gridLayout.setRowCount(GRID_SIZE);


        for (int i = 0; i < GRID_SIZE; i++){
            Arrays.fill(board[i], 1);
        }

        for(int i = 0; i < GRID_SIZE; i++){
            for(int j = 0; j < GRID_SIZE; j++){

                ImageView cell = new ImageView(this);
                cell.setImageDrawable(getDrawable(R.drawable._1x4));

                int cellSize;
                switch (GRID_SIZE){
                    case 4: cellSize = 60;
                            break;
                    case 6: cellSize = 53;
                            break;
                    case 8: cellSize = 40;
                            break;
                    case 10: cellSize = 35;
                            break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + GRID_SIZE);
                }
                
                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = (int) convertDpToPx(cellSize);
                param.width = (int) convertDpToPx(cellSize);
                param.rowSpec = GridLayout.spec(i);
                param.columnSpec = GridLayout.spec(j);
                cell.setLayoutParams(param);

                final int finalI = i;
                final int finalJ = j;

                cell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateBoard(finalI, finalJ, false);
                    }
                });

                gridLayout.addView(cell);

            }
        }

        boardGenerator();
    }

    private void updateBoard(int i, int j, boolean gen){

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        try {
            board[i-1][j] = (board[i-1][j] == 0) ? 1 : 0;
            ImageView cell = (ImageView) gridLayout.getChildAt(getIndex(i-1, j));
            cell.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("_" + board[i-1][j] + "x4", "drawable", getPackageName())));
        }catch (Exception ignored){}
        try {
            board[i][j-1] = (board[i][j-1] == 0) ? 1 : 0;
            ImageView cell = (ImageView) gridLayout.getChildAt(getIndex(i, j-1));
            cell.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("_" + board[i][j-1] + "x4", "drawable", getPackageName())));
        }catch (Exception ignored){}
        try {
            board[i+1][j] = (board[i+1][j] == 0) ? 1 : 0;
            ImageView cell = (ImageView) gridLayout.getChildAt(getIndex(i+1, j));
            cell.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("_" + board[i+1][j] + "x4", "drawable", getPackageName())));
        }catch (Exception ignored){}
        try {
            board[i][j+1] = (board[i][j+1] == 0) ? 1 : 0;
            ImageView cell = (ImageView) gridLayout.getChildAt(getIndex(i, j+1));
            cell.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("_" + board[i][j+1] + "x4", "drawable", getPackageName())));
        }catch (Exception ignored){}
        counter++;
        TextView result = findViewById(R.id.result);
        result.setText(String.valueOf(counter));

        if (!gen) solvedCheck();
    }

    private void solvedCheck(){
        for(int i = 0; i < GRID_SIZE; i++){
            for(int j = 0; j < GRID_SIZE; j++) {
                if (board[i][j] == 0){
                    return;
                }
            }
        }
        solved();
    }

    private int getIndex(int i, int j){
        return i*GRID_SIZE + j;
    }

    private void boardGenerator() {
        int iterations = GRID_SIZE*2 + (int)(Math.random() * ((GRID_SIZE*4 - GRID_SIZE*2) + 1));

        for (int i = 0; i < iterations; i++){
            updateBoard(randomGen(), randomGen(), true);
        }

        counter = 0;
        TextView result = findViewById(R.id.result);
        result.setText(String.valueOf(counter));
    }

    private int randomGen(){
        return (int)(Math.random() * GRID_SIZE);
    }

    private void setHighScore(int highScore) {
        SharedPreferences sharedPref = getSharedPreferences("highScore" + GRID_SIZE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("highScore" + GRID_SIZE, highScore);
        editor.apply();
    }

    private int getHighScore() {
        SharedPreferences sharedPref = getSharedPreferences("highScore" + GRID_SIZE, MODE_PRIVATE);
        return sharedPref.getInt("highScore" + GRID_SIZE, 0);
    }

    private float convertDpToPx(float dp) {
        return dp * this.getResources().getDisplayMetrics().density;
    }

    private void solved(){

        int highScore = getHighScore();

        if(highScore == 0 || highScore > counter){
            setHighScore(counter);
            Toast toast = Toast.makeText(getApplicationContext(), "SOLVED! NEW HIGH SCORE!!!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "SOLVED!", Toast.LENGTH_SHORT);
            toast.show();
        }

        int[] data = {counter, highScore, GRID_SIZE};
        Intent intent = new Intent(Game.this, Solved.class);
        intent.putExtra("results", data);
        startActivity(intent);
    }
}