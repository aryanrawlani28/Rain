package com.aryan.rain.graphics.ui;

import com.aryan.rain.util.Vector2i;

import java.awt.*;

public class UIComponent {

    public Vector2i pos, size;
    protected Vector2i offset;
    public Color color;

    protected UIPanel panel;

    public boolean active = true;

    UIComponent(Vector2i pos){
        this.pos = pos;
        offset = new Vector2i();
    }

    UIComponent(Vector2i pos, Vector2i size){
        this.pos = pos;
        this.size = size;
        offset = new Vector2i();
    }

    public void init(UIPanel panel){
        this.panel = panel;
    }

    public UIComponent setColor(int c){
        this.color = new Color(c);
        return this;
    }

    public void update() {

    }
    public void render(Graphics g){

    }

    public Vector2i getAbsolutePosition(){
        return new Vector2i(pos).add(offset);
    }

    void setOffset(Vector2i offset){
        this.offset = offset;
    }
}
