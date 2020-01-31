package com.aryan.rain.entity.mob;

import com.aryan.rain.Game;
import com.aryan.rain.entity.projectile.Projectile;
import com.aryan.rain.entity.projectile.WizardProjectile;
import com.aryan.rain.graphics.AnimatedSprite;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;
import com.aryan.rain.graphics.SpriteSheet;
import com.aryan.rain.input.Keyboard;
import com.aryan.rain.input.Mouse;

import static com.aryan.rain.input.Mouse.getX;

public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private boolean walking = false;

    private AnimatedSprite test = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);

    // Projectile p;                   // This projectile is a weapon.
    private int fireRate = 0;


    public Player(Keyboard input){
        this.input = input;
        sprite = Sprite.player_forward;
    }

    // Sometimes players are created at a specific location.
    public Player(int x, int y, Keyboard input){
        this.x = x;
        this.y = y;

        this.input = input;
        sprite = Sprite.player_forward;
        fireRate = WizardProjectile.FIRE_RATE;
    }


    public void update(){
        // When press keys, move our player from here. Affects the entity x and y.
        // If user presses up+down at same time, this will effectively cancel movement and player will not move.

        test.update();

        if (fireRate > 0) fireRate--;

        int xa = 0, ya = 0;
        if (anim < 7500){
            anim++;
        }else {
            anim = 0;
        }
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;

        if (xa != 0 || ya != 0){
            move(xa, ya);
            walking = true;
        }else {
            walking = false;
        }

        clear();
        updateShooting();
    }

    private void clear() {
        for (int i = 0; i < level.getProjectiles().size(); i++) {
            Projectile p = level.getProjectiles().get(i);
            if (p.isRemoved()){
                level.getProjectiles().remove(i);
            }
        }
    }

    private void updateShooting() {

        if (Mouse.getButton() == 1 && fireRate <= 0) {
            // atan2 automatically handles div by zero. So no crash. just atan doesn't handle.
            double dx = Mouse.getX() - Game.getWindowWidth()/2;
            double dy = Mouse.getY() - Game.getWindowHeight()/2;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);

            fireRate = WizardProjectile.FIRE_RATE;
        }

    }

    // You don't wanna center it always to the player.
    public void render(Screen screen){
        int flip = 0;
        if (dir == 0){
            sprite = Sprite.player_forward;
            if (walking){
                if (anim % 20 > 10){
                    sprite = Sprite.player_forward_1;
                }else{
                    sprite = Sprite.player_forward_2;
                }
            }
        }

        if(dir == 1){
            sprite = Sprite.player_side;
            if (walking){
                if (anim % 20 > 10){
                    sprite = Sprite.player_side_1;
                }else{
                    sprite = Sprite.player_side_2;
                }
            }
        }

        if (dir == 2) {
            sprite = Sprite.player_back;
            if (walking){
                if (anim % 20 > 10){
                    sprite = Sprite.player_back_1;
                }else{
                    sprite = Sprite.player_back_2;
                }
            }
        }

        if (dir == 3) {
            sprite = Sprite.player_side;
            flip = 1;
            if (walking){
                if (anim % 20 > 10){
                    sprite = Sprite.player_side_1;
                }else{
                    sprite = Sprite.player_side_2;
                }
            }
        }

        // Testing
        sprite = test.getSprite();
        //
        screen.renderPlayer(x-16, y-16, sprite, flip);
    }
}
