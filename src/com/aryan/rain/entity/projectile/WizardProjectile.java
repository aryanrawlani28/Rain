package com.aryan.rain.entity.projectile;

import com.aryan.rain.entity.spawner.ParticleSpawner;
import com.aryan.rain.entity.spawner.Spawner;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;

public class WizardProjectile extends Projectile{

    public static final int FIRE_RATE = 10;     // Higher the RoF, slower. Time between projectiles.

    public WizardProjectile(int x, int y, double dir) {
        super(x, y, dir);
        range = 200;
        speed = 4;
        damage = 20;

        sprite = Sprite.projectile_wizard;

        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    public void update(){
        if (level.tileCollision(x, y, nx, ny, 7)){
//            Particle p = new Particle((int)x, (int)y, 50);
//            level.add(p);
            level.add(new ParticleSpawner((int)x, (int)y, 30, 20, level));
            remove();
        }else {
            move();
        }
    }



    protected void move(){
        x += nx;
        y += ny;

        if (distance() > this.range) {
            remove();
        }
    }

    private double distance() {
        double dist = 0;

        double xDist = (xOrigin - x);
        double yDist = (yOrigin - y);

        dist = Math.sqrt((xDist * xDist) + (yDist * yDist));

        // dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));

        return dist;
    }

    public void render(Screen screen){
        screen.renderProjectile((int)x - 12, (int)y - 2, this);    // When render, we need int.
    }
}
