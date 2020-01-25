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
        if (input.up) y--;
        if (input.down) y++;
        if (input.left) x--;
        if (input.right) x++;
    }

    public void render(){

    }
}
