package com.aryan.rain.level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpawnLevel extends Level{

    public SpawnLevel(String path) {
        super(path);
    }

    // Imp.
    protected void loadLevel(String path){
        try {
            // SpawnLevel.class.getResource(path) -> defunct
            BufferedImage image = ImageIO.read(new File(path));
            int w = this.width = image.getWidth();
            int h = this.height = image.getHeight();

            tiles = new int[w * h];
            image.getRGB(0,0, w, h, tiles,0,w);
        }catch (IOException e){
            System.out.println("Rain couldn't load level file.");
            e.printStackTrace();
        }
    }

    protected void generateLevel(){

    }

}


