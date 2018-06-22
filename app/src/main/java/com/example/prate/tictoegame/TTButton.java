package com.example.prate.tictoegame;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class TTButton extends AppCompatButton {

    private int player =MainActivity.N0_PLAYER;
    public TTButton (Context context) {
        super(context);
    }
    public void setPlayer(int player){
       this.player=player;

        if(player==MainActivity.PLAYER_X){
            this.setText("X");

        }
        else if(player==MainActivity.PLAYER_O){
            this.setText("O");

        }

    }
    public int getPlayer(){
        return this.player;
    }
    public Boolean isEmpty(){
         if(this.player==MainActivity.N0_PLAYER){
             return true;
         }
         else
             return false;
    }


}

