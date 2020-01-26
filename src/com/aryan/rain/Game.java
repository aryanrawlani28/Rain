package com.aryan.rain;

import com.aryan.rain.entity.mob.Player;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.input.Keyboard;
import com.aryan.rain.level.Level;
import com.aryan.rain.level.RandomLevel;
import com.aryan.rain.level.SpawnLevel;
import com.aryan.rain.level.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable{

    public static int width = 300;
    public static int height = width / 16 * 9;      // Width: 168
    public static int scale = 3;

    public static String title = "Rain";

    private JFrame frame;

    private Keyboard key;

    private Level level;                            // Only load one level at a time.

    private Player player;

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

        key = new Keyboard();

        level = Level.Spawn;

        TileCoordinate playerSpawn = new TileCoordinate(20, 59);
        player = new Player(20*16, 59*16, key);
        player.init(level);
        addKeyListener(key);
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
        long lastTime = System.nanoTime();

        long timer = System.currentTimeMillis();

        final double ns = 1_000_000_000.0 / 60.0;
        double delta = 0;

        int frames = 0;
        int updates = 0;

        while (running){
            long now = System.nanoTime();
            delta += ((now-lastTime) / ns);
            lastTime = now;

            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }

            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){      // Happens once per second.
                timer+=1000;

                frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
                frames = 0;
                updates = 0;
            }
        }
    }

    public void update(){
        key.update();
        player.update();
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        screen.clear();

        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;

        level.render(xScroll, yScroll, screen);
        player.render(screen);

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
        game.frame.setTitle(game.title);
        game.frame.add(game);

        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
        game.requestFocus();

    }

}
