package com.aryan.rain.level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpawnLevel extends Level{


    //private int[] tiles;

    public SpawnLevel(String path) {
        super(path);
    }

    // Imp.
    protected void loadLevel(String path){
        try {
            // SpawnLevel.class.getResource(path) -> defunct
            BufferedImage image = ImageIO.read(new File(path));
            int w = image.getWidth();
            int h = image.getHeight();

            tiles = new int[w * h];
            image.getRGB(0,0,w,h, tiles,0,w);
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

        // 0xFF00 == 0x00FF00 (Like 256 == 0256)

        // My Green for grass: [0xFF007F0E]           Infield small grass: [0xFF00FF21]
//        for (int i = 0; i < levelPixels.length; i++) {
//            if (levelPixels[i] == 0xFF007F0E) tiles[i] = Tile.grass;        // The extra ff is for the alpha channel because of the way bufferedimage works.
//            if (levelPixels[i] == 0xFFFFFF00) tiles[i] = Tile.flower;
//            if (levelPixels[i] == 0xFF7F7F00) tiles [i] = Tile.rock;
//        }


    }

}


