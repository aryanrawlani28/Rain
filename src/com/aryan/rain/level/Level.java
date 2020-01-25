package com.aryan.rain.level;

// L1: Random gen (Noise mapping - advanced)
// L2: Map level

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.level.tile.Tile;

// Manages which tiles need to be rendered.
public class Level {

    protected int width, height;                    // Primarily for L1
    protected int[] tiles;                          // Eg tiles[1] is grass, 2 is stone, etc..

    public Level(int width, int height){
        // L1
        this.width = width;
        this.height = height;

        tiles = new int[width * height];            // Tile for every square

        generateLevel();
    }

    public Level(String path){
        // L2
        loadLevel(path);
    }

    protected void generateLevel() {

    }

    private void loadLevel(String path){
          
    }


    public void update(){
        // AI and stuff goes in update. 60 updates per sec
    }

    private void time(){
        // change level and stuff based on time.
    }

    //this method and vars define the render region
    public void render(int xScroll, int yScroll, Screen screen){

        // as fast as possible. probably empty here.
        // Corner pins: rendering is done from top left to top right

        int x0 = xScroll >> 4;                          // x0 is the inmost x coord.. left side of the screen.
        int x1 = (xScroll + screen.width) >> 4;         // x1 is the right side of the screen
        int y0 = yScroll >> 4;                          // y0 is the top
        int y1 = yScroll + screen.height >> 4;          // y1 is bottom

    }

    // We will render what this method returns
    public Tile getTile(int x, int y){

        if (tiles[x+y*width] == 0) return Tile.grass;

        return Tile.voidTile;
    }
}
