package com.aryan.rain.entity;

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;
import com.aryan.rain.level.Level;

import java.util.Random;


// A Entity doesn't necessarily have sprites.
public class Entity {
    protected double x, y;
    protected Sprite sprite;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();   // AI and stuff.

    public Entity(){}

    public Entity(int x, int y, Sprite sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void update(){

    }

    public void render(Screen screen){
        if (sprite != null) screen.renderSprite((int)x, (int)y, sprite, true);
    }

    // Remove entity from level
    public void remove(){
        removed = true;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public boolean isRemoved(){
        return removed;
    }

    public void init(Level level){
        this.level = level;
    }
}