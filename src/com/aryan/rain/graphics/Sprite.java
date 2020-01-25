package com.aryan.rain.graphics;

public class Sprite {

    public final int SIZE;     // Size of particular sprite

    // X, y are coords of individual sprites.
    private  int x,y;
    public int[] pixels;

    //Multiple spritesheets, multiple sprites. Which sprite belong to which sheet?
    private SpriteSheet sheet;


    // Sprites are static. Once defined, they stay the same. Sprite.grass would give us all we need.
    public static Sprite grass = new Sprite(16,0,0, SpriteSheet.tiles);
    public static Sprite voidTile = new Sprite(16, 0x339BF5);       // 0 is black. 0xffffff is white.


    public static Sprite player_back = new Sprite(32, 0, 5, SpriteSheet.tiles);
    public static Sprite player_forward = new Sprite(32, 2, 5, SpriteSheet.tiles);
    public static Sprite player_side = new Sprite(32, 1, 5, SpriteSheet.tiles);

    public static Sprite player_forward_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
    public static Sprite player_forward_2 = new Sprite(32, 0, 7, SpriteSheet.tiles);

    public static Sprite player_side_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
    public static Sprite player_side_2 = new Sprite(32, 1, 7, SpriteSheet.tiles);

    public static Sprite player_back_1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
    public static Sprite player_back_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);


    // Even tho we have 16px sprites, monsters, etc can be big. So they can be bigger.
    public Sprite(int size, int x, int y, SpriteSheet sheet){
        this.SIZE = size;

        pixels = new int[SIZE * SIZE];      // Creates a pixel array, size of sprite. Mostly 16.

        this.x = x * size;                  // Coords are x,y but sprites themselves will be of 16px.
        this.y = y * size;

        this.sheet = sheet;
        load();

    }

    public Sprite(int size, int color){
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    // Fill pixel array with some color
    private void setColor(int color) {
        for (int i = 0; i < SIZE * SIZE; i++){
            pixels[i] = color;
        }
    }

    private void load(){
        for(int y=0; y<SIZE; y++){
            for(int x=0; x < SIZE; x++){
                // Basically extract a single sprite out of the spritesheet. (Spritesheet has multiple sprites)
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }


}
