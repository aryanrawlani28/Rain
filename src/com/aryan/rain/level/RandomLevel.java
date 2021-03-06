package com.aryan.rain.level;

import java.util.Random;

// Since we don't have a def constructor, we need one!

// we need to have a get tile method. ex if 2, grass rendered, etc.

public class RandomLevel extends Level{

    private static final Random random = new Random();

    public RandomLevel(int width, int height) {
        super(width, height);
    }

    protected void generateLevel(){
        // cycles through every index
        for (int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tilesInt[x+y*width] = random.nextInt(4);   // 0 through 3 values can be assigned
            }
        }
    }
}
