package com.aryan.rain.entity.mob;

import com.aryan.rain.entity.Entity;
import com.aryan.rain.graphics.Sprite;
import com.aryan.rain.input.Mouse;

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

    protected void shoot(int x, int y, double dir){
        dir = Math.toDegrees(dir);
    }

    // A better way -> Check all 4 corners.
    private boolean collision(int xa, int ya){
        // boolean solid = false;

        for(int c = 0; c < 4; c++){
            int xt = ((x+xa) + c % 2 * 14 - 8) /16; // 10 is the width of collision area
            int yt = ((y+ya) + c / 2 * 12 + 3) /16;
            if (level.getTile(xt, yt).solid() == true) return true;
        }

        return false;
    }

    public void render(){

    }
}

// TODO: Add lightning support.
