package com.aryan.rain.graphics;

public class Font{

    private static SpriteSheet font = new SpriteSheet("res/fonts/arial.png", 208, 96);
    private static Sprite[] characters = Sprite.split(font);

    private static String charIndex = "ABCDEFGHIJKLM" + //      // This is kind of like a lookup table.
            "NOPQRSTUVWXYZ" + //
            "abcdefghijklm" + //
            "nopqrstuvwxyz" + //
            "0123456789.,'" + //
            "'“”;:!@$%()-+";

    public Font(){}

    public void render(int x, int y, String text, Screen screen) {
        render(x, y, 0, 0, text, screen);
    }

    public void render(int x, int y, int color, String text, Screen screen) {
        render(x, y, 0, color, text, screen);
    }

//    public void render(int x, int y, int spacing, int color, String text, Screen screen){
//        int x = 50;
//        int y = 50;
//        int xOffset = 0;
//        int line = 0;
//
//        for (int i = 0; i < text.length(); i++) {
//            xOffset += 16 + spacing;
//            int yOffset = 0;
//            char currentChar = text.charAt(i);
//            if (currentChar == 'g' || currentChar == 'y' || currentChar == 'q' || currentChar == ',' || currentChar == 'p' || currentChar == 'j') yOffset = 2;
//            if (currentChar == '\n') {
//                xOffset = 0;
//                line++;
//            }
//            int index = charIndex.indexOf(currentChar);
//            if (index == -1) continue;
//            screen.renderSprite(x + i*16, y + yOffset, characters[index], false);
//        }
//
//    }

    public void render(int x, int y, int spacing, int color, String text, Screen screen) {
        int xOffset = 0;
        int line = 0;
        for (int i = 0; i < text.length(); i++) {
            xOffset += 16 + spacing;
            int yOffset = 0;
            char currentChar = text.charAt(i);
            if (currentChar == 'g' || currentChar == 'y' || currentChar == 'q' || currentChar == 'p' || currentChar == 'j' || currentChar == ',') yOffset = 4;
            if (currentChar == '\n') {
                xOffset = 0;
                line++;
            }
            int index = charIndex.indexOf(currentChar);
            if (index == -1) continue;
            screen.renderTextCharacter(x + xOffset, y + line * 20 + yOffset, characters[index], color, false);
        }
    }

}
