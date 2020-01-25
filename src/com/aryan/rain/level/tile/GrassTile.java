package com.aryan.rain.level.tile;

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;

public class GrassTile extends Tile {
    GrassTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen){
        // x,y are in pixels. need to apply math ops to adjust, shift to tile precision.
        screen.renderTile(x << 4, y << 4, this);
    }


    // Grass is not solid. by default, all are are not solid.


}
