package com.aryan.rain.graphics.ui;

import com.aryan.rain.graphics.Font;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.util.Vector2i;

public class UILabel extends UIComponent {

    public String text;
    private Font font;

    public UILabel(Vector2i pos, String text) {
        super(pos);
        font = new Font();
    }

    public void render(Screen screen){
//        font.render((pos.add(offset.x)), pos.add(offset.y), text, screen);
    }
}
