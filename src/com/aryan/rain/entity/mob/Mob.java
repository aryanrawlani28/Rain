package com.aryan.rain.entity.mob;

import com.aryan.rain.entity.Entity;
import com.aryan.rain.entity.projectile.Projectile;
import com.aryan.rain.entity.projectile.WizardProjectile;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;

public abstract class Mob extends Entity {
    // protected Sprite sprite;
    protected boolean walking = false;   // Mobs usually have moving animations

    protected enum Direction{
        UP, DOWN, LEFT, RIGHT
    }

    protected Direction dir;

    public void move(double xa, double ya){

        // Sliding. If moving along 2 axis at once, do that separately.
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }
        // Any mob: xa -> where moves on x axis, ya -> where move on y axis.
        // Have 3 states: Not moving, up/down, left/right (individually)

        // 0 -> north. 1 -> east, 2 -> south, 3 -> west. Clockwise.
        if (xa > 0) dir = Direction.RIGHT;
        if (xa < 0) dir = Direction.LEFT;
        if (ya > 0) dir = Direction.UP;
        if (ya < 0) dir = Direction.DOWN;


        for (int x = 0; x < Math.abs(xa); x++){
            if(!collision(abs(xa), abs(ya))) {
                this.x += abs(xa);
            }
        }

        for (int y = 0; y < Math.abs(ya); y++){
            if(!collision(abs(xa), abs(ya))) {
                this.y += abs(ya);
            }
        }
    }

    private int abs(double val){
        if (val < 0) return -1;
        return 1;
    }

    public abstract void update();

    public abstract void render(Screen screen);

    protected void shoot(int x, int y, double dir){
        Projectile p = new WizardProjectile(x, y, dir);
        level.add(p);
    }

    // A better way -> Check all 4 corners.
    private boolean collision(double xa, double ya){
        // boolean solid = false;

        for(int c = 0; c < 4; c++){
            double xt = ((x+xa) + c % 2 * 16) / 16;     // 10 is the width of collision area
            double yt = ((y+ya) + c / 2 * 16) / 16;

            int ix = (int) Math.ceil(xt);
            int iy = (int) Math.ceil(yt);

            if (c % 2 == 0) ix = (int) Math.floor(xt);
            if (c / 2 == 0) iy = (int) Math.floor(yt);

            if (level.getTile(ix, iy).solid()) return true;

            // if (level.getTile(xt, yt).solid() == true) return true;
        }

        return false;
    }
}
