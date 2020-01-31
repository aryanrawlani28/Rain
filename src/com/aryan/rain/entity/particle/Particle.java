package com.aryan.rain.entity.particle;

import com.aryan.rain.entity.Entity;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

// We usually pop out 100s of particles at once. Not 1 by 1..

public class Particle extends Entity {

    private static List<Particle> particles = new ArrayList<Particle>();
    private Sprite sprite;

    private int life;
    private int time = 0;

    protected double xx, yy, zz;
    protected double xa, ya, za;

    // For one particle
    public Particle(int x, int y, int life){
        this.x = x;
        this.y = y;
        this.xx = x;
        this.yy = y;

        this.life = life + (random.nextInt(20) - 10);

        sprite = Sprite.particle_normal;

        this.xa = random.nextGaussian() + 1.45; // Gives a random no between -1 and 1 (more likely to be around 0)

        if (this.xa < 0) xa = 0.1;

        this.ya = random.nextGaussian();

        this.zz = random.nextFloat() + 2.0;
    }

    public void update(){
        // Animation code

        time++;
        // if (time >= Integer.MAX_VALUE - 1) time = 0;  -> If you want to be safe.

        if (time >= 7400) time = 0;
        if (time > life) remove();

        za -= 0.1;  // default val of za is 0. as soon as start, becomes neg.

        if (this.zz < 0){
            zz = 0;
            za *= -0.545;       // might differ from sprite to sprite. metals will bounce less for eg.

            xa *= 0.2;
            ya *= 0.4;
        }

        this.xx += xa;
        this.yy += ya;

        this.zz += za;
    }

    public void render(Screen screen){
        // Renders particles
        screen.renderSprite((int)xx, (int)yy - (int)zz, sprite, true);
    }

}
