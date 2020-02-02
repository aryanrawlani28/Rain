package com.aryan.rain.graphics.ui;

import com.aryan.rain.util.Vector2i;

import java.awt.*;

public class UIButton extends UIComponent {

    private UIButtonListener buttonListener;
    public UILabel label;

    public UIButton(Vector2i pos, Vector2i size) {
        super(pos, size);
        Vector2i lp = new Vector2i(pos);
        lp.x += 4;
        lp.y += size.y - 1;

        label = new UILabel(pos.add(5), "");
        label.setColor(0x444444);
        label.active = false;

        color = new Color(0xAAAAAA);

    }

    public void init(UIPanel panel){
        super.init(panel);
        panel.addComponent(label);
    }

    public void setText(String text){
        if (text == ""){
            label.active = false;
            return;
        }else {
            label.text = text;
        }
    }



    public void update(){
        // animation and stuff
    }

    public void render(Graphics g){
        g.setColor(color);
        g.fillRect(pos.x + offset.x, pos.y + offset.y, size.x, size.y);

        if (label != null){
            label.render(g);
        }
    }
}
