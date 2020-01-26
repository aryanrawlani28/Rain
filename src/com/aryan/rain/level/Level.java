package com.aryan.rain.level;

// L1: Random gen (Noise mapping - advanced)
// L2: Map level

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.level.tile.Tile;

// Manages which tiles need to be rendered.
public class Level {

    protected int width, height;                    // Primarily for L1
    protected int[] tilesInt;                       // Eg tiles[1] is grass, 2 is stone, etc..

    protected int[] tiles;  // Contains all of levels tiles. Since one level loaded at a time, this works well.

    public static Level Spawn = new SpawnLevel("res/levels/spawn_level.png");


    // Used when we generate a random level
    public Level(int width, int height){
        // L1
        this.width = width;
        this.height = height;

        tilesInt = new int[width * height];            // Tile for every square

        generateLevel();
    }

    public Level(String path){
        // L2
        loadLevel(path);
        generateLevel();
    }

    protected void generateLevel() {

    }

    protected void loadLevel(String path){
          
    }


    public void update(){
        // AI and stuff goes in update. 60 updates per sec
    }

    private void time(){
        // change level and stuff based on time.
    }

    //this method and vars define the render region
    public void render(int xScroll, int yScroll, Screen screen){
        screen.setOffset(xScroll, yScroll);

        // as fast as possible. probably empty here.
        // Corner pins: rendering is done from top left to top right

        int x0 = xScroll >> 4;                          // x0 is the inmost x coord.. left side of the screen.
        int x1 = (xScroll + screen.width + 16) >> 4;    // x1 is the right side of the screen (16 is being added for aesthetic purpose and prevent black!)
        int y0 = yScroll >> 4;                          // y0 is the top
        int y1 = (yScroll + screen.height + 16) >> 4;   // y1 is bottom

        for (int y = y0; y < y1; y++){
            for (int x = x0; x < x1; x++){
                getTile(x, y).render(x, y, screen);
//                if (x+y*16 < 0 || x + y * 16 >= 256){
//                    Tile.voidTile.render(x, y, screen);
//                    continue;
//                }
//                tiles[x+y*16].render(x, y, screen);
            }
        }
    }

    // We will render what this method returns. This converts arry to tiles.
    public Tile getTile(int x, int y){

        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.spawn_wall1;

        // Green -> Grass [0xFF00]
        // Yellow -> Flower [0xFFFFFF00]
        // Brown -> Rock [0xFFFFFF00]

        // 0xFF00 == 0x00FF00 (Like 256 == 0256)

        // My Green for grass: [0xFF007F0E]           Infield small grass: [0xFF00FF21]

        if (tiles[x+y*width] == Tile.col_spawn_floor) return Tile.spawn_floor;
        if (tiles[x+y*width] == Tile.col_spawn_grass) return Tile.spawn_grass;
        //if (tiles[x+y*width] == Tile.col_spawn_hedge) return Tile.spawn_hedge;
        if (tiles[x+y*width] == Tile.col_spawn_wall1) return Tile.spawn_wall1;
        if (tiles[x+y*width] == Tile.col_spawn_wall2) return Tile.spawn_wall2;
        //if (tiles[x+y*width] == Tile.col_spawn_water) return Tile.spawn_water;

        return Tile.spawn_wall1;
    }
}
