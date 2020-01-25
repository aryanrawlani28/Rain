package com.aryan.rain.graphics;

import com.aryan.rain.level.tile.Tile;

import java.util.Random;

public class Screen {
    public int width;
    public int height;

    public int[] pixels;

    private final int MAP_SIZE = 8;
    private final int MAP_SIZE_MASK = MAP_SIZE - 1;

    public int xOffset, yOffset;

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

//    public void render(int xOffset, int yOffset){
//        // Height: 300px & Width: 168px.
//
//        int yp, xp;
//
//        for(int y=0; y < height; y++){
//            yp = y + yOffset;
//            if(yp < 0 || yp >= height) continue;
//
//            for(int x=0; x < width; x++){
//
//                xp = x + xOffset;
//                if(xp < 0 || xp >= width) continue;
//                // At the moment, it doesn't really work as intended.
//                pixels[xp + yp * width] = Sprite.grass.pixels[(x&15) + (y&15) * Sprite.grass.SIZE];
//
//            }
//        }
//    }

    // Make sure to render only what we need. Also, when we move right -> player moves right but map should move to left.
    public void renderTile(int xp, int yp, Tile tile){
        xp -= xOffset;
        yp -= yOffset;

        for (int y = 0; y < tile.sprite.SIZE; y++){                     //mostly tile size is 16. if increase size in future, no probs this way.

            int ya = y + yp;

            for (int x = 0; x < tile.sprite.SIZE; x++){                 // same thing for x.

                int xa = x + xp;                                        // xa = x absolute
                if (xa < 0 || xa >= width || ya < 0 || ya >= width){    // maps are going to be infinite? (Come back to this later #29) are easier?
                    break;
                }

                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];

            }
        }
    }

    public void setOffset(int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
