package com.aryan.rain.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    // Specify path:

    // Break down as much as possible

    private String path; //Path to spritsheet

    private final int SIZE;

    public int[] pixels;

    public SpriteSheet(String path, int size){
        this.path = path;
        this.SIZE = size;
        pixels = new int[SIZE*SIZE];
    }

    // Load the image file

    private void load(){
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));

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
