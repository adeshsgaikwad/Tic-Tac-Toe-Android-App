package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0-'O' and 1-'X'
    int activePlayer=0;

    boolean gameIsActive=true;

    // 2 means that it is unoccupied or unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {

        ImageView image = (ImageView) view;
        int tappedGrid = Integer.parseInt(image.getTag().toString());

        if(gameState[tappedGrid] == 2 && gameIsActive) {

            gameState[tappedGrid]=activePlayer;
            image.setTranslationY(-1000f);

            if (activePlayer == 0) {
                image.setImageResource(R.drawable.o);
                activePlayer = 1;
            } else {
                image.setImageResource(R.drawable.x);
                activePlayer = 0;
            }
            image.animate().translationYBy(1000f).rotation(720).setDuration(200);
            for(int[] winningPosition: winningPositions) {
                if( (gameState[winningPosition[0]] == gameState[winningPosition[1]]) &&
                        (gameState[winningPosition[1]] == gameState[winningPosition[2]])  &&
                            (gameState[winningPosition[0]] != 2) ) {

                    gameIsActive=false;
                    String winner ="O";
                    if(gameState[winningPosition[0]] == 1) {
                        winner = "X";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerText);
                    winnerMessage.setText("Winner : " + winner);

                    LinearLayout layout =  (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                } else {

                    boolean gameIsOver = true;

                    for(int counterstate : gameState) {
                        if(counterstate == 2)
                            gameIsOver=false;
                    }

                    if(gameIsOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerText);
                        winnerMessage.setText("Match Draw");

                        LinearLayout layout =  (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }

                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView tictactoe = (ImageView) findViewById(R.id.tictactoe);
        tictactoe.setImageResource(R.drawable.headline);
    }
}
