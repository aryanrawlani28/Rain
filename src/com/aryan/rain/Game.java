package com.aryan.rain;

import com.aryan.rain.entity.mob.Player;
import com.aryan.rain.events.Event;
import com.aryan.rain.events.EventListener;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.layers.Layer;
import com.aryan.rain.graphics.ui.UIManager;
import com.aryan.rain.input.Keyboard;
import com.aryan.rain.input.Mouse;
import com.aryan.rain.level.Level;
import com.aryan.rain.level.TileCoordinate;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable, EventListener {

    private static int width = 300 - 80;
    private static int height = 168;      // Width: 168
    private static int scale = 3;

    public static String title = "Rain";

    private JFrame frame;

    private Keyboard key;

    private Level level;                            // Only load one level at a time.

    private Player player;

    private Thread thread;

    private boolean running = false;

    private static UIManager uiManager;

    private Screen screen;

    private BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();

    private List<Layer> layerStack = new ArrayList<Layer>();


    public Game(){
        Dimension size = new Dimension(width*scale + 80*3, height*scale);
        setPreferredSize(size);
        screen = new Screen(width, height);
        uiManager = new UIManager();

        frame = new JFrame();

        key = new Keyboard();

        level = Level.Spawn;
        addLayer(level);

         TileCoordinate playerSpawn = new TileCoordinate(20, 59);         // TODO: Issue #1 : Does not work
        player = new Player("Aryan", playerSpawn.x(), playerSpawn.y(), key);

        level.add(player);

        addKeyListener(key);

        Mouse mouse = new Mouse(this);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public static int getWindowWidth(){
        return width * scale;
    }

    public static int getWindowHeight(){
        return height * scale;
    }

    public static UIManager getUIManager(){
        return uiManager;
    }

    public void addLayer(Layer layer){
        layerStack.add(layer);
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

    public void onEvent(Event event){
        for (int i= layerStack.size() - 1; i >= 0; i--){
            layerStack.get(i).onEvent(event);
        }
    }

    public void update(){
        key.update();
//        level.update();
        uiManager.update();

        // Update layers here:
        for (int i=0; i < layerStack.size(); i++){
            layerStack.get(i).update();
        }
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        screen.clear();

        double xScroll = player.getX() - screen.width / 2;
        double yScroll = player.getY() - screen.height / 2;

        level.setScroll((int)xScroll, (int)yScroll);
//        level.render(screen); -> Shouldn't happen anymore since Layers.

        // Render layers here:
        for (int i=0; i < layerStack.size(); i++){
            layerStack.get(i).render(screen);
        }

        for(int i=0; i<pixels.length; i++){
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(), getHeight());
        g.drawImage(img, 0, 0, width*scale, height*scale, null);

        g.fillRect(Mouse.getX()-16, Mouse.getY()-16, 32,32);

        uiManager.render(g);

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
