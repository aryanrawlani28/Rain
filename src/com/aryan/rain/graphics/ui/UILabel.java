package com.aryan.rain.graphics.ui;

import com.aryan.rain.util.Vector2i;

import java.awt.*;

public class UILabel extends UIComponent {

    public String text;
    private Font font;
    private Color color;

    public UILabel(Vector2i pos, String text) {
        super(pos);
        font = new Font("Helvetica", Font.PLAIN, 32);
        this.text = text;
        this.color = new Color(0xff00ff);
    }

    public void render(Graphics g){
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, pos.x + offset.x, pos.y + offset.y);
    }
}
