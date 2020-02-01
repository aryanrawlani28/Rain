package com.aryan.rain.entity.mob;

import com.aryan.rain.entity.Entity;
import com.aryan.rain.graphics.AnimatedSprite;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;
import com.aryan.rain.graphics.SpriteSheet;
import com.aryan.rain.util.Debug;
import com.aryan.rain.util.Vector2i;

import java.util.List;
import java.util.Random;

public class Shooter extends Mob {

    public static final int NO_OF_SHOOTER = 5;

    private int time = 0;
    private int xa = 0, ya = 0;

    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);

    private AnimatedSprite animSprite = down;

    Entity rand = null;

    public Shooter(int x, int y){
        this.x = x << 4;
        this.y = y << 4;

        sprite = Sprite.dummy;
    }

    // Logic & behaviour
    public void update() {

        time++;         // 60 inc per second. time & 60 == 0 -> once per second ie. one sec

        if (time % (random.nextInt(90) + 30) == 0){
//            xa = -xa;
//            ya = -ya;
            xa = random.nextInt(3) - 1;     // -1 = left, 0 = stationary, 1 = right
            ya = random.nextInt(3) - 1;

            if (random.nextInt(4) == 0){
                xa = 0;
                ya = 0;
            }
        }



        // boolean walking = false;
        if (walking) animSprite.update();
        else animSprite.setFrame(0);
        if (ya < 0) {
            animSprite = up;
            dir = Direction.UP;
        }else if (ya > 0){
            animSprite = down;
            dir = Direction.DOWN;
        }

        if (xa < 0){
            animSprite = left;
            dir = Direction.LEFT;
        }else if (xa > 0){
            animSprite = right;
            dir = Direction.RIGHT;
        }

        if (xa != 0 || ya != 0){
            move(xa, ya);
            walking = true;
        }else {
            walking = false;
        }

        shootClosest();
        // shootRandom();
    }

    private void shootRandom() {
        if (time % (30 + random.nextInt(60)) == 0) {
            List<Entity> entities = level.getEntities(this, 200);
            entities.add(level.getClientPlayer());

            int index = random.nextInt(entities.size());

            rand = entities.get(index);
        }

        if (rand != null) {
            int px = (int) rand.getX();
            int py = (int) rand.getY();

            double dx = (px - this.x);
            double dy = (py - this.y);

            double dir = Math.atan2(dy, dx);

            shoot(x, y, dir);
        }
    }

    private void shootClosest(){
        List<Entity> entities = level.getEntities(this, 200);
        entities.add(level.getClientPlayer());

        double min = 0;
        Entity closest = null;
        for (int i=0; i < entities.size(); i++){
            Entity e = entities.get(i);
            double distance = Vector2i.getDistance(new Vector2i((int)x, (int)y), new Vector2i((int)e.getX(), (int)e.getY()));

            if (i==0 || distance < min) {
                min = distance;
                closest = e;
            }

        }

        if (closest != null) {
            int px = (int) closest.getX();
            int py = (int) closest.getY();

            double dx = (px - this.x);
            double dy = (py - this.y);

            double dir = Math.atan2(dy, dx);

            shoot(x, y, dir);
        }
    }

    // Visual stuff
    public void render(Screen screen) {
        sprite = animSprite.getSprite();
        Debug.drawRect(screen, 40, 40, 100, 40,true);
        screen.renderMob((int)x - 16,(int)y - 16,this);
    }
}
