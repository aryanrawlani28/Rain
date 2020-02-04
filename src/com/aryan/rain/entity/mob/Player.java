package com.aryan.rain.entity.mob;

import com.aryan.rain.Game;
import com.aryan.rain.entity.projectile.Projectile;
import com.aryan.rain.entity.projectile.WizardProjectile;
import com.aryan.rain.events.Event;
import com.aryan.rain.events.EventDispatcher;
import com.aryan.rain.events.EventListener;
import com.aryan.rain.events.types.MousePressedEvent;
import com.aryan.rain.events.types.MouseReleasedEvent;
import com.aryan.rain.graphics.AnimatedSprite;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;
import com.aryan.rain.graphics.SpriteSheet;
import com.aryan.rain.graphics.ui.*;
import com.aryan.rain.input.Keyboard;
import com.aryan.rain.input.Mouse;
import com.aryan.rain.util.Vector2i;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Mob implements EventListener {

    private String name;
    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private boolean walking = false;

    private boolean shooting = false;

    private BufferedImage image;

    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

    private AnimatedSprite animSprite = down;

    private int fireRate = 0;

    private UIManager ui;
    private UIProgressBar uiHealthBar;
    private UIButton button;


    @Deprecated
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

        this.health = 100;

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

        uiHealthBar = new UIProgressBar(new Vector2i(10, 215), new Vector2i(220, 20));
        uiHealthBar.setColor(0x6a6a6a);
        uiHealthBar.setForegroundColor(0xee3a3a);
        panel.addComponent(uiHealthBar);

        UILabel hpLabel = new UILabel(new Vector2i(uiHealthBar.pos).add(new Vector2i(2, 16)), "HP");
        hpLabel.setColor(0xffffff);
        hpLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.addComponent(hpLabel);

//        uiHealthBar.setProgress(1.0);

        button = new UIButton(new Vector2i(10, 260), new Vector2i(120, 30), new UIActionListener() {
            @Override
            public void perform() {
                // Button press events here
                System.out.println("Button has been pressed");
            }
        });
//        button.setButtonListener(new UIButtonListener(){
//            public void pressed(UIButton button){
//                button.performAction();
//                button.ignoreNextPress();
//            }
//        });
        button.setText("Button");
        panel.addComponent(button);

        // Image button:
        try {
            image = ImageIO.read(new File("res/textures/home.png"));
            // System.out.println(image.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        UI Button Funtionality:
//        UIButton imageButton = new UIButton(new Vector2i(10, 360), image, new UIActionListener() {
//            public void perform() {
//                // System.exit(0);
//            }
//        });
//        imageButton.setButtonListener(new UIButtonListener() {
//            public void entered(UIButton button) {
//                button.setImage(ImageUtils.changeBrightness(image, -50));
//            }
//
//            public void exited(UIButton button) {
//                button.setImage(image);
//            }
//
//            public void pressed(UIButton button) {
//                button.setImage(ImageUtils.changeBrightness(image, 50));
//            }
//
//            public void released(UIButton button) {
//                button.setImage(image);
//            }
//        });
//        panel.addComponent(imageButton);
    }

    public void onEvent(Event event){
        EventDispatcher dispatcher = new EventDispatcher(event);

        dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent)e));
        dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent)e));
    }

    public boolean onMousePressed(MousePressedEvent e){
        if (Mouse.getX() > 660) return false;         // Will not shoot if press on the UI

//        double progress = uiHealthBar.getProgress();
        if (Mouse.getButton() == MouseEvent.BUTTON1) {
            shooting = true;


//            // atan2 automatically handles div by zero. So no crash. just atan doesn't handle.
//            double dx = Mouse.getX() - Game.getWindowWidth()/2;
//            double dy = Mouse.getY() - Game.getWindowHeight()/2;
//            double dir = Math.atan2(dy, dx);
//            shoot(x, y, dir);
//
//            fireRate = WizardProjectile.FIRE_RATE;


//            uiHealthBar.setProgress(progress - 0.1);
//            uiHealthBar.setProgress((time++ % 100) / 100.0);
            return true;        // Means we've handled the event.
        }

        uiHealthBar.setProgress(health / 100.0);
//        uiHealthBar.setProgress((time++ % 100) / 100.0);


        return false;
    }

    public boolean onMouseReleased(MouseReleasedEvent e){
        if (Mouse.getButton() == MouseEvent.NOBUTTON){
            shooting = false;
            return true;
        }
        return false;
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

    private void updateShooting(){
        if (!shooting || fireRate > 0) return;
        // atan2 automatically handles div by zero. So no crash. just atan doesn't handle.
        double dx = Mouse.getX() - Game.getWindowWidth()/2;
        double dy = Mouse.getY() - Game.getWindowHeight()/2;
        double dir = Math.atan2(dy, dx);
        shoot(x, y, dir);

        fireRate = WizardProjectile.FIRE_RATE;
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

    int time = 0;
//    private void updateShooting() {
//
//    }

    // You don't wanna center it always to the player.
    public void render(Screen screen){
        int flip = 0;

        sprite = animSprite.getSprite();

        screen.renderMob((int)(x - 16), (int)(y - 16), sprite, flip);
    }
}
