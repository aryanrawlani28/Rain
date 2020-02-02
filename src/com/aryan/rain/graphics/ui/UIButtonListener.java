package com.aryan.rain.graphics.ui;

public class UIButtonListener {

    // Most imp: Mouse,

    //////// Mouse ////////

    // MouseUP and MouseDOWN
    // MouseEntered and MouseExited

    public void entered(UIButton button){
        button.setColor(0xcdcdcd);
//        System.out.println("entered!");
    }
    public void exited(UIButton button){
        button.setColor(0xaaaaaa);
//        System.out.println("exited!");
    }

    public void pressed(UIButton button){
        button.setColor(0xcc22cc);
//        System.out.println("pressed!");
    }
    public void released(UIButton button){
        button.setColor(0x22cc22);
//        System.out.println("Released!");
    }

    ////////////////////////

}
