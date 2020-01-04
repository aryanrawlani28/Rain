package com.aryan.rain;

public class Game implements Runnable{

    public static int width = 300;
    public static int height = width*16/9;
    public static int scale = 3;

    private Thread thread;

    private boolean running = false;

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

    void run(){
        while (running){
            // game elements
        }
    }

}
