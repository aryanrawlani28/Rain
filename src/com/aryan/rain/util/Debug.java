package com.aryan.rain.util;

import com.aryan.rain.graphics.Screen;

public class Debug {

    // Private because this is never supposed to be instantiated.
    private Debug(){}

    public static void drawRect(Screen screen, int x, int y, int width, int height, boolean fixed){
        screen.drawRect(x, y, width, height, 0xff0000, fixed);
    }

    public static void drawRect(Screen screen, int x, int y, int width, int height, int color, boolean fixed){
        screen.drawRect(x, y, width, height, color, fixed);
    }

}
