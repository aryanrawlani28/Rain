package com.aryan.rain.entity.mob;

import com.aryan.rain.graphics.AnimatedSprite;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;
import com.aryan.rain.graphics.SpriteSheet;
import com.aryan.rain.level.Node;
import com.aryan.rain.util.Vector2i;

import java.util.List;

public class Star extends Mob {
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);

    private AnimatedSprite animSprite = down;

    public static final int NO_OF_STAR = 1;

    private int xa = 0;
    private int ya = 0;

    // private int x, y;

    private double speed = 1;

    private int time = 0;

    private List<Node> path = null;


    public Star(int x, int y){
        this.x = (x << 4);
        this.y = (y << 4);

        this.sprite = Sprite.dummy;
    }

    private void move() {
        xa = 0;
        ya = 0;

        int px = (int) level.getPlayerAt(0).getX();      // This will be in pixel-precision
        int py = (int) level.getPlayerAt(0).getY();

        Vector2i start = new Vector2i((int)getX() >> 4, (int)getY() >> 4);
        Vector2i destination = new Vector2i(px >> 4, py >> 4);

        if (time % 3 == 0) path = level.findPath(start, destination);

        if (path != null){
            if (path.size() > 0){
                Vector2i vec = path.get(path.size() - 1).tile;

                if (x < (vec.getX() << 4)) xa++;
                if (x > (vec.getX() << 4)) xa--;
                if (y < (vec.getY() << 4)) ya++;
                if (y > (vec.getY() << 4)) ya--;

            }
        }else{
            // Random move.
        }

        if (xa != 0 || ya != 0){
            move(xa, ya);
            walking = true;
        }else {
            walking = false;
        }



    }

    @Override
    public void update() {
        move();
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
    }

    @Override
    public void render(Screen screen) {
        sprite = animSprite.getSprite();

        screen.renderMob((int)x - 16 , (int)y - 16,this);
    }
}
