package com.aryan.rain.level.tile;

// All tiles have own rendering. many tiles - stones, grass, etc.

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;

public class Tile {
    // even if we have a null sprite -> something should be rendered.
    // collideable?

    public int x, y;
    public Sprite sprite;

    Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){

    }

    public boolean solid(){

        return false; // Not solid -> can pass through.
    }


}
