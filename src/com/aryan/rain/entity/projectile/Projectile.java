package com.aryan.rain.entity.projectile;

import com.aryan.rain.entity.Entity;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;

import java.util.Random;

public abstract class Projectile extends Entity {

    protected double x, y;

    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double nx, ny;
    protected double speed, rateOfFire, range, damage;

    protected double distance;  // Distance from origin

    protected final Random random = new Random();

    public Projectile(int x, int y, double dir){
        xOrigin = x;
        yOrigin = y;
        angle = dir;

        this.x = x;
        this.y = y;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public int getSpriteSize(){
        return sprite.SIZE;
    }

    protected void move(){

    }
}
