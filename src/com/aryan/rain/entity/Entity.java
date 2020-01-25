package com.aryan.rain.entity;

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.level.Level;

import java.util.Random;


// A Entity doesn't necessarily have sprites.
public abstract class Entity {
    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();   // AI and stuff.

    public void update(){

    }

    public void render(Screen screen){

    }

    // Remove entity from level
    public void remove(){
        removed = true;
    }

    public boolean isRemoved(){
        return removed;
    }
}