package com.aryan.rain.graphics;

public class Font{

    private static SpriteSheet font = new SpriteSheet("res/fonts/arial.png", 16);
    private static Sprite[] characters = Sprite.split(font);
//    private static SpriteSheet font_characters = new SpriteSheet();

    public Font(){}

    public void render(Screen screen){
        screen.renderSprite(50,50, characters[0], false);
    }

}
