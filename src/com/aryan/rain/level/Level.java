package com.aryan.rain.level;

// L1: Random gen (Noise mapping - advanced)
// L2: Map level

import com.aryan.rain.graphics.Screen;

public class Level {

    protected int width, height; // Primarily for L1
    protected int[] tiles;  // Eg tiles[1] is grass, 2 is stone, etc..

    public Level(int width, int height){
        // L1
        this.width = width;
        this.height = height;

        tiles = new int[width * height]; // Tile for every square

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


    public void render(int xScroll, int yScroll, Screen screen){
        // as fast as possible. probably empty here
    }
}
