package com.aryan.rain.entity.mob;

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;

public class Dummy extends Mob{

    public Dummy(int x, int y){
        // X, Y are spawning locations of NPCs

        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.player_forward;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {
        screen.renderMob(x, y, sprite, 0);
    }
}
