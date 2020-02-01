package com.aryan.rain.graphics;

public class Font{

    private static SpriteSheet font = new SpriteSheet("res/fonts/arial.png", 16);
    private static Sprite[] characters = Sprite.split(font);

    private static String charIndex = "ABCDEFGHIJKLM" + //      // This is kind of like a lookup table.
            "NOPQRSTUVWXYZ" + //
            "abcdefghijklm" + //
            "nopqrstuvwxyz" + //
            "0123456789.,'" + //
            "'“”;:!@$%()-+";

    public Font(){}

    public void render(String text, Screen screen){
        int x = 50, y = 50;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') continue;
            char currentChar = text.charAt(i);
            int index = charIndex.indexOf(currentChar);
            screen.renderSprite(x + i*16, y, characters[index], false);
        }

    }

}
