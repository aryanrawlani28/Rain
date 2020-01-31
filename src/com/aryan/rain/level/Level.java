package com.aryan.rain.level;

// L1: Random gen (Noise mapping - advanced)
// L2: Map level

import com.aryan.rain.entity.Entity;
import com.aryan.rain.entity.particle.Particle;
import com.aryan.rain.entity.projectile.Projectile;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.level.tile.Tile;

import java.util.ArrayList;
import java.util.List;

// Manages which tiles need to be rendered.
public class Level {

    protected int width, height;                    // Primarily for L1
    protected int[] tilesInt;                       // Eg tiles[1] is grass, 2 is stone, etc..

    protected int[] tiles;  // Contains all of levels tiles. Since one level loaded at a time, this works well.

    private List<Entity> entities = new ArrayList<Entity>();
    private List<Projectile> projectiles = new ArrayList<Projectile>();
    private List<Particle> particles = new ArrayList<Particle>();

    public static Level Spawn = new SpawnLevel("res/levels/spawn_level.png");


    // Used when we generate a random level
    public Level(int width, int height){
        // L1
        this.width = width;
        this.height = height;

        tilesInt = new int[width * height];            // Tile for every square

        generateLevel();
    }

    public Level(String path){
        // L2
        loadLevel(path);
        generateLevel();
    }

    protected void generateLevel() {

    }

    protected void loadLevel(String path){
          
    }

    public List<Projectile> getProjectiles(){
        return projectiles;
    }


    public void update(){
        // AI and stuff goes in update. 60 updates per sec
        for (int i=0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved()){
                entities.remove(i);
            }else {
                entities.get(i).update();
            }
        }

        for (int j=0; j < projectiles.size(); j++) {
            if (projectiles.get(j).isRemoved()) {
                projectiles.remove(j);
            }else{
                projectiles.get(j).update();
            }
        }

        for (int k=0; k < particles.size(); k++) {
            if (particles.get(k).isRemoved()){
                particles.remove(k);
            }else{
                particles.get(k).update();
            }
        }

        remove();
    }

    private void remove(){

    }

    private void time(){
        // change level and stuff based on time.
    }

    public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset){
        // boolean solid = false;
        // Size is the size of our object

        for(int c = 0; c < 4; c++){

            int xt = (x - c % 2 * size + xOffset) >> 4;   // basically do we use size or not? to check each corner
            int yt = (y - c / 2 * size + yOffset) >> 4;

            if (getTile(xt, yt).solid()) return true;
        }

        return false;
    }

    //this method and vars define the render region
    public void render(int xScroll, int yScroll, Screen screen){
        screen.setOffset(xScroll, yScroll);

        // as fast as possible. probably empty here.
        // Corner pins: rendering is done from top left to top right

        int x0 = xScroll >> 4;                          // x0 is the inmost x coord.. left side of the screen.
        int x1 = (xScroll + screen.width + 16) >> 4;    // x1 is the right side of the screen (16 is being added for aesthetic purpose and prevent black!)
        int y0 = yScroll >> 4;                          // y0 is the top
        int y1 = (yScroll + screen.height + 16) >> 4;   // y1 is bottom

        for (int y = y0; y < y1; y++){
            for (int x = x0; x < x1; x++){
                getTile(x, y).render(x, y, screen);
            }
        }

        for (int i=0; i< entities.size(); i++) {
            entities.get(i).render(screen);
        }

        for (int j=0; j < projectiles.size(); j++) {
            projectiles.get(j).render(screen);
        }

        for (int k=0; k < particles.size(); k++) {
            particles.get(k).render(screen);
        }
    }


    public void add(Entity e){
        e.init(this);

        if (e instanceof Particle){
            particles.add((Particle) e);
        }else if (e instanceof Projectile){
            projectiles.add((Projectile) e);
        }else {
            entities.add(e);
        }
    }


    // We will render what this method returns. This converts arry to tiles.
    public Tile getTile(int x, int y){

        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.spawn_grass;

        if (tiles[x+y*width] == Tile.col_spawn_floor) return Tile.spawn_floor;
        if (tiles[x+y*width] == Tile.col_spawn_grass) return Tile.spawn_grass;
        if (tiles[x+y*width] == Tile.col_spawn_hedge) return Tile.spawn_hedge;
        if (tiles[x+y*width] == Tile.col_spawn_wall1) return Tile.spawn_wall1;
        if (tiles[x+y*width] == Tile.col_spawn_wall2) return Tile.spawn_wall2;
        if (tiles[x+y*width] == Tile.col_spawn_water) return Tile.spawn_water;

        return Tile.spawn_grass;
    }
}
