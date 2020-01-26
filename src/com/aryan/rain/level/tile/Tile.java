package com.aryan.rain.level.tile;

// All tiles have own rendering. many tiles - stones, grass, etc.

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;
import com.aryan.rain.level.tile.spawn_level.*;

// Will be a template aka abstract in future maybe.
public class Tile {
    // even if we have a null sprite -> something should be rendered.
    // collideable?

    public int x, y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass); // Polymorphism.
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile rock = new RockTile(Sprite.rock);


    //////////////////////////// Spawn Tiles //////////////////////////////

    // Each has own class because can have diff way of behaving, eg: one is breakable, another is passable, etc.
    public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
    public static Tile spawn_hedge = new SpawnHedgeTile(Sprite.spawn_hedge);
    public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
    public static Tile spawn_wall1 = new SpawnWallTile(Sprite.spawn_wall1);
    public static Tile spawn_wall2 = new SpawnWallTile(Sprite.spawn_wall2); // If just for aesthetic purpose, can have same class.
    public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);

    // The following hash colors will be according to your spawn_level generated.

    public static final int col_spawn_grass = 0xFF4CFF00;
    public static final int col_spawn_hedge = 0;
    public static final int col_spawn_water = 0;
    public static final int col_spawn_wall1 = 0xFF404040;
    public static final int col_spawn_wall2 = 0xFF000000;
    public static final int col_spawn_floor = 0xFF7F3300;

    /////////////////////////////////////////////////////////////////////////////////////////

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
