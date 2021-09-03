package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final Button [] buttons = new Button[9];
    private TextView PlayerOneScore, PlayerTwoScore, whosround;
    private boolean side = true;
    private int RoundCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < buttons.length; i ++) {
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }

        Button btn_Reset = findViewById(R.id.btn_Reset);
        PlayerOneScore = findViewById(R.id.PlayerOneScore);
        PlayerTwoScore = findViewById(R.id.PlayerTwoScore);
        whosround = findViewById(R.id.whosround);

        btn_Reset.setOnClickListener(view -> {
            for (Button button : buttons) {
                button.setText("");
                PlayerOneScore.setText("0");
                PlayerTwoScore.setText("0");
                side = true;
                RoundCount = 0;
                whosround.setText("O");
            }
        });
    }// end OnCreate

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if (! ( (Button) v ).getText().toString().equals("")) {
            return;
        }

        if (side) {
            ( (Button) v ).setText("O");
            side = false;
            whosround.setText("X");
        }
        else {
            ( (Button) v ).setText("X");
            side = true;
            whosround.setText("O");
        }
        RoundCount ++;

        if (CheckWin () != 0) {
            if (CheckWin () == 1) {
                Toast.makeText(this, "Player One Win!", Toast.LENGTH_SHORT).show();
                int PlayerOneScoreCount = Integer.parseInt(PlayerOneScore.getText().toString()) ;
                PlayerOneScoreCount ++;
                PlayerOneScore.setText(Integer.toString(PlayerOneScoreCount));
            }
            if (CheckWin () == 2) {
                Toast.makeText(this, "Player Two Win!", Toast.LENGTH_SHORT).show();
                int PlayerTwoScoreCount = Integer.parseInt(PlayerTwoScore.getText().toString()) ;
                PlayerTwoScoreCount ++;
                PlayerTwoScore.setText(Integer.toString(PlayerTwoScoreCount));
            }
            for (Button button : buttons) {
                button.setText("");
                side = true;
                RoundCount = 0;
                whosround.setText("O");
            }
            return ;
        }
        if (RoundCount == 9) {
            Toast.makeText(this, "Tie, Restart.", Toast.LENGTH_SHORT).show();
            for (Button button : buttons) {
                button.setText("");
                side = true;
                RoundCount = 0;
                whosround.setText("O");
            }
        }
    }

    private int CheckWin () {
        int [][] win_line = {
                {0,1,2}, {3,4,5}, {6,7,8},
                {0,3,6}, {1,4,7}, {2,5,8},
                {0,4,8}, {2,4,6} };

        for (int [] line : win_line) {
            String btn_0_txt = buttons[line[0]].getText().toString();
            String btn_1_txt = buttons[line[1]].getText().toString();
            String btn_2_txt = buttons[line[2]].getText().toString();

            boolean condition_0 = btn_0_txt.equals("");
            boolean condition_1 = btn_0_txt.equals(btn_1_txt);
            boolean condition_2 = btn_0_txt.equals(btn_2_txt);

            if (! condition_0 && condition_1 && condition_2) {
                if (btn_0_txt.equals("O")) {
                    return 1;
                }
                else {
                    return 2;
                }
            }
        }

        return 0;
    }
}// end class