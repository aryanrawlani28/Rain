package com.aryan.rain.level;

import com.aryan.rain.level.tile.GrassTile;
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
            tiles = new Tile[w * h];
            image.getRGB(0,0,w,h,levelPixels,0,w);
        }catch (IOException e){
            System.out.println("Rain couldn't load level file.");
            e.printStackTrace();
        }
    }

    // Convert levelpixels[] to tiles.
    protected void generateLevel(){ // void return because it will be faster.
        // There are as many pixels as there are tiles.

        // Green -> Grass [0xFF00]
        // Yellow -> Flower [0xFFFF00]
        // Brown -> Rock [0x7F7F00]
        for (int i = 0; i < levelPixels.length; i++) {
            if (levelPixels[i] == 0xFF00) tiles[i] = Tile.grass;
            if (levelPixels[i] == 0xFFFF00) tiles[i] = Tile.flower;
            if (levelPixels[i] == 0x7F7F00) tiles[i] = Tile.rock;
        }
    }

}


