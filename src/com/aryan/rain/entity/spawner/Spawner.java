package com.aryan.rain.entity.spawner;

import com.aryan.rain.entity.Entity;
import com.aryan.rain.entity.particle.Particle;
import com.aryan.rain.level.Level;

import java.util.ArrayList;
import java.util.List;

public class Spawner extends Entity {

    // Going to be a parent of any entity. So firstly, it is an entity in itself.
    // Not every has life. Eg mobs are there until they die. Particles however, disappear after a short while.

    // Creates own file in compiler
    public enum Type{
        MOB, PARTICLE
    }

    private Type type;

    public Spawner(int x, int y, Type type, int amount, Level level){
        init(level);

        this.x = x;
        this.y = y;

        this.type = type;


    }

}
