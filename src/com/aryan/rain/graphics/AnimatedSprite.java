package com.aryan.rain.graphics;

public class AnimatedSprite extends Sprite {

    private int frame = 0;
    private Sprite sprite;
    private int rate = 5;

    private int time = 0;

    private int length = -1;        // 0 is invalid -> nonexistant frame

    public AnimatedSprite(SpriteSheet sheet, int width, int height, int length){
        super(sheet, width, height);
        this.length = length;
        sprite = sheet.getSprite()[0];

        if (length > sheet.getSprite().length) System.err.println("Some length animation error occured.");
    }

    public void update(){
        time++;
        if (time % rate == 0) {
            if (frame >= length - 1) frame = 0;
            else frame++;
            sprite = sheet.getSprite()[frame];
        }
        // System.out.println(sprite + " " + frame);
    }

    public Sprite getSprite(){
        return sprite;
    }

    public void setFrameRate(int frames){
        rate = frames;
    }

    public void setFrame(int index) {
        if (index > sheet.getSprite().length - 1) {
            System.err.println("Index bounds error");
            return;
        }

        sprite = sheet.getSprite()[index];

    }
}
