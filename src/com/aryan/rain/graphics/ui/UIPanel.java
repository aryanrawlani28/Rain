package com.aryan.rain.graphics.ui;

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;
import com.aryan.rain.util.Vector2i;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIPanel extends UIComponent{
    private List<UIComponent> components = new ArrayList<UIComponent>();
    private Vector2i size;

    public UIPanel(Vector2i pos, Vector2i size){
        super(pos);
        this.pos = pos;
        color = new Color(0xcacaca);
        this.size = size;
    }

    public void addComponent(UIComponent component){
        init(this);
        components.add(component);
    }

    public void update(){
        for (UIComponent component : components){
            component.setOffset(pos);
            component.update();
        }
    }

    public void render(Graphics g){
        g.setColor(color);
        g.fillRect(pos.x, pos.y, size.x, size.y);
        for (UIComponent component : components){
            component.render(g);
        }
    }
}
