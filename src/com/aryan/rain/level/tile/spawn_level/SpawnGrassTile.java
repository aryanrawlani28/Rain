package com.aryan.rain.level.tile.spawn_level;

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;
import com.aryan.rain.level.tile.Tile;

public class SpawnGrassTile extends Tile {
    public SpawnGrassTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen){
        // x,y are in pixels. need to apply math ops to adjust, shift to tile precision.
        screen.renderTile(x << 4, y << 4, this);
    }
}
