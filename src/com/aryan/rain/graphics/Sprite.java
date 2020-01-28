package com.aryan.rain.graphics;

public class Sprite {

    public final int SIZE;     // Size of particular sprite

    // X, y are coords of individual sprites.
    private  int x,y;
    public int[] pixels;

    private int width, height;

    // Multiple spritesheets, multiple sprites. Which sprite belong to which sheet?
    private SpriteSheet sheet;


// ---------------------------------------------------------------------------------------------------------- //

    ////////////////////////////// GLOBAL SPRITES /////////////////////////////////////

    // Sprites are static. Once defined, they stay the same. Sprite.grass would give us all we need.
    public static Sprite grass = new Sprite(16,0,0, SpriteSheet.tiles);
    public static Sprite flower = new Sprite(16,1,0, SpriteSheet.tiles);
    public static Sprite rock = new Sprite(16,2,0, SpriteSheet.tiles);
    public static Sprite voidTile = new Sprite(16, 0x339BF5);               // 0 is black. 0xffffff is white.

    //////////////////////////////////////////////////////////////////////////////////////////


// ---------------------------------------------------------------------------------------------------------- //


    ////////////////////////////// SPAWN LEVEL SPRITES /////////////////////////////////////

    public static Sprite spawn_grass = new Sprite(16,0,0,SpriteSheet.spawn_level);
    public static Sprite spawn_hedge = new Sprite(16,1,0,SpriteSheet.spawn_level);
    public static Sprite spawn_water = new Sprite(16,2,0,SpriteSheet.spawn_level);
    public static Sprite spawn_wall1 = new Sprite(16,0,1,SpriteSheet.spawn_level);
    public static Sprite spawn_wall2 = new Sprite(16,0,2,SpriteSheet.spawn_level);
    public static Sprite spawn_floor = new Sprite(16,1,1,SpriteSheet.spawn_level);

    //////////////////////////////////////////////////////////////////////////////////////////


// ---------------------------------------------------------------------------------------------------------- //


    ////////////////////////////// PLAYER SPRITES /////////////////////////////////////

    public static Sprite player_back = new Sprite(32, 0, 5, SpriteSheet.tiles);
    public static Sprite player_forward = new Sprite(32, 2, 5, SpriteSheet.tiles);
    public static Sprite player_side = new Sprite(32, 1, 5, SpriteSheet.tiles);

    public static Sprite player_forward_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
    public static Sprite player_forward_2 = new Sprite(32, 0, 7, SpriteSheet.tiles);

    public static Sprite player_side_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
    public static Sprite player_side_2 = new Sprite(32, 1, 7, SpriteSheet.tiles);

    public static Sprite player_back_1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
    public static Sprite player_back_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);


    //////////////////////////////////////////////////////////////////////////////////////////


// ---------------------------------------------------------------------------------------------------------- //


    ////////////////////////////// PROJECTILE SPRITES /////////////////////////////////////

    public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);

    //////////////////////////////////////////////////////////////////////////////////////////


// ---------------------------------------------------------------------------------------------------------- //

    ////////////////////////////// PARTICLE SPRITES /////////////////////////////////////

    public static Sprite particle_normal = new Sprite(3, 0xAAAAAA);

    //////////////////////////////////////////////////////////////////////////////////////////


// ---------------------------------------------------------------------------------------------------------- //

    // Even tho we have 16px sprites, monsters, etc can be big. So they can be bigger.
    public Sprite(int size, int x, int y, SpriteSheet sheet){
        this.SIZE = size;

        this.height = size;
        this.width = size;

        pixels = new int[SIZE * SIZE];      // Creates a pixel array, size of sprite. Mostly 16.

        this.x = x * size;                  // Coords are x,y but sprites themselves will be of 16px.
        this.y = y * size;

        this.sheet = sheet;
        load();

    }

    public Sprite(int width, int height, int color){
        this.height = height;
        this.width = width;
        pixels = new int[height*width];
        SIZE = -1;
        setColor(color);
    }

    public Sprite(int size, int color){
        this.height = size;
        this.width = size;

        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    // Fill pixel array with some color
    private void setColor(int color) {
        for (int i = 0; i < width * height; i++){
            pixels[i] = color;
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
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
