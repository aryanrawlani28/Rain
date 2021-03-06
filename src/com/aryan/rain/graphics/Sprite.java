package com.aryan.rain.graphics;

public class Sprite {

    public final int SIZE;     // Size of particular sprite

    // X, y are coords of individual sprites.
    private  int x,y;
    public int[] pixels;

    private int width, height;

    // Multiple spritesheets, multiple sprites. Which sprite belong to which sheet?
    protected SpriteSheet sheet;


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
    public static Sprite projectile_arrow = new Sprite(16, 1, 0, SpriteSheet.projectile_wizard);

    //////////////////////////////////////////////////////////////////////////////////////////


// ---------------------------------------------------------------------------------------------------------- //

    ////////////////////////////// PARTICLE SPRITES /////////////////////////////////////

    public static Sprite particle_normal = new Sprite(3, 0xAAAAAA);

    //////////////////////////////////////////////////////////////////////////////////////////


// ---------------------------------------------------------------------------------------------------------- //

    public static Sprite dummy = new Sprite(32, 0, 0, SpriteSheet.dummy_down);


    protected Sprite(SpriteSheet sheet, int width, int height){
        // We're not really gonna use this
        if (width == height) SIZE = width;
        else SIZE = -1;

        // SIZE = (width == height) ? width : -1;

        this.width = width;
        this.height = height;

        this.sheet = sheet;
    }

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

    public Sprite(int[] pixels, int width, int height) {

        if (width == height) SIZE = width;
        else SIZE = -1;

        this.width = width;
        this.height = height;

        this.pixels = new int[pixels.length];
        for (int i=0; i < pixels.length; i++){
            this.pixels[i] = pixels[i];
        }

    }

    public static Sprite rotate(Sprite sprite, double angle){
        return new Sprite(rotate(sprite.pixels, sprite.width, sprite.height, angle), sprite.width, sprite.height);
    }


    private static int[] rotate(int[] pixels, int width, int height, double angle){
        int[] result = new int[width*height];

        double nx_x = rot_x(-angle, 1.0, 0.0);
        double nx_y = rot_y(-angle, 1.0, 0.0);

        double ny_x = rot_x(-angle, 0.0, 1.0);
        double ny_y = rot_y(-angle, 0.0, 1.0);

        double x0 = rot_x(-angle, -width / 2.0, -height / 2.0) + width / 2.0;
        double y0 = rot_y(-angle, -width / 2.0, -height / 2.0) + height / 2.0;

        // Move
        for (int y = 0; y < height; y++){
            double x1 = x0;
            double y1 = y0;
            for (int x = 0; x < width; x++){
                int xx = (int) x1;
                int yy = (int) y1;

                int col = 0;
                if (xx < 0 || xx >= width || yy < 0 || yy >= height){
                    col = 0xFFFF00DC;
                }else{
                    col = pixels[xx + yy * width];
                }

                result[x + y * width] = col;
                x1 += nx_x;
                y1 += nx_y;
            }
            x0 += ny_x;
            y0 += ny_y;
        }

        return result;
    }

    // Figure out where is the new x
    private static double rot_x(double angle, double x, double y){
        double res = 0;

        double cos = Math.cos(angle - Math.PI / 2);
        double sin = Math.sin(angle - Math.PI / 2);

        res = (x * cos) + (y * -sin);

        return res;
    }

    private static double rot_y(double angle, double x, double y){
        double res = 0;

        double cos = Math.cos(angle - Math.PI / 2);
        double sin = Math.sin(angle - Math.PI / 2);

        res = (x * sin) + (y * cos);

        return res;
    }


    public static Sprite[] split(SpriteSheet sheet) {

        int amount = ((sheet.getWidth() * sheet.getHeight()) / (sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT));
        Sprite[] sprites = new Sprite[amount];
        int current = 0;

        int[] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT];

        for (int yp = 0; yp < sheet.getWidth() / sheet.SPRITE_HEIGHT; yp++){
            // go upto 5
            for (int xp = 0; xp < sheet.getWidth() / sheet.SPRITE_WIDTH; xp++){
                // go upto 13.
                // above 2 loops identify where we are -> tile precision. eg A is at 0,0

                // below 2 loops take that tile of 16x16 and copy all to pixel-precision
                for (int y = 0; y<sheet.SPRITE_HEIGHT; y++){
                    for (int x = 0; x<sheet.SPRITE_WIDTH; x++){
                        int x0 = x + xp * sheet.SPRITE_WIDTH;
                        int y0 = y + yp * sheet.SPRITE_HEIGHT;
                        pixels[x+y * sheet.SPRITE_WIDTH] = sheet.getPixels()[(x0 + y0 * sheet.getWidth())];
                    }
                }

                sprites[current++] = new Sprite(pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT);
            }
        }

        return sprites;
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
        for(int y=0; y<height; y++){
            for(int x=0; x < width; x++){
                // Basically extract a single sprite out of the spritesheet. (Spritesheet has multiple sprites)
                pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SPRITE_WIDTH];
            }
        }
    }


}
