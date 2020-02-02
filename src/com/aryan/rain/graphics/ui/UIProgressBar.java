package com.aryan.rain.graphics.ui;

import com.aryan.rain.util.Vector2i;

import java.awt.*;

public class UIProgressBar extends UIComponent{

    private Vector2i size;
    private double progress;   // 0-100

    private Color foregroundColor;

    public UIProgressBar(Vector2i pos, Vector2i size) {
        super(pos);
        this.size = size;
        this.progress = 0;

        foregroundColor = new Color(0xff00ff);
    }

    public void setForegroundColor(int color){
        this.foregroundColor = new Color(color);
    }

    public void setProgress(double progress){
        if (progress < 0.0 || progress > 1.0) return;
        this.progress = progress;
    }

    public double getProgress(){
        return progress;
    }

    public void update(){}

    public void render(Graphics g){
        g.setColor(color);
        g.fillRect(pos.x + offset.x, pos.y + offset.y, size.x, size.y);

        g.setColor(foregroundColor);
        g.fillRect(pos.x + offset.x, pos.y + offset.y, (int) (progress*size.x), size.y);
    }
}
