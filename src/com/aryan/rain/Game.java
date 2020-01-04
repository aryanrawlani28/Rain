package com.aryan.rain;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable{

    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;

    private JFrame frame;

    private Thread thread;

    private boolean running = false;

    public Game(){
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);

        frame = new JFrame();
    }

    private synchronized void start(){
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    private synchronized void end(){
        running = false;
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void run(){
        while (running){
            // game elements
            System.out.println("Running the game!");
        }
    }

    public static void main(String[] args){
        Game game = new Game();

        game.frame.setResizable(false);
        game.frame.setTitle("Rain");
        game.frame.add(game);

        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        // game.frame.setPreferredSize();

        game.start();

    }

}
