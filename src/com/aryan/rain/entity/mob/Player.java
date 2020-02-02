package com.aryan.rain.entity.mob;

import com.aryan.rain.Game;
import com.aryan.rain.entity.Entity;
import com.aryan.rain.entity.projectile.Projectile;
import com.aryan.rain.entity.projectile.WizardProjectile;
import com.aryan.rain.graphics.AnimatedSprite;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;
import com.aryan.rain.graphics.SpriteSheet;
import com.aryan.rain.graphics.ui.UILabel;
import com.aryan.rain.graphics.ui.UIManager;
import com.aryan.rain.graphics.ui.UIPanel;
import com.aryan.rain.input.Keyboard;
import com.aryan.rain.input.Mouse;
import com.aryan.rain.util.Vector2i;

import java.awt.*;
import java.util.List;

public class Player extends Mob {

    private String name;
    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private boolean walking = false;

    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

    private AnimatedSprite animSprite = down;

    // Projectile p;                   // This projectile is a weapon.
    private int fireRate = 0;

    private UIManager ui;


    public Player(String name, Keyboard input){
        this.name = name;
        this.input = input;
        sprite = Sprite.player_forward;
        animSprite = down;
    }

    // Sometimes players are created at a specific location.
    public Player(String name, int x, int y, Keyboard input){
        this.name = name;
        this.x = x;
        this.y = y;

        this.input = input;
        sprite = Sprite.player_forward;
        fireRate = WizardProjectile.FIRE_RATE;

        ui = Game.getUIManager();
        UIPanel panel = (UIPanel) new UIPanel(new Vector2i((300-80)*3, 0), new Vector2i(80*3, 168*3)).setColor(0x4f4f4f);
        ui.addPanel(panel);
        UILabel nameLabel = new UILabel(new Vector2i(40,200), name);
        nameLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        nameLabel.setColor(0xbbbbbb);
        nameLabel.dropShadow = true;
        panel.addComponent(nameLabel);
    }


    public void update(){
        // When press keys, move our player from here. Affects the entity x and y.
        // If user presses up+down at same time, this will effectively cancel movement and player will not move.

        double speed = 1.25;

        if (walking) animSprite.update();
        else animSprite.setFrame(0);

        if (fireRate > 0) fireRate--;

        double xa = 0, ya = 0;
        if (anim < 7500){
            anim++;
        }else {
            anim = 0;
        }


        if (input.up) {
            ya -= speed;
            animSprite = up;
        }else if (input.down){
            ya += speed;
            animSprite = down;
        }

        if (input.left){
            animSprite = left;
            xa -= speed;
        }else if (input.right){
            animSprite = right;
            xa += speed;
        }

        if (xa != 0 || ya != 0){
            move(xa, ya);
            walking = true;
        }else {
            walking = false;
        }

        clear();
        updateShooting();
    }

    public String getName(){
        return name;
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

        sprite = animSprite.getSprite();

        screen.renderMob((int)(x - 16), (int)(y - 16), sprite, flip);
    }
}
