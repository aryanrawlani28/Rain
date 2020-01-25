package com.aryan.rain.level.tile;

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;

public class VoidTile extends Tile {
    VoidTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen){
        // x,y are in pixels. need to apply math ops to adjust
        screen.renderTile(x, y, this);
    }
}
