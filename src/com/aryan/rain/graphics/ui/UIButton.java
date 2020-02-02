package com.aryan.rain.graphics.ui;

import com.aryan.rain.input.Mouse;
import com.aryan.rain.util.Vector2i;

import java.awt.*;

public class UIButton extends UIComponent {

    public UILabel label;
    private UIButtonListener buttonListener;
    private UIActionListener actionListener;

    private boolean inside = false;

    public UIButton(Vector2i pos, Vector2i size, UIActionListener actionListener) {
        super(pos, size);
        this.actionListener = actionListener;
        Vector2i lp = new Vector2i(pos);
        lp.x += 4;
        lp.y += size.y - 2;

        label = new UILabel(lp, "");
        label.setColor(0x444444);
        label.active = false;

        init();
    }

    public void init(){
        setColor(0xaaaaaa);
        buttonListener = new UIButtonListener();
    }

    public void init(UIPanel panel){
        super.init(panel);
        panel.addComponent(label);
    }

    public void setText(String text){
        if (text == ""){
            label.active = false;
        }else {
            label.text = text;
        }
    }



    public void update(){
        // animation and stuff
        Rectangle rect = new Rectangle(getAbsolutePosition().x, getAbsolutePosition().y, size.x, size.y);
        if (rect.contains(new Point(Mouse.getX(), Mouse.getY()))){
            if (!inside) {
                buttonListener.entered(this);
                inside = true;
            }
        }else{
            if (inside) {
                buttonListener.exited(this);
            }
            inside = false;
        }
    }

    public void render(Graphics g){
        g.setColor(color);
        g.fillRect(pos.x + offset.x, pos.y + offset.y, size.x, size.y);

        if (label != null){
            label.render(g);
        }
    }
}
