package com.aryan.rain.graphics.ui;

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.Sprite;
import com.aryan.rain.util.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class UIPanel {
    private List<UIComponent> components = new ArrayList<UIComponent>();
    private Vector2i pos;

    private Sprite sprite;

    public UIPanel(Vector2i pos){
        this.pos = pos;
        sprite = new Sprite(80, 168, 0xcacaca);
    }

    public void addCompnent(UIComponent component){
        components.add(component);
    }

    public void update(){
        for (UIComponent component : components){
            component.setOffset(pos);
            component.update();
        }
    }

    public void render(Screen screen){
        screen.renderSprite(pos.x, pos.y, sprite, false);
        for (UIComponent component : components){
            component.render(screen);
        }
    }
}
