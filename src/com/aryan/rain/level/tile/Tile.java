package com.aryan.rain.level.tile;

// All tiles have own rendering. many tiles - stones, grass, etc.

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;

// Will be a template aka abstract in future maybe.
public class Tile {
    // even if we have a null sprite -> something should be rendered.
    // collideable?

    public int x, y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass); // Polymorphism.
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile rock = new RockTile(Sprite.rock);


    public static Tile voidTile = new VoidTile(Sprite.voidTile);

    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){

    }

    public boolean solid(){

        return false; // Not solid -> can pass through.
    }


}
