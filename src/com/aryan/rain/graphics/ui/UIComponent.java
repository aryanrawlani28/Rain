package com.aryan.rain.graphics.ui;

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.util.Vector2i;

import java.awt.*;

public class UIComponent {

    public int backgroundColor;
    public Vector2i pos, offset;
    public Color color;
    private Font font;

    UIComponent(Vector2i pos){
        this.pos = pos;
        offset = new Vector2i();
    }

    public UIComponent setFont(Font f){
        this.font = f;
        return this;
    }

    public UIComponent setColor(int c){
        this.color = new Color(c);
        return this;
    }

    public void update() {

    }
    public void render(Graphics g){

    }

    void setOffset(Vector2i offset){
        this.offset = offset;
    }
}
