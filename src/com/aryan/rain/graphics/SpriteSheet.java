package com.aryan.rain.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// This loads entire sheet, but what about individual sprites? I create another class for this.

public class SpriteSheet {
    // Specify path:

    // Break down as much as possible

    private String path; //Path to spritesheet

    public final int SIZE;
    public final int SPRITE_WIDTH, SPRITE_HEIGHT;     // Size of sprite

    private int width, height;

    public int[] pixels;

    // Since we've now included res as a library - java automatically includes it.
    // Just use the folders/files inside it for path. Size is 256x256.
    public static SpriteSheet tiles = new SpriteSheet("res/textures/sheets/spritesheet.png", 256);
    public static SpriteSheet spawn_level = new SpriteSheet("res/textures/sheets/spawn_lvl.png", 48);
    public static SpriteSheet projectile_wizard = new SpriteSheet("res/textures/sheets/projectiles/wizard.png", 48);

    public static SpriteSheet player = new SpriteSheet("res/textures/sheets/player_sheet.png", 128, 96);
    public static SpriteSheet player_down = new SpriteSheet(player, 0, 0, 1, 3, 32);
    public static SpriteSheet player_up = new SpriteSheet(player, 1, 0, 1, 3, 32);
    public static SpriteSheet player_left = new SpriteSheet(player, 2, 0, 1, 3, 32);
    public static SpriteSheet player_right = new SpriteSheet(player, 3, 0, 1, 3, 32);

    public static SpriteSheet dummy = new SpriteSheet("res/textures/sheets/king_cherno.png", 128, 96);
    public static SpriteSheet dummy_down = new SpriteSheet(dummy, 0, 0, 1, 3, 32);
    public static SpriteSheet dummy_up = new SpriteSheet(dummy, 1, 0, 1, 3, 32);
    public static SpriteSheet dummy_left = new SpriteSheet(dummy, 2, 0, 1, 3, 32);
    public static SpriteSheet dummy_right = new SpriteSheet(dummy, 3, 0, 1, 3, 32);

    private Sprite sprites[];

    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {

        // Args provided width and height are sprite-precision values.

        // This is a sub-sheet, we specify what section to crop out.

        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;     // Pixel-precision
        int h = height * spriteSize;    // Pixel-precision

        if (width == height) SIZE = width;
        else SIZE = -1;

        this.SPRITE_WIDTH = w;
        this.SPRITE_HEIGHT = h;

        pixels = new int[w*h];

        for (int y0 = 0; y0 < h; y0++){
            int yp = yy + y0;
            for (int x0 = 0; x0 < w; x0++){
                int xp = xx + x0;
                pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
            }
        }

        int frame = 0;

        sprites = new Sprite[width * height];

        for (int ya = 0; ya < height; ya++){
            for (int xa = 0; xa < width; xa++){

                int[] spritePixels = new int[spriteSize * spriteSize];

                // Below two iterate through our sprites.
                // Basically move through entire spritesheet and take out particular sections.
                // Take out one area.
                for (int y0 = 0; y0 < spriteSize; y0++){
                    for (int x0 = 0; x0 < spriteSize; x0++){
                        // We're advancing horizontally first, row by row
                        spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * this.SPRITE_WIDTH];   // Remember to offset by the actual position in the sheet
                    }
                }
                Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
                sprites[frame++] = sprite;

            }
        }


    }

    public SpriteSheet(String path, int size){
        this.path = path;
        this.SIZE = size;
        this.SPRITE_HEIGHT = size;
        this.SPRITE_WIDTH = size;

        pixels = new int[SIZE*SIZE];
        load(path);
    }

    public SpriteSheet(String path, int width, int height){
        this.path = path;

        this.SIZE = -1;
        this.SPRITE_WIDTH = width;
        this.SPRITE_HEIGHT = height;

        pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
        load(path);
    }

    public Sprite[] getSprite(){
        return sprites;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

    // Load the image file
    private void load(String path){
        try {
            System.out.print("Trying to load: " + path + "...");
            BufferedImage image = ImageIO.read(new File(path));
            System.out.println(" succeeded!");



            //we wanna deal with images in pixels
            int width = image.getWidth();
            int height = image.getHeight();
            System.out.println("Width: " + width + "Height: " + height);

            // Convert image to pixels. Now I can use these pixels individually
            image.getRGB(0, 0, width, height, pixels, 0, width);

        }catch (IOException e){
            e.printStackTrace();
            System.err.println("Failed!!");
        }
    }
}
