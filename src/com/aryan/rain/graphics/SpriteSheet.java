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
    public final int WIDTH, HEIGHT;

    public int[] pixels;

    // Since we've now included res as a library - java automatically includes it.
    // Just use the folders/files inside it for path. Size is 256x256.
    public static SpriteSheet tiles = new SpriteSheet("res/textures/sheets/spritesheet.png", 256);
    public static SpriteSheet spawn_level = new SpriteSheet("res/textures/sheets/spawn_level.png", 48);
    public static SpriteSheet projectile_wizard = new SpriteSheet("res/textures/sheets/projectiles/wizard.png", 48);

    public static SpriteSheet player = new SpriteSheet("res/textures/sheets/player_sheet.png", 128, 96);
    public static SpriteSheet player_down = new SpriteSheet(player, 0, 0, 1, 3, 32);

    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {

        // Args provided width and height are sprite-precision values.

        // This is a sub-sheet, we specify what section to crop out.

        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;     // Pixel-precision
        int h = height * spriteSize;    // Pixel-precision

        if (width == height) SIZE = width;
        else SIZE = -1;

        this.WIDTH = w;
        this.HEIGHT = h;

        pixels = new int[w*h];

        for (int y0 = 0; y0 < h; y0++){
            int yp = yy + y0;
            for (int x0 = 0; x0 < w; x0++){
                int xp = xx + x0;
                pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
            }
        }
    }

    public SpriteSheet(String path, int size){
        this.path = path;
        this.SIZE = size;
        this.HEIGHT = size;
        this.WIDTH = size;

        pixels = new int[SIZE*SIZE];
        load(path);
    }

    public SpriteSheet(String path, int width, int height){
        this.path = path;

        this.SIZE = -1;
        this.WIDTH = width;
        this.HEIGHT = height;

        pixels = new int[WIDTH*HEIGHT];
        load(path);
    }

    // Load the image file
    private void load(String path){
        try {
            BufferedImage image = ImageIO.read(new File(path));

            //we wanna deal with images in pixels
            int w = image.getWidth();
            int h = image.getHeight();

            // Convert image to pixels. Now I can use these pixels individually
            image.getRGB(0, 0, w, h, pixels, 0, w);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
