package com.aryan.rain.entity.mob;

import com.aryan.rain.input.Keyboard;

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

    public void render(){

    }
}
