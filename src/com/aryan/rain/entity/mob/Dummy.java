package com.aryan.rain.entity.mob;

import com.aryan.rain.graphics.AnimatedSprite;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;
import com.aryan.rain.graphics.SpriteSheet;

public class Dummy extends Mob{

    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);

    private AnimatedSprite animSprite = down;

    public static final int NO_OF_DUMMIES = 5;

    private int xa = 0;
    private int ya = 0;

    private int time = 0;

    public Dummy(int x, int y){
        // X, Y are spawning locations of NPCs

        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummy;
    }

    @Override
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
    }

    @Override
    public void render(Screen screen) {
        sprite = animSprite.getSprite();
        screen.renderMob((int)x, (int)y, sprite, 0);

    }
}
