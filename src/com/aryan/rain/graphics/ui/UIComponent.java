package com.aryan.rain.graphics.ui;

import com.aryan.rain.graphics.Screen;
import com.aryan.rain.util.Vector2i;

public class UIComponent {

    public int backgroundColor;
    public Vector2i pos, offset;

    UIComponent(Vector2i pos){
        this.pos = pos;
        offset = new Vector2i();
    }

    public void update(){

    }

    public void render(Screen screen){


    }

    void setOffset(Vector2i offset){
        this.offset = offset;
    }
}
