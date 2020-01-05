package com.aryan.rain.graphics;

public class Screen {
    private int width;
    private int height;

    public int[] pixels;

    public Screen(int width, int height){
        this.width = width;
        this.height = height;

        pixels = new int[width*height]; // 50,400 Size [0, 50399]
    }

    public void clear(){
        for(int i=0; i<pixels.length; i++){
            pixels[i] = 0;
        }
    }

    public void render(){

        for(int y=0; y < height; y++){
            for(int x=0; x < width; x++){
                pixels[x + y * width] = 0xff00ff; //0b10111 - binary
            }
        }
    }
}
