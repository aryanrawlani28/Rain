package com.aryan.rain.entity.mob;

import com.aryan.rain.entity.Entity;
import com.aryan.rain.graphics.Sprite;

public class Mob extends Entity {
    protected Sprite sprite;
    protected int dir = 0;              // 0 -> north. 1 -> east, 2 -> south, 3 -> west. Clockwise.
    protected boolean moving = false;   // Mobs usually have moving animations

    public void move(int xa, int ya){
        // Any mob: xa -> where moves on x axis, ya -> where move on y axis.
        // Have 3 states: Not moving, up/down, left/right (individually)

        // 0 -> north. 1 -> east, 2 -> south, 3 -> west. Clockwise.
        if (xa > 0) dir = 1;
        if (xa < 0) dir = 3;
        if (ya > 0) dir = 2;
        if (ya < 0) dir = 0;

        // If no collision -> can move.
        if(!collision()) {
            x += xa;
            y += ya;
        }
    }

    public void update(){

    }

    private boolean collision(){

        return false;
    }

    public void render(){

    }
}

// TODO: Add lightning support.
