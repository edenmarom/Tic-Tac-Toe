package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameBoard = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public static int moves = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (gameBoard[tappedImage] == 2) {
            moves++;
            gameBoard[tappedImage] = activePlayer;

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                ImageView status = findViewById(R.id.status);
                status.setImageResource(R.drawable.oplay);
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                ImageView status = findViewById(R.id.status);
                status.setImageResource(R.drawable.xplay);
            }
        }
        int winFlag = 0;
        int posIndex = 0;
        // Check if any player has won
        for (int[] winPosition : winPositions) {
            if (gameBoard[winPosition[0]] == gameBoard[winPosition[1]] &&
                    gameBoard[winPosition[1]] == gameBoard[winPosition[2]] &&
                    gameBoard[winPosition[0]] != 2) {
                winFlag = 1;
                int winnerImg;
                if (gameBoard[winPosition[0]] == 0) {
                    winnerImg = R.drawable.xwin;
                } else {
                    winnerImg = R.drawable.owin;
                }
                ImageView status = findViewById(R.id.status);
                status.setImageResource(winnerImg);

                Button resetBtn = findViewById(R.id.resetButton);
                resetBtn.setVisibility(View.VISIBLE);
                resetBtn.setOnClickListener(this::gameReset);

                ImageView winLine = findViewById(R.id.winLine);

                String uri = "@drawable/mark"+ posIndex;
                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                winLine.setImageResource(imageResource);
            }
            posIndex++;
        }
        if (moves == 9 && winFlag == 0) {
            ImageView status = findViewById(R.id.status);
            status.setImageResource(R.drawable.nowin);

            Button resetBtn = findViewById(R.id.resetButton);
            resetBtn.setVisibility(View.VISIBLE);
            resetBtn.setOnClickListener(this::gameReset);
        }
    }

    public void gameReset(View view) {
        activePlayer = 0;
        moves = 0;
        Arrays.fill(gameBoard, 2);
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView) findViewById(R.id.winLine)).setImageResource(0);
        ImageView status = findViewById(R.id.status);
        status.setImageResource(R.drawable.xplay);
    }
}