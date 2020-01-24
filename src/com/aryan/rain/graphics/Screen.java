package com.aryan.rain.graphics;

import com.aryan.rain.level.tile.Tile;

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
        // Height: 300px & Width: 168px.

        int yp, xp;

        for(int y=0; y < height; y++){
            yp = y + yOffset;
            if(yp < 0 || yp >= height) continue;

            for(int x=0; x < width; x++){

                xp = x + xOffset;
                if(xp < 0 || xp >= width) continue;
                // At the moment, it doesn't really work as intended.
                pixels[xp + yp * width] = Sprite.grass.pixels[(x&15) + (y&15) * Sprite.grass.SIZE];

            }
        }
    }


    public void renderTile(int xp, int yp, Tile tile){
        for (int y = 0; y < tile.sprite.SIZE; y++){ //mostly tile size is 16. if increase size in future, no probs this way.
            int ya = y + yp;
        }
    }


}
