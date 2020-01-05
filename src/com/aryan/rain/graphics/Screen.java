package com.aryan.rain.graphics;

import java.util.Random;

public class Screen {
    private int width;
    private int height;

    public int[] pixels;

    public int[] tiles;
    public Random random = new Random();

    public Screen(int width, int height){
        this.width = width;
        this.height = height;

        pixels = new int[width*height]; // 50,400 Size [0, 50399]
        tiles = new int[64*64];
        for(int i=0; i<tiles.length; i++){
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public void clear(){
        for(int i=0; i<pixels.length; i++){
            pixels[i] = 0;
        }
    }

    public void render(){
        int tileIndex;
        for(int y=0; y < height; y++){
            if(y < 0 || y > height) break;
            for(int x=0; x < width; x++){
                if(x < 0 || x > width) break;
                tileIndex = (x >> 4) + (y >> 4) * 64;
                pixels[x + y * width] = tiles[tileIndex];
            }
        }
    }
}
