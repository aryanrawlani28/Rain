package com.aryan.rain.level;

import com.aryan.rain.level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level{

    private Tile[] tiles;   // Slow, but precise.
    private int[] levelPixels;

    public SpawnLevel(String path) {
        super(path);
    }

    // Imp.
    protected void loadLevel(String path){
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0,0,w,h,levelPixels,0,w);
        }catch (IOException e){
            System.out.println("Rain couldn't load level file.");
            e.printStackTrace();
        }
    }

    // Convert levelpixels[] to tiles.
    protected void generateLevel(){


    }

}


