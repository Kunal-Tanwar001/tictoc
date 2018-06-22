package com.example.prate.tictoegame;


import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int PLAYER_X = 1;
    public static final int PLAYER_O = 0;
    public static final int N0_PLAYER = -1;

    public static final int INCOMPLETE = 1;
    public static final int PLAYER_X_WON = 2;
    public static final int PLAYER_O_WON = 3;
    public static final int DRAW = 4;
    public int currentstatus = DRAW;
    public int currentplayer;
    public int size = 3;

    public TTButton[][] board;
    ArrayList<LinearLayout> rows = new ArrayList<>();
    LinearLayout rootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.rootLayout);
        setupboard();


    }

    public void setupboard() {
        currentstatus = INCOMPLETE;
        currentplayer = PLAYER_O;

        rootLayout.removeAllViews();
        rows.clear();
        board = new TTButton[size][size];
        for (int i = 0; i < size; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams linearlayoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
            linearLayout.setLayoutParams(linearlayoutparams);
            rootLayout.addView(linearLayout);
            rows.add(linearLayout);

        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                TTButton button = new TTButton(this);
                LinearLayout.LayoutParams UU = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                button.setLayoutParams(UU);
                button.setOnClickListener(this);
                LinearLayout row = rows.get(i);
                row.addView(button);
                board[i][j] = button;

            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.resetItem) {
            setupboard();
        } else if (id == R.id.size3) {
            size = 3;
            setupboard();
        } else if (id == R.id.size4) {
            size = 4;
            setupboard();
        } else if (id == R.id.size5) {
            size = 5;
            setupboard();
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        if (currentstatus == INCOMPLETE) {
            TTButton button = (TTButton) v;
            button.setPlayer(currentplayer);
            checkstatus();
            togglePlayer();
            button.setEnabled(false);

        }
    }

    public void checkstatus() {
        //for rows

        for (int i = 0; i < size; i++) {
            boolean rowsame = true;
            for (int j = 1; j < size; j++) {
                if (board[i][0].getPlayer() != board[i][j].getPlayer() || board[i][j].isEmpty()) {
                    rowsame = false;
                    break;
                }
            }
            if (rowsame) {
                updateStatus(currentplayer);
                return;
            }
        }
        //FOR COLLUMNS

        for (int j = 0; j < size; j++) {
            boolean colsame = true;
            for (int i = 1; i < size; i++) {
                if (board[0][j].getPlayer() != board[i][j].getPlayer() || board[i][j].isEmpty()) {
                    colsame = false;
                    break;
                }
            }
            if (colsame) {
                updateStatus(currentplayer);
                return;
            }
        }


        boolean colsame = true;
        for (int i = 0; i < size; i++) {
            if (board[0][0].getPlayer() != board[i][i].getPlayer() || board[i][i].isEmpty()) {
                colsame = false;
                break;
            }
        }
        if (colsame) {
            updateStatus(currentplayer);
            return;
        }
        boolean clsame = true;
        for (int i = 0; i < size; i++) {
            if (board[0][size - 1].getPlayer() != board[i][size - 1 - i].getPlayer() || board[i][i].isEmpty()) {
                clsame = false;
                break;
            }
        }
        if (clsame) {
            updateStatus(currentplayer);
            return;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                TTButton button = board[i][j];
                if (button.isEmpty()) {
                    return;
                }
            }
        }

        Toast.makeText(this, "Draw", Toast.LENGTH_LONG).show();
        currentstatus = DRAW;


    }

    public void updateStatus(int playerWon) {
        if (playerWon == PLAYER_X) {
            currentstatus = PLAYER_X_WON;
            Toast.makeText(this, "PLAYER X WON", Toast.LENGTH_LONG).show();
        } else if (playerWon == PLAYER_O) {
            currentstatus = PLAYER_O_WON;
            Toast.makeText(this, "PLAYER O WON", Toast.LENGTH_LONG).show();
        }
    }

    public void togglePlayer() {
        if (currentplayer == PLAYER_O) {
            currentplayer = PLAYER_X;
        } else {
            currentplayer = PLAYER_O;
        }
    }
}


