package com.aryan.rain;

import com.aryan.rain.graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable{

    public static int width = 300;
    public static int height = width / 16 * 9; //Width: 168
    public static int scale = 3;

    private JFrame frame;

    private Thread thread;

    private boolean running = false;

    private Screen screen;

    private BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();


    public Game(){
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);

        screen = new Screen(width, height);

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
            update();
            render();
        }
    }

    public void update(){
        // Logic
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        screen.render();

        for(int i=0; i<pixels.length; i++){
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(), getHeight());
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);

        g.dispose();

        bs.show();
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

        game.start();

    }

}