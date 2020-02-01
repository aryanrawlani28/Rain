package com.aryan.rain.level;

import com.aryan.rain.entity.mob.Chaser;
import com.aryan.rain.entity.mob.Dummy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class SpawnLevel extends Level{

    private static Random random = new Random();

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
        for (int i=0; i < 5; i++) {
//            add(new Dummy(random.nextInt(2) + 20, random.nextInt(7) + 59));
            add(new Dummy(20, 59));
        }

        add(new Chaser(20, 59));
    }

    protected void generateLevel(){

    }

}


