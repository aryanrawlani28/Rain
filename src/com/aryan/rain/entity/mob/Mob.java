package com.aryan.rain.entity.mob;

import com.aryan.rain.entity.Entity;
import com.aryan.rain.graphics.Sprite;

public class Mob extends Entity {
    protected Sprite sprite;
    protected int dir = 0;              // 0 -> north. 1 -> east, 2 -> south, 3 -> west. Clockwise.
    protected boolean moving = false;   // Mobs usually have moving animations

    public void move(int xa, int ya){

        // Sliding. If moving along 2 axis at once, do that separately.
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }
        // Any mob: xa -> where moves on x axis, ya -> where move on y axis.
        // Have 3 states: Not moving, up/down, left/right (individually)

        // 0 -> north. 1 -> east, 2 -> south, 3 -> west. Clockwise.
        if (xa > 0) dir = 1;
        if (xa < 0) dir = 3;
        if (ya > 0) dir = 2;
        if (ya < 0) dir = 0;

        // If no collision -> can move.
        if(!collision(xa, ya)) {
            x += xa;    // Tile that you are going to be in.
            y += ya;
        }
    }

    public void update(){

    }

    private boolean collision(int xa, int ya){
        boolean solid = false;

        if (level.getTile((x+xa)/16, (y+ya)/16).solid()){
            return true;
        }
        return false;
    }

    public void render(){

    }
}

// TODO: Add lightning support.
