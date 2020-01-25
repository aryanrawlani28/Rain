package com.aryan.rain.entity.mob;

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;
import com.aryan.rain.input.Keyboard;

import java.util.Scanner;

public class Player extends Mob {

    private Keyboard input;

    public Player(Keyboard input){
        this.input = input;
    }

    // Sometimes players are created at a specific location.
    Player(int x, int y, Keyboard input){
        this.x = x;
        this.y = y;

        this.input = input;
    }


    public void update(){
        // When press keys, move our player from here. Affects the entity x and y.
        // If user presses up+down at same time, this will effectively cancel movement and player will not move.
        int xa = 0, ya = 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;

        if (xa != 0 || ya != 0){
            move(xa, ya);
        }
    }

    // You don't wanna center it always to the player.
    public void render(Screen screen){
        int xx = x - 16;
        int yy = y - 16;

        screen.renderPlayer(xx, yy, Sprite.player0);
        screen.renderPlayer(xx+16, yy, Sprite.player1);
        screen.renderPlayer(xx, yy+16, Sprite.player2);
        screen.renderPlayer(xx+16, yy+16, Sprite.player3);
    }
}
