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

    public int[] pixels;

    // Since we've now included res as a library - java automatically includes it.
    // Just use the folders/files inside it for path. Size is 256x256.
    public static SpriteSheet tiles = new SpriteSheet("res/textures/sheets/spritesheet.png", 256);
    public static SpriteSheet spawn_level = new SpriteSheet("res/textures/sheets/spawn_level.png", 48);

    public SpriteSheet(String path, int size){
        this.path = path;
        this.SIZE = size;
        pixels = new int[SIZE*SIZE];
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
