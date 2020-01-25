package com.aryan.rain.entity.mob;

import com.aryan.rain.entity.Entity;
import com.aryan.rain.graphics.Sprite;

public class Mob extends Entity {
    protected Sprite sprite;
    protected int dir = 0;      // 0 -> north. 1 -> east, 2 -> south, 3 -> west.
    protected boolean moving = false;   // Mobs usually have moving animations

    public void move(){

    }

    public void update(){

    }

    private boolean collision(){

        return false;
    }

}

// TODO: Add lightning support.
