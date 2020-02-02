package com.aryan.rain.graphics.ui;

import com.aryan.rain.util.Vector2i;

import java.awt.*;

public class UILabel extends UIComponent {

    public String text;
    private Font font;
    private Color color;
    public boolean dropShadow = false;

    public UILabel(Vector2i pos, String text) {
        super(pos);
        font = new Font("Verdana", Font.PLAIN, 32);
        this.text = text;
        this.color = new Color(0xffaaff);
    }

    public UILabel setFont(Font font){
        this.font = font;
        return this;
    }

    public void render(Graphics g){
        if (dropShadow) {
            g.setColor(new Color(0));
            g.setFont(new Font(font.getFontName(), font.getStyle(), font.getSize() + 1));
            g.drawString(text, pos.x + offset.x + 2, pos.y + offset.y + 2);
        }

        g.setColor(color);
        g.setFont(font);
        g.drawString(text, pos.x + offset.x, pos.y + offset.y);
    }
}
