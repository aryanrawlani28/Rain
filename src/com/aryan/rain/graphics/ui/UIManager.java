package com.aryan.rain.graphics.ui;

import com.aryan.rain.graphics.Screen;

import java.util.ArrayList;
import java.util.List;

public class UIManager {
    // Made of panels and components. Panel holds multiple components. Panels -> groups.
    private List<UIPanel> panels = new ArrayList<UIPanel>();


    public UIManager(){

    }

    public void addPanel(UIPanel panel){
        panels.add(panel);
    }

    public void update() {
        for (UIPanel panel : panels){
            panel.update();
        }
    }

    public void render(Screen screen) {
        for (UIPanel panel : panels){
            panel.render(screen);
        }
    }


}
