package com.preethi.gameconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = X,  1 = Red

    int activePlayer = 0;

    boolean gameIsActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};



    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int i = Integer.parseInt(counter.getTag().toString());

        if (gameState[i] == 2 && gameIsActive) {

            gameState[i] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.xyellow);

                activePlayer = 1;
            }

            else if (activePlayer == 1) {

                counter.setImageResource(R.drawable.ored);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(180).setDuration(200);


            for (int[] winningPosition : winningPositions) {

                if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {

                    gameIsActive = false;

                    System.out.println(gameState[winningPosition[0]]);

                    String winner = "O";

                    if(gameState[winningPosition[0]] == 0) {

                        winner = "X";
                    }

                    TextView winnerMessage =  findViewById(R.id.winnerTextView);

                    winnerMessage.setText(winner + " wins!");

                    LinearLayout layout = findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);

                    layout.setAlpha(0f);

                    layout.animate().alpha(1f).setDuration(500);

                    layout.bringToFront();

                }

                else  {

                    boolean gameIsOver = true;

                    for (int counterState  : gameState) {

                        if (counterState == 2) {

                            gameIsOver =  false;
                        }

                    }

                    if(gameIsOver && gameIsActive) {

                        TextView winnerMessage = findViewById(R.id.winnerTextView);

                        winnerMessage.setText("It's a draw !");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                        layout.setAlpha(0f);

                        layout.animate().alpha(1f).setDuration(500);

                        layout.bringToFront();

                    }

                }
            }

        }

    }


    public void playAgain (View view) {

        LinearLayout layout = findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        gameIsActive = true;

        for (int i = 0; i < gameState.length; i++)
        {

            gameState[i] = 2;

        }

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        gridLayout.bringToFront();

        for (int i = 0; i < gridLayout.getChildCount(); i++)
        {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }
}
