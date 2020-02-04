package com.aryan.rain.graphics.ui;

import com.aryan.rain.input.Mouse;
import com.aryan.rain.util.Vector2i;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class UIButton extends UIComponent {

    public UILabel label;
    private UIButtonListener buttonListener;
    private UIActionListener actionListener;

    private Image image;

    private boolean inside = false;
    private boolean pressed = false;
    private boolean ignorePressed =false;
    private boolean ignoreAction = false;

    public UIButton(Vector2i pos, Vector2i size, UIActionListener actionListener) {
        super(pos, size);
        this.actionListener = actionListener;
        Vector2i lp = new Vector2i(pos);
        lp.x += 660;
        lp.y += size.y;

        label = new UILabel(lp, "");
        label.setColor(0x444444);
        label.active = false;

        init();
    }

    public UIButton(Vector2i position, BufferedImage image, UIActionListener actionListener){
        super(position, new Vector2i(image.getWidth(), image.getHeight()));
        this.actionListener = actionListener;
        setImage(image);
        init();

    }

    public void init(){
        setColor(0xaaaaaa);
        buttonListener = new UIButtonListener();
    }

    public void init(UIPanel panel){
        super.init(panel);
        if (label != null) {
            panel.addComponent(label);
        }
    }

    public void setText(String text){
        if (text == ""){
            label.active = false;
        }else {
            label.text = text;
        }
    }


    public void setButtonListener(UIButtonListener buttonListener){
        this.buttonListener = buttonListener;
    }

    public void setImage(Image image){
        this.image = image;
    }

    public void update(){
        // animation and stuff
        Rectangle rect = new Rectangle(getAbsolutePosition().x, getAbsolutePosition().y, size.x, size.y);
        boolean leftMouseButtonDown = Mouse.getButton() == MouseEvent.BUTTON1;
        if (rect.contains(new Point(Mouse.getX(), Mouse.getY()))){
            if (!inside) {
                if (leftMouseButtonDown){
                    // entered while pressing
                    ignorePressed = true;
                }else{
                    ignorePressed = false;
                }
                buttonListener.entered(this);
                inside = true;
            }

            if (!pressed && !ignorePressed && leftMouseButtonDown){
                // If we've clicked the button
                pressed = true;
//                blocked = true;
                buttonListener.pressed(this);
            }else if (Mouse.getButton() == MouseEvent.NOBUTTON){
                if (pressed){
                    pressed = false;
                    buttonListener.released(this);
                    if (!ignoreAction) {
                        actionListener.perform();
                    }
                }
                ignorePressed = false;
            }
        }else{
            if (inside) {
                buttonListener.exited(this);
                pressed = false;
            }
            inside = false;
        }
    }

    public void render(Graphics g){

        int x = pos.x + offset.x;
        int y = pos.y + offset.y;

        if (image != null){
            g.drawImage(image, x, y, null);
        }else {

        }

        g.setColor(color);
        g.fillRect(x, y, size.x, size.y);

        if (label != null){
            label.render(g);
        }
    }



    public void performAction() {
        actionListener.perform();

    }

    public void ignoreNextPress() {

    }
}
