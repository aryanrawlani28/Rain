package com.aryan.rain.entity.projectile;

import com.aryan.rain.entity.Entity;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;

public abstract class Projectile extends Entity {

    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double nx, ny;
    protected double speed, rateOfFire, range, damage;

    public Projectile(int x, int y, double dir){
        xOrigin = x;
        yOrigin = y;
        angle = dir;

        this.x = x;
        this.y = y;
    }

    protected void move(){

    }
}
