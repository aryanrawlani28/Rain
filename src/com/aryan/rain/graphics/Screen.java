package com.aryan.rain.graphics;

import java.util.Random;

public class Screen {
    private int width;
    private int height;

    public int[] pixels;

    private final int MAP_SIZE = 8;
    private final int MAP_SIZE_MASK = MAP_SIZE - 1;

    public int[] tiles = new int[MAP_SIZE*MAP_SIZE];
    public Random random = new Random();

    public Screen(int width, int height){
        this.width = width;
        this.height = height;

        pixels = new int[width*height]; // 50,400 Size [0, 50399]
        for(int i=0; i<MAP_SIZE*MAP_SIZE; i++){
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public void clear(){
        for(int i=0; i<pixels.length; i++){
            pixels[i] = 0;
        }
    }

    public void render(int xOffset, int yOffset){
        int tileIndex;

        // Height: 300px & Width: 168px.
        for(int y=0; y < height; y++){

            //yy is the duplicate of y, we don't wanna change rendering area, we need to move the tile index
            int yy = y + yOffset;

            // when goes out of bounds, break.
            //if(yy < 0 || yy > height) break;


            for(int x=0; x < width; x++){
                int xx = x + xOffset;

                //if(xx < 0 || xx > width) break;

                // Everytime xx / 16, becomes bigger than 63, go to zero again.
                tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4) & MAP_SIZE_MASK) * MAP_SIZE;

                pixels[x + y * width] = tiles[tileIndex];
            }
        }
    }
}
