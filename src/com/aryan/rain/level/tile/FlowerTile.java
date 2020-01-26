package com.aryan.rain.level.tile;

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;

public class FlowerTile extends Tile {
    FlowerTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen){
        // x,y are in pixels. need to apply math ops to adjust, shift to tile precision.
        screen.renderTile(x << 4, y << 4, this);
    }
}
