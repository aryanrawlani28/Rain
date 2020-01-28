package com.aryan.rain.entity.particle;

import com.aryan.rain.entity.Entity;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

// We usually pop out 100s of particles at once. Not 1 by 1..

public class Particle extends Entity {

    private List<Particle> particles = new ArrayList<Particle>();
    private Sprite sprite;

    private int life;

    protected double xx, yy, xa, ya;    // Amount of pixels moves on respective axis.

    // For one particle
    public Particle(int x, int y, int life){
        this.x = x;
        this.y = y;
        this.xx = x;
        this.yy = y;

        this.life = life;

        sprite = Sprite.particle_normal;

        this.xa = random.nextGaussian(); // Gives a random no between -1 and 1 (more likely to be around 0)
        this.ya = random.nextGaussian();
    }

    public Particle(int x, int y, int life, int amount){
        this(x, y, life);

        for (int i=0; i < amount-1; i++){
            particles.add(new Particle(x, y, life));
        }

        particles.add(this);
    }

    public void update(){
        // Animation code

        this.xx += xa;
        this.yy += ya;
    }

    public void render(Screen screen){
        // Renders particles
        screen.renderSprite((int)xx, (int)yy, sprite, true);
    }

}
